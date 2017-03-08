package Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * 数据库操作类，对给定的参数做不同的响应
 * 
 * */
public class Operate {
	private ConnectDB cDB = null;
	private Connection con = null;
	private ResultSet result = null;
	private PreparedStatement prepar = null;
	public Operate(){
		try {
			cDB = new ConnectDB();
			//连接数据库
			con = cDB.getConObj();
			
			System.out.println(con);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	//查询操作
	public void Select(String sql){
		
		try {
			//获取动态sql
			prepar = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			//获取结果集
			this.result = prepar.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	//插入操作
	public void Insert(String sql){
		try {
			//获取动态sql
			prepar = con.prepareStatement(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			
			prepar.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	//删除操作
	public void Delete(String sql){
		
		try {
			//获取动态sql
			prepar = con.prepareStatement(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			
			prepar.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	//数据更新
	public void Update(String sql){
		try{
			
			prepar = con.prepareStatement(sql);
			
			prepar.executeUpdate();
		}catch(Exception e){
			
		}
	}
	
	public ResultSet getResult(){
		return this.result;
	}
}
