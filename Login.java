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
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class Login extends JPanel 
{
	JLabel l1,l2;
	JButton b1,b2;
	Dimension d1;
	JTextField tf1,tf2;
	index in;
	String user;
	Font f1;
public Login(Dimension d1,index ind){
	setLayout(null);
	this.d1 = d1;
	in = ind;
	int width = (int)d1.getWidth()/4;
	int height = (int)d1.getHeight()/3;
	width=10;
	

	f1 = new Font("Courier New",Font.PLAIN,14);
	l1 = new JLabel("Username");
	l1.setBounds(width,height,100,30);
	add(l1);
	l1.setFont(f1);

	tf1 = new JTextField();
	tf1.setBounds(width+100,height,200,30);
	add(tf1);
	tf1.setFont(f1);

	l2 = new JLabel("Password");
	l2.setBounds(width,height+50,100,30);
	add(l2);
	l2.setFont(f1);

	tf2 = new JPasswordField();
	tf2.setBounds(width+100,height+50,200,30);
	add(tf2);
	tf2.setFont(f1);
	

	b1 = new JButton("Login");
	b1.setBounds(width+50,height+100,100,30);
	add(b1);
	b1.setFont(f1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			user = tf1.getText();
			String pass =tf2.getText();
			if(user.length() <= 0 || user == null){
				JOptionPane.showMessageDialog(null,"Please enter username");
				tf1.grabFocus();
				return;
			}
			if(pass.length() <= 0 || pass == null){
				JOptionPane.showMessageDialog(null,"Please enter password");
				tf2.grabFocus();
				return;
			}
			try{
				if(user.equals("admin") && pass.equals("admin")){
					in.enable();
					in.card.show(in.p2,"p3");
				}else{
					JOptionPane.showMessageDialog(null,"Invalid Login");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	});

}
public void clear(){
	tf1.setText("");
	tf2.setText("");
}
}