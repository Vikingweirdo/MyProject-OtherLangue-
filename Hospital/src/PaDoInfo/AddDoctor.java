package PaDoInfo;

import javax.swing.*;

import Connect.Operate;

import java.awt.*;
import java.awt.event.*;

public class AddDoctor {
	public JFrame frame = new JFrame("医生信息的增加");// 创建窗体
	JPanel panel = new JPanel();

	JLabel stafflabel = new JLabel("职工号", JLabel.CENTER);// 创建标签
	JLabel namelabel = new JLabel("姓名", JLabel.CENTER);
	JLabel sexlabel = new JLabel("性别", JLabel.CENTER);
	JLabel agelabel = new JLabel("年龄", JLabel.CENTER);
	JLabel deplabel = new JLabel("科室", JLabel.CENTER);
	JLabel deglabel = new JLabel("职称", JLabel.CENTER);
	JLabel phonelabel = new JLabel("联系方式", JLabel.CENTER);
	JLabel resultShow = new JLabel();
	Font font = new Font("serief", Font.BOLD, 25);

	JTextField stafftext = new JTextField();// 设置文本框
	JTextField nametext = new JTextField();
	JTextField sextext = new JTextField();
	JTextField agetext = new JTextField();
	JTextField deptext = new JTextField();
	JTextField degtext = new JTextField();
	JTextField phonetext = new JTextField();

	// 获取数据库操作对象
	private Operate op = null;

	public void init() {
		panel.setLayout(null);
		stafflabel.setFont(font);// 设置标签内容字体的格式和大小
		namelabel.setFont(font);
		sexlabel.setFont(font);
		agelabel.setFont(font);
		deplabel.setFont(font);
		deglabel.setFont(font);
		phonelabel.setFont(font);
		resultShow.setFont(font);
		stafflabel.setBounds(70, 100, 150, 50);// 设置标签的大小和位置
		namelabel.setBounds(70, 150, 150, 50);
		sexlabel.setBounds(70, 200, 150, 50);
		agelabel.setBounds(70, 250, 150, 50);
		deplabel.setBounds(70, 300, 150, 50);
		deglabel.setBounds(70, 350, 150, 50);
		phonelabel.setBounds(70, 400, 150, 50);
		resultShow.setBounds(150, 560, 300, 50);

		stafftext.setBounds(280, 110, 150, 30);// 设置文本框的大小和位置
		nametext.setBounds(280, 160, 150, 30);
		sextext.setBounds(280, 210, 150, 30);
		agetext.setBounds(280, 260, 150, 30);
		deptext.setBounds(280, 310, 150, 30);
		degtext.setBounds(280, 360, 150, 30);
		phonetext.setBounds(280, 410, 150, 30);

		JButton surebutton = new JButton("确定");// 设置按钮
		JButton retbutton = new JButton("返回");
		surebutton.setBounds(150, 480, 80, 30);// 设置按钮的大小和位置
		retbutton.setBounds(300, 480, 80, 30);

		frame.add(panel);// 将面板加入窗体
		panel.add(stafflabel);// 将标签加入面板
		panel.add(namelabel);
		panel.add(sexlabel);
		panel.add(agelabel);
		panel.add(deplabel);
		panel.add(deglabel);
		panel.add(phonelabel);
		panel.add(resultShow);
		panel.add(stafftext);// 将文本框加入面板
		panel.add(nametext);
		panel.add(sextext);
		panel.add(agetext);
		panel.add(deptext);
		panel.add(degtext);
		panel.add(phonetext);

		panel.add(surebutton);// 将按钮加入面板
		panel.add(retbutton);
		frame.setSize(600, 800);// 设置窗体的大小
		frame.setResizable(false);// 确定窗口的大小不能再改变
		frame.setVisible(true);// 显示窗体

		// 给surebutton加入事件监听
		surebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ((stafftext.getText().isEmpty())
						|| (nametext.getText().isEmpty())
						|| // 如果文本框其中一个为空，则输出警告
						(sextext.getText().isEmpty())
						|| (agetext.getText().isEmpty())
						|| (deptext.getText().isEmpty())
						|| (degtext.getText().isEmpty())
						|| (phonetext.getText().isEmpty())) {
					resultShow.setText("警告：输入不可为空！");
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

		// 给resultbutton加入事件监听
		retbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();// 销毁窗体
			}

		});
	}

	// 设置数据库操作对象
	public void setOperate(Operate op) {
		this.op = op;
	}

	// 插入数据库
	public void InsertDB(String id, String name, String sex, int age,
			String phone, String deparment, String staff) {
		op.Insert("insert into doctor values(\"" + id + "\" , \"" + name
				+ "\",\"" + sex + "\"," + age + ",\"" + phone + "\",\""
				+ deparment + "\",\"" + staff + "\");");

	}
}
