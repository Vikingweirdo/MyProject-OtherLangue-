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
	JFrame frame = new JFrame("ҽ����ѯ���");// ��������
	JPanel panel = new JPanel();
	JLabel stafflabel = new JLabel("ְ����", JLabel.CENTER);// ������ǩ
	JLabel namelabel = new JLabel("����", JLabel.CENTER);
	JLabel sexlabel = new JLabel("�Ա�", JLabel.CENTER);
	JLabel agelabel = new JLabel("����", JLabel.CENTER);
	JLabel deplabel = new JLabel("����", JLabel.CENTER);
	JLabel deglabel = new JLabel("ְ��", JLabel.CENTER);
	JLabel phonelabel = new JLabel("��ϵ��ʽ", JLabel.CENTER);
	Font font = new Font("", Font.BOLD, 25);
	
	JTextField text1 = new JTextField();
	JTextField text2 = new JTextField();
	JTextField text3 = new JTextField();
	JTextField text4 = new JTextField();
	JTextField text5 = new JTextField();
	JTextField text6 = new JTextField();
	JTextField text7 = new JTextField();
	
	
	//��ѯ����
	private String edit = null;
	
	//����׼��
	private Operate op = null;//��������
	private int rowCount = 0;
	

	public void Init() {
		panel.setLayout(null);
		stafflabel.setFont(font);// ���ñ�ǩ��������ĸ�ʽ�ʹ�С
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

		//�������
		fillData();
		
		// ������ʾλ��
		frame.setLocation(900, 300);
		frame.setSize(550, 700);
		frame.setVisible(true);
	}
	//��ȡ��������
	public void setOperate(Operate op){
		this.op = op;
	}
	
	//���ò�ѯ����
	public void setEdit(String edit){
		this.edit = edit;
	}
	public String getEdit(){
		return this.edit;
	}
	

	//���ò�ѯ�������
	public void fillData() {
		ResultSet result = null;	//��ȡ��ѯ�����
		op.Select("select * from doctor where DoId = '" + edit
				+ "';");
		
		result = op.getResult();
		
		try {
			result.last();// �ƶ������һ��
			rowCount = result.getRow(); // ��ȡ����
			
			if(rowCount == 0){		//�޽������
				return ;
			}
			
			
			result.first(); // �ƶ�����һ��
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
