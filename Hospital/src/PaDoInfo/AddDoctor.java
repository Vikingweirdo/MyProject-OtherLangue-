package PaDoInfo;

import javax.swing.*;

import Connect.Operate;

import java.awt.*;
import java.awt.event.*;

public class AddDoctor {
	public JFrame frame = new JFrame("ҽ����Ϣ������");// ��������
	JPanel panel = new JPanel();

	JLabel stafflabel = new JLabel("ְ����", JLabel.CENTER);// ������ǩ
	JLabel namelabel = new JLabel("����", JLabel.CENTER);
	JLabel sexlabel = new JLabel("�Ա�", JLabel.CENTER);
	JLabel agelabel = new JLabel("����", JLabel.CENTER);
	JLabel deplabel = new JLabel("����", JLabel.CENTER);
	JLabel deglabel = new JLabel("ְ��", JLabel.CENTER);
	JLabel phonelabel = new JLabel("��ϵ��ʽ", JLabel.CENTER);
	JLabel resultShow = new JLabel();
	Font font = new Font("serief", Font.BOLD, 25);

	JTextField stafftext = new JTextField();// �����ı���
	JTextField nametext = new JTextField();
	JTextField sextext = new JTextField();
	JTextField agetext = new JTextField();
	JTextField deptext = new JTextField();
	JTextField degtext = new JTextField();
	JTextField phonetext = new JTextField();

	// ��ȡ���ݿ��������
	private Operate op = null;

	public void init() {
		panel.setLayout(null);
		stafflabel.setFont(font);// ���ñ�ǩ��������ĸ�ʽ�ʹ�С
		namelabel.setFont(font);
		sexlabel.setFont(font);
		agelabel.setFont(font);
		deplabel.setFont(font);
		deglabel.setFont(font);
		phonelabel.setFont(font);
		resultShow.setFont(font);
		stafflabel.setBounds(70, 100, 150, 50);// ���ñ�ǩ�Ĵ�С��λ��
		namelabel.setBounds(70, 150, 150, 50);
		sexlabel.setBounds(70, 200, 150, 50);
		agelabel.setBounds(70, 250, 150, 50);
		deplabel.setBounds(70, 300, 150, 50);
		deglabel.setBounds(70, 350, 150, 50);
		phonelabel.setBounds(70, 400, 150, 50);
		resultShow.setBounds(150, 560, 300, 50);

		stafftext.setBounds(280, 110, 150, 30);// �����ı���Ĵ�С��λ��
		nametext.setBounds(280, 160, 150, 30);
		sextext.setBounds(280, 210, 150, 30);
		agetext.setBounds(280, 260, 150, 30);
		deptext.setBounds(280, 310, 150, 30);
		degtext.setBounds(280, 360, 150, 30);
		phonetext.setBounds(280, 410, 150, 30);

		JButton surebutton = new JButton("ȷ��");// ���ð�ť
		JButton retbutton = new JButton("����");
		surebutton.setBounds(150, 480, 80, 30);// ���ð�ť�Ĵ�С��λ��
		retbutton.setBounds(300, 480, 80, 30);

		frame.add(panel);// �������봰��
		panel.add(stafflabel);// ����ǩ�������
		panel.add(namelabel);
		panel.add(sexlabel);
		panel.add(agelabel);
		panel.add(deplabel);
		panel.add(deglabel);
		panel.add(phonelabel);
		panel.add(resultShow);
		panel.add(stafftext);// ���ı���������
		panel.add(nametext);
		panel.add(sextext);
		panel.add(agetext);
		panel.add(deptext);
		panel.add(degtext);
		panel.add(phonetext);

		panel.add(surebutton);// ����ť�������
		panel.add(retbutton);
		frame.setSize(600, 800);// ���ô���Ĵ�С
		frame.setResizable(false);// ȷ�����ڵĴ�С�����ٸı�
		frame.setVisible(true);// ��ʾ����

		// ��surebutton�����¼�����
		surebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ((stafftext.getText().isEmpty())
						|| (nametext.getText().isEmpty())
						|| // ����ı�������һ��Ϊ�գ����������
						(sextext.getText().isEmpty())
						|| (agetext.getText().isEmpty())
						|| (deptext.getText().isEmpty())
						|| (degtext.getText().isEmpty())
						|| (phonetext.getText().isEmpty())) {
					resultShow.setText("���棺���벻��Ϊ�գ�");
				} else {
					InsertDB(stafftext.getText(),nametext.getText(),
							sextext.getText(),
							Integer.parseInt(agetext.getText()),
							phonetext.getText(),
							degtext.getText(),deptext.getText());
					frame.dispose();
					DoctorUI.jframe.dispose();
					DoctorUI doctor = new DoctorUI();
					doctor.setOperate(op);
					doctor.Init();
				}
			}
		});

		// ��resultbutton�����¼�����
		retbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();// ���ٴ���
			}

		});
	}

	// �������ݿ��������
	public void setOperate(Operate op) {
		this.op = op;
	}

	// �������ݿ�
	public void InsertDB(String id, String name, String sex, int age,
			String phone, String deparment, String staff) {
		op.Insert("insert into doctor values(\"" + id + "\" , \"" + name
				+ "\",\"" + sex + "\"," + age + ",\"" + phone + "\",\""
				+ deparment + "\",\"" + staff + "\");");

	}
}
