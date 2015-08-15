package smart;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JOptionPane;
public class UpdateQuantity extends JPanel{
	JLabel l1,l2;
	JButton b1;
	Dimension d1;
	JTextField tf1;
	JComboBox c1;
	Font f1;
public UpdateQuantity(Dimension d1){
	setLayout(null);
	this.d1 = d1;
	int width = (int)d1.getWidth()/4;
	int height = (int)d1.getHeight()/3;
	width=10;
	

	f1 = new Font("Courier New",Font.PLAIN,14);
	l1 = new JLabel("Product ID");
	l1.setBounds(width,height,100,30);
	add(l1);
	l1.setFont(f1);

	c1 = new JComboBox();
	c1.setBounds(width+140,height,200,30);
	add(c1);
	c1.setFont(f1);

	l2 = new JLabel("Quantity");
	l2.setBounds(width,height+50,100,30);
	add(l2);
	l2.setFont(f1);

	tf1 = new JTextField();
	tf1.setBounds(width+140,height+50,200,30);
	add(tf1);
	tf1.setFont(f1);

	
	b1 = new JButton("Update Quantity");
	b1.setBounds(width+50,height+100,180,30);
	add(b1);
	b1.setFont(f1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			update();
		}
	});

}
public void clear(){
	tf1.setText("");
}
public void getProductID(){
	try{
		c1.removeAllItems();
		String item = DBCon.getProductID();
		if(!item.equals("none")){
			String it[] = item.split(",");
			for(String str : it){
				c1.addItem(str);
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
public void update(){
	String pid = c1.getSelectedItem().toString().trim();
	String qty = tf1.getText();
	if(qty.length() <= 0 || qty == null){
		JOptionPane.showMessageDialog(null,"Please enter quantity");
		tf1.grabFocus();
		return;
	}
	int quantity = 0;
	try{
		quantity = Integer.parseInt(qty.trim());
	}catch(NumberFormatException nfe){
		JOptionPane.showMessageDialog(null,"Quantity must be numeric value only");
		tf1.grabFocus();
		return;
	}
	try{
		String msg = DBCon.updateQuantity(pid,qty);
		if(msg.equals("success")){
			JOptionPane.showMessageDialog(null,"quantity details added");
			getProductID();
			clear();
		}else{
			JOptionPane.showMessageDialog(null,"Error in adding quantity details");
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
}