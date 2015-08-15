package smart;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Toolkit;
public class index extends JFrame implements ActionListener,Runnable
{
	Dimension d1;
	JPanel p1,p2,p3;
	JMenuBar bar;
	JMenu m1,m2,m3;
	JMenuItem login,exit,logout;
	JMenuItem addproduct,issuecard,update_quantity,recharge;
	JMenuItem purchase;
	Font f1,f2;
	JLabel l1,l2;
	CardLayout card;
	Login logins;
	AddProduct ap;
	UpdateQuantity uq;
	IssueCard ic;
	Recharge rc;
	Shopping shop;
	Thread thread;
public index(){
	super("Smart Shopping");
	d1 = Toolkit.getDefaultToolkit().getScreenSize();
	p1 = new JPanel();
	f1 = new Font("Courier New",Font.PLAIN,18);
	l1 = new JLabel("<HTML><BODY><CENTER>Smart Shopping System using RFID</CENTER></BODY></HTML>");
	l1.setFont(f1);
	p1.add(l1);
	p1.setBackground(new Color(223,159,123));
	p2 = new JPanel();
	card = new CardLayout();
	p2.setLayout(card);

	bar = new JMenuBar();
	setJMenuBar(bar);

	f2 = new Font("Courier New",Font.PLAIN,14);
	m1 = new JMenu("Administrator");
	bar.add(m1);
	m1.setFont(f2);
	
	login = new JMenuItem("Login");
	m1.add(login);
	login.addActionListener(this);
	login.setFont(f2);

	addproduct = new JMenuItem("Add Products");
	m1.add(addproduct);
	addproduct.addActionListener(this);
	addproduct.setFont(f2);

	issuecard = new JMenuItem("Issue Card");
	m1.add(issuecard);
	issuecard.addActionListener(this);
	issuecard.setFont(f2);

	recharge = new JMenuItem("Recharge Card");
	m1.add(recharge);
	recharge.addActionListener(this);
	recharge.setFont(f2);

	update_quantity = new JMenuItem("Update Quantity");
	m1.add(update_quantity);
	update_quantity.addActionListener(this);
	update_quantity.setFont(f2);

	logout = new JMenuItem("Logout");
	m1.add(logout);
	logout.addActionListener(this);
	logout.setFont(f2);

	m2 = new JMenu("Customer");
	bar.add(m2);
	m2.setFont(f2);

	purchase = new JMenuItem("Purchase");
	m2.add(purchase);
	purchase.addActionListener(this);
	purchase.setFont(f2);
	
	
	m3 = new JMenu("Exit");
	bar.add(m3);
	m3.setFont(f2);


	exit = new JMenuItem("Exit");
	m3.add(exit);
	exit.addActionListener(this);
	exit.setFont(f2);

	

	p3 = new JPanel();
	p3.setLayout(new BorderLayout());
	ImageIcon icon=new ImageIcon("img/images.jpg");
	l2 = new JLabel(icon);
	p3.add(l2);

	d1 = new Dimension(icon.getIconWidth(),icon.getIconHeight());

	
	p2.add(p3,"p3");
	card.show(p2,"p3");

	logins = new Login(d1,this);
	p2.add(logins,"login");

	ap = new AddProduct(d1);
	p2.add(ap,"ap");
	
	uq = new UpdateQuantity(d1);
	p2.add(uq,"uq");

	ic = new IssueCard(d1);
	p2.add(ic,"ic");

	rc = new Recharge(d1);
	p2.add(rc,"rc");

	shop = new Shopping();
	p2.add(shop,"shop");

	getContentPane().add(p1,BorderLayout.NORTH);
	getContentPane().add(p2,BorderLayout.CENTER);

	disable(); 
	thread = new Thread(this);
	thread.start();
}
public void enable(){
	addproduct.setEnabled(true);
	issuecard.setEnabled(true);
	update_quantity.setEnabled(true);
	logout.setEnabled(true);
	login.setEnabled(false);
	recharge.setEnabled(true);
}
public void disable(){
	addproduct.setEnabled(false);
	issuecard.setEnabled(false);
	update_quantity.setEnabled(false);
	logout.setEnabled(false);
	login.setEnabled(true);
	recharge.setEnabled(false);
}
public void run(){
	try{
		while(true){
			l1.setForeground(Color.white);
			thread.sleep(500);
			l1.setForeground(Color.blue);
			thread.sleep(500);
			l1.setForeground(Color.black);
			thread.sleep(500);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
public void actionPerformed(ActionEvent ae){
	if(ae.getSource() == exit){
		System.exit(0);
	}
	if(ae.getSource() == logout){
		disable();
		card.show(p2,"p3");
	}
	if(ae.getSource() == login){
		logins.clear();
		card.show(p2,"login");
	}
	if(ae.getSource() == addproduct){
		ap.clear();
		card.show(p2,"ap");
	}
	if(ae.getSource() == update_quantity){
		uq.clear();
		uq.getProductID();
		card.show(p2,"uq");
	}
	if(ae.getSource() == issuecard){
		ic.clear();
		card.show(p2,"ic");
	}
	if(ae.getSource() == recharge){
		rc.clear();
		rc.getCardID();
		card.show(p2,"rc");
	}
	if(ae.getSource() == purchase){
		card.show(p2,"shop");
	}
}
public static void main(String a[])throws Exception{
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	index in = new index();
	in.setVisible(true);
	in.setExtendedState(JFrame.MAXIMIZED_BOTH);
}
}