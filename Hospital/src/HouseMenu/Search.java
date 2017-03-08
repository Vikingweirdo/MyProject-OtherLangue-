package HouseMenu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import Connect.Operate;

public class Search extends JFrame{
	
	//新建窗口，模板，文本框，按钮，标签；
	JFrame jF;
	JPanel jP;
	JTextField jT;
	JButton jB;
	JLabel jL;
	JLabel jL1;
	Font font;
	
	private Operate op = null; // 数据库操作对象
	private String tableFlag = null;
	
	public void Init1(final String s){
		
		//实例化窗口，模板，标签，文本框，按钮；
		jF = new JFrame(s);
		jP = new JPanel();					
		jL = new JLabel(s+":");
		jL1 = new JLabel("");
		jT = new JTextField("");	
		jB = new JButton("搜索");
		font = new Font("Serief", Font.BOLD, 15);
		
		//确定标签，文本框，按钮的位置和大小；
		jL.setBounds(20,20,150,50);	
		jL.setFont(font);
		jL1.setBounds(20, 80, 200, 20);
		jL1.setFont(font);
		jT.setBounds(105,35,200,20);
		jB.setBounds(310,35,60,20);
		
		//搜索按钮的监听；
		jB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//文本框的判空操作；
				if(jT.getText().equals(""))
				{
					jL1.setText("警告：文本框不可为空！");
					
				}
				else{
					Result result = new Result();
					result.setOperate(op);
					result.setEditText(jT.getText());
					result.setTableFlag(tableFlag);
					result.Init(s);
					jF.dispose();	
				}
			}
		});
		
		
		//为窗口添加面板，标签，文本框，按钮，并设置窗口大小；
		jP.setLayout(null);
		jP.add(jL1);
		jP.add(jL);
		jP.add(jT);
		jP.add(jB);
		jF.add(jP);
		
		jF.setSize(470,150);
		jF.setLocation(900,300);
		jF.setResizable(false);
		jF.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jF.setVisible(true);
	}

	// 获取操作对象
	public void setOperate(Operate op) {
		this.op = op;
	}

	public Operate getOperate() {
		return this.op;
	}
	//获取表条件
	public void setTableFlag(String tableFlag){
		this.tableFlag = tableFlag;
	}
}