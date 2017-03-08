package PaDoInfo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Connect.Operate;

class AddPatient {
	public JFrame frame = new JFrame("病人信息的增加");// 创建窗体
	JPanel panel = new JPanel();

	JLabel patlabel = new JLabel("病历号", JLabel.CENTER);// 创建标签
	JLabel namelabel = new JLabel("姓名", JLabel.CENTER);
	JLabel sexlabel = new JLabel("性别", JLabel.CENTER);
	JLabel agelabel = new JLabel("年龄", JLabel.CENTER);
	JLabel idlabel = new JLabel("身份证号", JLabel.CENTER);
	JLabel addresslabel = new JLabel("家庭住址", JLabel.CENTER);
	JLabel phonelabel = new JLabel("联系方式", JLabel.CENTER);
	JLabel doclabel = new JLabel("主治医生", JLabel.CENTER);
	JLabel intimelabel = new JLabel("入院时间", JLabel.CENTER);
	JLabel outtimelabel = new JLabel("出院时间", JLabel.CENTER);
	JLabel resultShow = new JLabel();
	Font font = new Font("serief", Font.BOLD, 25);

	JTextField pattext = new JTextField();// 设置文本框
	JTextField nametext = new JTextField();
	JTextField sextext = new JTextField();
	JTextField agetext = new JTextField();
	JTextField idtext = new JTextField();
	JTextField addresstext = new JTextField();
	JTextField phonetext = new JTextField();
	JTextField doctext = new JTextField();
	JTextField intimetext = new JTextField();
	JTextField outtimetext = new JTextField();

	// 获取数据库操作对象
	private Operate op = null;

	public void init() {

		panel.setLayout(null);
		patlabel.setFont(font);// 设置标签内容字体的格式和大小
		namelabel.setFont(font);
		sexlabel.setFont(font);
		agelabel.setFont(font);
		idlabel.setFont(font);
		addresslabel.setFont(font);
		phonelabel.setFont(font);
		doclabel.setFont(font);
		intimelabel.setFont(font);
		outtimelabel.setFont(font);
		resultShow.setFont(font);
		patlabel.setBounds(70, 100, 150, 50);// 设置标签的大小和位置
		namelabel.setBounds(70, 150, 150, 50);
		sexlabel.setBounds(70, 200, 150, 50);
		agelabel.setBounds(70, 250, 150, 50);
		idlabel.setBounds(70, 300, 150, 50);
		addresslabel.setBounds(70, 350, 150, 50);
		phonelabel.setBounds(70, 400, 150, 50);
		doclabel.setBounds(70, 450, 150, 50);
		intimelabel.setBounds(70, 500, 150, 50);
		outtimelabel.setBounds(70, 550, 150, 50);
		resultShow.setBounds(150, 710, 300, 50);

		pattext.setBounds(280, 110, 150, 30);// 设置文本框的大小和位置
		nametext.setBounds(280, 160, 150, 30);
		sextext.setBounds(280, 210, 150, 30);
		agetext.setBounds(280, 260, 150, 30);
		idtext.setBounds(280, 310, 150, 30);
		addresstext.setBounds(280, 360, 150, 30);
		phonetext.setBounds(280, 410, 150, 30);
		doctext.setBounds(280, 460, 150, 30);
		intimetext.setBounds(280, 510, 150, 30);
		outtimetext.setBounds(280, 560, 150, 30);

		JButton surebutton = new JButton("确定");// 设置按钮
		JButton retbutton = new JButton("返回");
		surebutton.setBounds(150, 630, 80, 30);// 设置按钮的大小和位置
		retbutton.setBounds(300, 630, 80, 30);

		frame.add(panel);// 将面板加入窗体
		panel.add(patlabel);// 将标签加入面板
		panel.add(namelabel);
		panel.add(sexlabel);
		panel.add(agelabel);
		panel.add(idlabel);
		panel.add(addresslabel);
		panel.add(phonelabel);
		panel.add(doclabel);
		panel.add(intimelabel);
		panel.add(outtimelabel);
		panel.add(resultShow);
		panel.add(pattext);// 将文本框加入面板
		panel.add(nametext);
		panel.add(sextext);
		panel.add(agetext);
		panel.add(idtext);
		panel.add(addresstext);
		panel.add(phonetext);
		panel.add(doctext);
		panel.add(intimetext);
		panel.add(outtimetext);

		panel.add(surebutton);// 将按钮加入面板
		panel.add(retbutton);
		frame.setSize(580, 900);// 设置窗体的大小
		frame.setResizable(false);// 确定窗口的大小不能再改变
		frame.setVisible(true);// 显示窗体
		// 给确定按钮加入事件监听
		surebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ((pattext.getText().isEmpty())
						|| (nametext.getText().isEmpty())
						|| // 如果文本框其中一个为空，则输出警告
						(sextext.getText().isEmpty())
						|| (agetext.getText().isEmpty())
						|| (idtext.getText().isEmpty())
						|| (addresstext.getText().isEmpty())
						|| (phonetext.getText().isEmpty())
						|| (doctext.getText().isEmpty())
						|| (intimetext.getText().isEmpty())
						|| (outtimetext.getText().isEmpty())) {
					resultShow.setText("警告：输入不可为空！");
				} else {
					InsertDB(pattext.getText(),nametext.getText(),
							sextext.getText(),
							Integer.parseInt(agetext.getText()),
							phonetext.getText(),
							addresstext.getText(),
							idtext.getText(),
							intimetext.getText(),
							outtimetext.getText());
					
					InsertDB(pattext.getText(),doctext.getText());
					frame.dispose();
					UI.jframe.dispose();
					UI patient = new UI();
					patient.setOperate(op);
					patient.Init();
				}
			}

		});
		// 给返回按钮加入事件监听
		retbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}

		});
	}

	// 设置数据库操作对象
	public void setOperate(Operate op) {
		this.op = op;
	}

	// 插入数据库
	public void InsertDB(String Paid, String PaName, String sex, int age,
			String phone, String PaAdr, String PapNum , String dataIn , String dataOut) {
		
		op.Insert("insert into patient values(\"" + Paid + "\" , \"" + PaName
				+ "\",\"" + sex + "\"," + age + ",\"" + phone + "\",\""
				+ PaAdr + "\",\"" + PapNum + "\",\""+dataIn+"\",\""+dataOut+"\");");

	}
	//插入数据库
	public void InsertDB(String Paid , String Doid){
		op.Insert("insert into relapado values(\""+Paid+"\",\""+Doid+"\");");
	}
}