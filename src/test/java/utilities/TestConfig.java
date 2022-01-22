package utilities;

public class TestConfig{
	
	public static String server="smtp.gmail.com";
	public static String from = "hammadonoff@gmail.com";
	public static String password = "Computer@g19";
	public static String[] to ={"hammadalik01@gmail.com","hammad@tlu.ee"};
	public static String subject = "Automation Testing Result Report";
	public static String messageBody ="Error found while clicking on an element";
	
	
	public static String attachmentPath = System.getProperty("user.dir") +"\\test-output\\html\\index.html";
	
	
	//public static String attachmentPath="D:\\ok\\1.jpg";
	public static String attachmentName="Testing Report";
	
	
	
	
	//SQL DATABASE DETAILS	
	public static String driver="net.sourceforge.jtds.jdbc.Driver"; 
	public static String dbConnectionUrl="jdbc:jtds:sqlserver://192.101.44.22;DatabaseName=monitor_eval"; 
	public static String dbUserName="sa"; 
	public static String dbPassword="$ql$!!1"; 
	
	
	
	//MYSQL DATABASE DETAILS
	public static String mysqldriver="com.mysql.jdbc.Driver";
	public static String mysqluserName = "root";
	public static String mysqlpassword = "selenium";
	public static String mysqlurl = "jdbc:mysql://localhost:3306/acs";
	
	
	
	
	
	
	
	
	
}
