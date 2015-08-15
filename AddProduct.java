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
import javax.swing.JColorChooser;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JOptionPane;

public class AddProduct extends JPanel 
{
	JLabel l1,l2,l3,l4,l5,l6;
	JButton b1,b2;
	Dimension d1;
	JTextField tf1,tf2,tf3,tf4,tf5,tf6;
	index in;
	String user;
	Font f1;
public AddProduct(Dimension d1){
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

	tf1 = new JTextField();
	tf1.setBounds(width+140,height,200,30);
	add(tf1);
	tf1.setFont(f1);

	l2 = new JLabel("Product Name");
	l2.setBounds(width,height+50,100,30);
	add(l2);
	l2.setFont(f1);

	tf2 = new JTextField();
	tf2.setBounds(width+140,height+50,200,30);
	add(tf2);
	tf2.setFont(f1);

	l3 = new JLabel("Product Price");
	l3.setBounds(width,height+100,140,30);
	add(l3);
	l3.setFont(f1);

	tf3 = new JTextField();
	tf3.setBounds(width+140,height+100,200,30);
	add(tf3);
	tf3.setFont(f1);

	l4 = new JLabel("Quantity");
	l4.setBounds(width,height+150,100,30);
	add(l4);
	l4.setFont(f1);

	tf4 = new JTextField();
	tf4.setBounds(width+140,height+150,200,30);
	add(tf4);
	tf4.setFont(f1);

	l5 = new JLabel("Product Details");
	l5.setBounds(width,height+200,140,30);
	add(l5);
	l5.setFont(f1);

	tf5 = new JTextField();
	tf5.setBounds(width+140,height+200,500,30);
	add(tf5);
	tf5.setFont(f1);

	l6 = new JLabel("Tag Value");
	l6.setBounds(width,height+250,100,30);
	add(l6);
	l6.setFont(f1);

	tf6 = new JTextField();
	tf6.setBounds(width+140,height+250,200,30);
	add(tf6);
	tf6.setFont(f1);
	

	b1 = new JButton("Add Product");
	b1.setBounds(width+50,height+300,140,30);
	add(b1);
	b1.setFont(f1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			addProduct();
		}
	});

}
public void clear(){
	tf1.setText("");
	tf2.setText("");
	tf3.setText("");
	tf4.setText("");
	tf5.setText("");
	tf6.setText("");
}
public void addProduct(){
	String pid = tf1.getText();
	String pname = tf2.getText();
	String price = tf3.getText();
	String qty = tf4.getText();
	String details = tf5.getText();
	String tag =tf6.getText();
	if(pid.length() <= 0 || pid == null){
		JOptionPane.showMessageDialog(null,"Please enter product id");
		tf1.grabFocus();
		return;
	}
	if(pname.length() <= 0 || pname == null){
		JOptionPane.showMessageDialog(null,"Please enter product name");
		tf2.grabFocus();
		return;
	}
	if(price.length() <= 0 || price == null){
		JOptionPane.showMessageDialog(null,"Please enter product price");
		tf3.grabFocus();
		return;
	}
	if(qty.length() <= 0 || qty == null){
		JOptionPane.showMessageDialog(null,"Please enter quantity");
		tf4.grabFocus();
		return;
	}
	if(details.length() <= 0 || details == null){
		JOptionPane.showMessageDialog(null,"Please enter product details");
		tf5.grabFocus();
		return;
	}
	if(tag.length() <= 0 || tag == null){
		JOptionPane.showMessageDialog(null,"Please enter Tag value");
		tf6.grabFocus();
		return;
	}
	double prices = 0;
	int quantity = 0;
	try{
		prices = Double.parseDouble(price.trim());
	}catch(NumberFormatException nfe){
		JOptionPane.showMessageDialog(null,"Price must be numeric value only");
		tf3.grabFocus();
		return;
	}
	try{
		quantity = Integer.parseInt(qty.trim());
	}catch(NumberFormatException nfe){
		JOptionPane.showMessageDialog(null,"Quantity must be numeric value only");
		tf4.grabFocus();
		return;
	}
	try{
		String input[] = {pid,pname,price,qty,details,tag};
		String msg = DBCon.addProduct(input);
		if(msg.equals("success")){
			JOptionPane.showMessageDialog(null,"Product details added");
			clear();
		}else{
			JOptionPane.showMessageDialog(null,"Error in adding product details");
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
}