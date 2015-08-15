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
public class IssueCard extends JPanel 
{
	JLabel l1,l2,l3,l4,l5;
	JButton b1,b2;
	Dimension d1;
	JTextField tf1,tf2,tf3,tf4,tf5;
	index in;
	String user;
	Font f1;
public IssueCard(Dimension d1){
	setLayout(null);
	this.d1 = d1;
	int width = (int)d1.getWidth()/4;
	int height = (int)d1.getHeight()/3;
	width=10;
	

	f1 = new Font("Courier New",Font.PLAIN,14);
	l1 = new JLabel("Card No");
	l1.setBounds(width,height,100,30);
	add(l1);
	l1.setFont(f1);

	tf1 = new JTextField();
	tf1.setBounds(width+140,height,200,30);
	add(tf1);
	tf1.setFont(f1);

	l2 = new JLabel("Customer Name");
	l2.setBounds(width,height+50,140,30);
	add(l2);
	l2.setFont(f1);

	tf2 = new JTextField();
	tf2.setBounds(width+140,height+50,200,30);
	add(tf2);
	tf2.setFont(f1);

	l3 = new JLabel("Contact No");
	l3.setBounds(width,height+100,140,30);
	add(l3);
	l3.setFont(f1);

	tf3 = new JTextField();
	tf3.setBounds(width+140,height+100,200,30);
	add(tf3);
	tf3.setFont(f1);

	l4 = new JLabel("Address");
	l4.setBounds(width,height+150,100,30);
	add(l4);
	l4.setFont(f1);

	tf4 = new JTextField();
	tf4.setBounds(width+140,height+150,500,30);
	add(tf4);
	tf4.setFont(f1);

	l5 = new JLabel("Amount");
	l5.setBounds(width,height+200,140,30);
	add(l5);
	l5.setFont(f1);

	tf5 = new JTextField();
	tf5.setBounds(width+140,height+200,100,30);
	add(tf5);
	tf5.setFont(f1);

	
	

	b1 = new JButton("Issue Card");
	b1.setBounds(width+50,height+250,140,30);
	add(b1);
	b1.setFont(f1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			issuecard();
		}
	});

}
public void clear(){
	tf1.setText("");
	tf2.setText("");
	tf3.setText("");
	tf4.setText("");
	tf5.setText("");
}
public void issuecard(){
	String cardno = tf1.getText();
	String cname = tf2.getText();
	String contact = tf3.getText();
	String address = tf4.getText();
	String amount = tf5.getText();
	if(cardno.length() <= 0 || cardno == null){
		JOptionPane.showMessageDialog(null,"Please enter card no");
		tf1.grabFocus();
		return;
	}
	if(cname.length() <= 0 || cname == null){
		JOptionPane.showMessageDialog(null,"Please enter customer name");
		tf2.grabFocus();
		return;
	}
	if(contact.length() <= 0 || contact == null){
		JOptionPane.showMessageDialog(null,"Please enter contact no");
		tf3.grabFocus();
		return;
	}
	if(address.length() <= 0 || address == null){
		JOptionPane.showMessageDialog(null,"Please enter address");
		tf4.grabFocus();
		return;
	}
	if(amount.length() <= 0 || amount == null){
		JOptionPane.showMessageDialog(null,"Please enter amount");
		tf5.grabFocus();
		return;
	}
	double amt = 0;
	try{
		amt = Double.parseDouble(amount.trim());
	}catch(NumberFormatException nfe){
		JOptionPane.showMessageDialog(null,"Amount must be numeric value only");
		tf5.grabFocus();
		return;
	}
	try{
		String input[] = {cardno,cname,contact,address,amount};
		String msg = DBCon.addCustomer(input);
		if(msg.equals("success")){
			JOptionPane.showMessageDialog(null,"Customer details added");
			clear();
		}else{
			JOptionPane.showMessageDialog(null,"Error in adding customer details");
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
}