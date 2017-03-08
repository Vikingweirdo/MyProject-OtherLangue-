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
	
	//�½����ڣ�ģ�壬�ı��򣬰�ť����ǩ��
	JFrame jF;
	JPanel jP;
	JTextField jT;
	JButton jB;
	JLabel jL;
	JLabel jL1;
	Font font;
	
	private Operate op = null; // ���ݿ��������
	private String tableFlag = null;
	
	public void Init1(final String s){
		
		//ʵ�������ڣ�ģ�壬��ǩ���ı��򣬰�ť��
		jF = new JFrame(s);
		jP = new JPanel();					
		jL = new JLabel(s+":");
		jL1 = new JLabel("");
		jT = new JTextField("");	
		jB = new JButton("����");
		font = new Font("Serief", Font.BOLD, 15);
		
		//ȷ����ǩ���ı��򣬰�ť��λ�úʹ�С��
		jL.setBounds(20,20,150,50);	
		jL.setFont(font);
		jL1.setBounds(20, 80, 200, 20);
		jL1.setFont(font);
		jT.setBounds(105,35,200,20);
		jB.setBounds(310,35,60,20);
		
		//������ť�ļ�����
		jB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//�ı�����пղ�����
				if(jT.getText().equals(""))
				{
					jL1.setText("���棺�ı��򲻿�Ϊ�գ�");
					
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
		
		
		//Ϊ���������壬��ǩ���ı��򣬰�ť�������ô��ڴ�С��
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

	// ��ȡ��������
	public void setOperate(Operate op) {
		this.op = op;
	}

	public Operate getOperate() {
		return this.op;
	}
	//��ȡ������
	public void setTableFlag(String tableFlag){
		this.tableFlag = tableFlag;
	}
}