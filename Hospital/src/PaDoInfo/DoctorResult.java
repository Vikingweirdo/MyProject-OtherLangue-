package PaDoInfo;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Connect.Operate;

public class DoctorResult {
	JFrame frame = new JFrame("医生查询结果");// 创建窗体
	JPanel panel = new JPanel();
	JLabel stafflabel = new JLabel("职工号", JLabel.CENTER);// 创建标签
	JLabel namelabel = new JLabel("姓名", JLabel.CENTER);
	JLabel sexlabel = new JLabel("性别", JLabel.CENTER);
	JLabel agelabel = new JLabel("年龄", JLabel.CENTER);
	JLabel deplabel = new JLabel("科室", JLabel.CENTER);
	JLabel deglabel = new JLabel("职称", JLabel.CENTER);
	JLabel phonelabel = new JLabel("联系方式", JLabel.CENTER);
	Font font = new Font("", Font.BOLD, 25);
	
	JTextField text1 = new JTextField();
	JTextField text2 = new JTextField();
	JTextField text3 = new JTextField();
	JTextField text4 = new JTextField();
	JTextField text5 = new JTextField();
	JTextField text6 = new JTextField();
	JTextField text7 = new JTextField();
	
	
	//查询条件
	private String edit = null;
	
	//数据准备
	private Operate op = null;//操作对象
	private int rowCount = 0;
	

	public void Init() {
		panel.setLayout(null);
		stafflabel.setFont(font);// 设置标签内容字体的格式和大小
		namelabel.setFont(font);
		sexlabel.setFont(font);
		agelabel.setFont(font);
		deplabel.setFont(font);
		deglabel.setFont(font);
		phonelabel.setFont(font);
		stafflabel.setBounds(70, 100, 150, 50);
		namelabel.setBounds(70, 150, 150, 50);
		sexlabel.setBounds(70, 200, 150, 50);
		agelabel.setBounds(70, 250, 150, 50);
		deplabel.setBounds(70, 300, 150, 50);
		deglabel.setBounds(70, 350, 150, 50);
		phonelabel.setBounds(70, 400, 150, 50);
		
		text1.setBounds(280, 110, 150, 30);
		text2.setBounds(280, 160, 150, 30);
		text3.setBounds(280, 210, 150, 30);
		text4.setBounds(280, 260, 150, 30);
		text5.setBounds(280, 310, 150, 30);
		text6.setBounds(280, 360, 150, 30);
		text7.setBounds(280, 410, 150, 30);
		frame.add(panel);
		panel.add(stafflabel);
		panel.add(namelabel);
		panel.add(sexlabel);
		panel.add(agelabel);
		panel.add(deplabel);
		panel.add(deglabel);
		panel.add(phonelabel);
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		panel.add(text4);
		panel.add(text5);
		panel.add(text6);
		panel.add(text7);

		//填充数据
		fillData();
		
		// 设置显示位置
		frame.setLocation(900, 300);
		frame.setSize(550, 700);
		frame.setVisible(true);
	}
	//获取操作对象
	public void setOperate(Operate op){
		this.op = op;
	}
	
	//设置查询条件
	public void setEdit(String edit){
		this.edit = edit;
	}
	public String getEdit(){
		return this.edit;
	}
	

	//设置查询内容填充
	public void fillData() {
		ResultSet result = null;	//获取查询结果集
		op.Select("select * from doctor where DoId = '" + edit
				+ "';");
		
		result = op.getResult();
		
		try {
			result.last();// 移动到最后一行
			rowCount = result.getRow(); // 获取行数
			
			if(rowCount == 0){		//无结果结束
				return ;
			}
			
			
			result.first(); // 移动到第一行
			text1.setText(result.getString(1));
			text2.setText(result.getString(2));
			text3.setText(result.getString(3));
			text4.setText(result.getString(4));
			text5.setText(result.getString(6));
			text6.setText(result.getString(7));
			text7.setText(result.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
