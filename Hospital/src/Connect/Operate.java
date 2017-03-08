package Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * ���ݿ�����࣬�Ը����Ĳ�������ͬ����Ӧ
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
			//�������ݿ�
			con = cDB.getConObj();
			
			System.out.println(con);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	//��ѯ����
	public void Select(String sql){
		
		try {
			//��ȡ��̬sql
			prepar = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			//��ȡ�����
			this.result = prepar.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	//�������
	public void Insert(String sql){
		try {
			//��ȡ��̬sql
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
	
	//ɾ������
	public void Delete(String sql){
		
		try {
			//��ȡ��̬sql
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
	
	//���ݸ���
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
