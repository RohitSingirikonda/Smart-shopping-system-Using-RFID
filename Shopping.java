package smart;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
public class Shopping extends JPanel{
	JPanel p1,p2;
	JButton b1,b2;
	JLabel l1;
	JTextField tf1;
	Font f1;
	DefaultTableModel dtm;
	JScrollPane jsp;
	JTable table;
	static boolean flag = false;
	RFID rfid;
public static boolean getFlag(){
	return flag;
}
public static void setFlag(boolean f){
	flag = f;
}
public Shopping(){
	f1 = new Font("Courier New",Font.BOLD,14);
	setLayout(new BorderLayout());
	p1 = new JPanel();
	l1 = new JLabel("Customer Name");
	l1.setFont(f1);
	//p1.add(l1);
	tf1 = new JTextField(15);
	tf1.setFont(f1);
	//p1.add(tf1);

	b1 = new JButton("Finish");
	b1.setFont(f1);
	p1.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			setFlag(true);
		}
	});

	p2 = new JPanel();
	p2.setLayout(new BorderLayout());
	dtm = new DefaultTableModel(){
		public boolean isCellEditable(int r,int c){
			return false;
		}
	};
	table = new JTable(dtm);
	table.setFont(f1);
	table.setRowHeight(30);
	table.getTableHeader().setFont(new Font("Courier New",Font.BOLD,15));
	dtm.addColumn("Product ID");
	dtm.addColumn("Product Name");
	dtm.addColumn("Quantity");
	dtm.addColumn("Price");
	dtm.addColumn("Total");
	jsp = new JScrollPane(table);
	p2.add(jsp,BorderLayout.CENTER);

	add(p1,BorderLayout.NORTH);
	add(p2,BorderLayout.CENTER);

	rfid = new RFID(dtm,flag);
	rfid.setPort("COM1");
	rfid.setRate(9600);
	rfid.initialize();
}
}