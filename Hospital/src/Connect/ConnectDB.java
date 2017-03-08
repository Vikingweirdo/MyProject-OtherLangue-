package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
/*
 * 连接数据库的类
 * 
 * */

public class ConnectDB{		
	public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	//最后一个是自己创建的数据库名字org.gjt.mm.mysql.Driver
	public static final String DBURL = "jdbc:mysql://localhost:3306/hospital";
	public static final String DBUSER = "root";
	public static final String DBPASS = "mysqladmin";
	private  Connection con = null;
	public ConnectDB() throws Exception{
		//System.out.println("successs");
		Class.forName(DBDRIVER);
		
		con = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
		//con.close();
	}
	
	public  Connection getConObj(){
		return con;
	}
}
