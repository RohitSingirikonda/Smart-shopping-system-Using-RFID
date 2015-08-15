package smart;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class RFID {
	private String PORT_NAME;
	private static final int TIME_OUT = 2000;
	private int DATA_RATE;
	private SerialPort serialPort;
	DefaultTableModel dtm;
	boolean flag;
public RFID(DefaultTableModel dtm,boolean flag){
	this.dtm = dtm;
	this.flag = flag;
}
public void setPort(String PORT_NAME){
	this.PORT_NAME = PORT_NAME;
}
public void setRate(int DATA_RATE){
	this.DATA_RATE = DATA_RATE;
}
public void initialize(){
	CommPortIdentifier portId = null;
	try{
		portId = CommPortIdentifier.getPortIdentifier(PORT_NAME);
	}catch (NoSuchPortException e){
		e.printStackTrace();
	}
	try{
		this.serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
		this.serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
		SerialPort.PARITY_NONE);
		this.serialPort.addEventListener(new MyListener(this.serialPort,dtm,flag));
		this.serialPort.notifyOnDataAvailable(true);
	}catch (Exception e){
		e.printStackTrace();
	}
}

public synchronized void close(){
	
	if (this.serialPort != null) {
		this.serialPort.removeEventListener();
		this.serialPort.close();
	}
	
}
}
class MyListener implements SerialPortEventListener{
	private final SerialPort port;
	DefaultTableModel dtm;
	StringBuilder builder = new StringBuilder();
	boolean flag;
	double total = 0;
public MyListener(SerialPort port,DefaultTableModel dtm,boolean flag){
	super();
	this.port = port;
	this.dtm = dtm;
	this.flag = flag;
}
public void serialEvent(SerialPortEvent event){
	if(event.getEventType() == SerialPortEvent.DATA_AVAILABLE){
		try{
			int available = this.port.getInputStream().available();
			byte chunk[] = new byte[available];
			this.port.getInputStream().read(chunk, 0, available);
			String input=new String(chunk);
			builder.append(input);
			writeLog();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
public void writeLog(){
	try{
		System.out.println(builder.toString()+" "+Shopping.getFlag());
		if(builder.length() == 12 && !Shopping.getFlag()){
			String output = DBCon.getDetails(builder.toString().trim());
			if(!output.equals("none")){
				String msg[] = output.split("#");
				total = total + Double.parseDouble(msg[2]);
				Object row[] = {msg[0],msg[1],"1",msg[2],total+""};
				dtm.addRow(row);
				builder.delete(0,builder.length());
			}
			builder.delete(0,builder.length());
		}
		if(builder.length() == 12 && Shopping.getFlag()){
			JOptionPane.showMessageDialog(null,"Billing completed\nTotal Amount "+total);
			Shopping.setFlag(false);
			builder.delete(0,builder.length());
			for(int i=dtm.getRowCount()-1;i>=0;i--){
				dtm.removeRow(i);
			}
			total = 0;
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
