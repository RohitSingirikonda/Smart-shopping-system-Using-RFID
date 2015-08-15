package smart;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class DBCon{
    private static Connection con;
public static Connection getCon()throws Exception {
	Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost/smartshopping","root","root");
    return con;
}
public static String addCustomer(String[] input)throws Exception{
    String msg="no";
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select card_id from customer_card where card_id='"+input[0]+"'");
    if(rs.next()){
        msg = input[0]+" card id already exist";
    }else{
		java.util.Date dd = new java.util.Date();
		java.sql.Date cd = new java.sql.Date(dd.getTime());
		PreparedStatement stat=con.prepareStatement("insert into customer_card values(?,?,?,?,?,?)");
		stat.setString(1,input[0]);
		stat.setString(2,input[1]);
		stat.setString(3,input[2]);
		stat.setString(4,input[3]);
		stat.setString(5,input[4]);
		stat.setDate(6,cd);
		int i=stat.executeUpdate();
		if(i > 0)
			msg = "success";
	}
    return msg;
}
public static String getDetails(String input)throws Exception{
    StringBuilder sb = new StringBuilder();
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select product_id,product_name,price from addproduct where product_tag='"+input+"'");
    while(rs.next()){
        sb.append(rs.getString(1)+"#"+rs.getString(2)+"#"+rs.getString(3));
    }
	if(sb.length() == 0)
		sb.append("none");
	return sb.toString();
}
public static String addProduct(String[] input)throws Exception{
    String msg="no";
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select product_id from addproduct where product_id='"+input[0]+"'");
    if(rs.next()){
        msg = input[0]+" product id already exist";
    }else{
		PreparedStatement stat=con.prepareStatement("insert into addproduct values(?,?,?,?,?,?)");
		stat.setString(1,input[0]);
		stat.setString(2,input[1]);
		stat.setString(3,input[2]);
		stat.setString(4,input[3]);
		stat.setString(5,input[4]);
		stat.setString(6,input[5]);
		int i=stat.executeUpdate();
		if(i > 0)
			msg = "success";
	}
    return msg;
}
public static String updateQuantity(String pid,String qty)throws Exception{
	String msg="no";
    con = getCon();
	PreparedStatement stat=con.prepareStatement("update addproduct set quantity=quantity+"+qty+" where product_id=?");
	stat.setString(1,pid);
	int i=stat.executeUpdate();
	stat.close();con.close();
	if(i > 0)
		msg="success";
	return msg;
}
public static String recharge(String card_id,String amount)throws Exception{
	String msg="no";
    con = getCon();
	java.util.Date dd = new java.util.Date();
	java.sql.Date cd = new java.sql.Date(dd.getTime());
	PreparedStatement stat=con.prepareStatement("update customer_card set recharge_date=?,amount=amount+"+amount+" where card_id=?");
	stat.setDate(1,cd);
	stat.setString(2,card_id);
	int i=stat.executeUpdate();
	stat.close();con.close();
	if(i > 0)
		msg="success";
	return msg;
}
public static String getProductID()throws Exception{
	StringBuilder sb = new StringBuilder();
    con = getCon();
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("select product_id from addproduct where quantity < 5");
	while(rs.next()){
		sb.append(rs.getString(1)+",");
	}
	if(sb.length() > 0)
		sb.deleteCharAt(sb.length()-1);
	if(sb.length() == 0)
		sb.append("none");
	return sb.toString();
}
public static String getCardID()throws Exception{
	StringBuilder sb = new StringBuilder();
    con = getCon();
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("select card_id from customer_card");
	while(rs.next()){
		sb.append(rs.getString(1)+",");
	}
	if(sb.length() > 0)
		sb.deleteCharAt(sb.length()-1);
	if(sb.length() == 0)
		sb.append("none");
	return sb.toString();
}
}
