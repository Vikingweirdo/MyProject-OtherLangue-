package BedInfo;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Connect.Operate;

//增加床位 窗体
class Increase extends JFrame {

	Box increaseBox;
	Box increasetext;
	Box increaseboxbase; // 使用 盒式布局 的容器

	JTextField tfroomno = new JTextField(10); // 构造一个具有10列的新的空文本框
	JTextField tfbedno = new JTextField(10); // 构造一个具有10列的新的空文本框
	JTextField tfpatientno = new JTextField(10); // 构造一个具有10列的新的空文本框

	JButton increasecertain = new JButton("确定"); // 创建“确定”按钮
	JButton increaseclear = new JButton("取消"); // 创建“取消”按钮

	JPanel pincrease = new JPanel(); // 创建面板

	// 操作数据库准备
	private Operate op = null;

	public Increase() {
		setTitle("增加床位");
		add(pincrease); // 界面 套入面板
		setBounds(100, 100, 310, 260); // 界面设置大小

		increaseBox = Box.createVerticalBox(); // 表示 盒式布局 的列式 布局 的 容器

		increaseBox.add(new Label("病房号:")); // 容器加入 病房号 按钮
		increaseBox.add(Box.createVerticalStrut(8));// 创建高度为16的不可见组件
		increaseBox.add(new Label("病床号:")); // 容器加入 病床号 标签
		increaseBox.add(Box.createVerticalStrut(8));// 创建高度为16的不可见组件
		increaseBox.add(new Label("病人号:")); // 容器加入 病床号 标签
		increaseBox.add(Box.createVerticalStrut(8)); // 创建高度为16的不可见组件
		increaseBox.add(increasecertain); // 容器加入 按钮

		increasetext = Box.createVerticalBox(); // 使用 列式布局 容器

		increasetext.add(tfroomno); // 容器加入 该文本框
		increasetext.add(Box.createVerticalStrut(16));
		increasetext.add(tfbedno); // 容器加入 该文本框
		increasetext.add(Box.createVerticalStrut(16));
		increasetext.add(tfpatientno);
		increasetext.add(Box.createVerticalStrut(16));
		increasetext.add(increaseclear); // 容器加入 “取消按钮”

		increaseboxbase = Box.createHorizontalBox(); // 使用 盒式布局 的容器

		increaseboxbase.add(increaseBox); // 该容器中加入 以上 容器
		increaseboxbase.add(Box.createHorizontalStrut(16)); // 创建宽度为 16 的不可见组件

		increaseboxbase.add(increasetext);// 加入以上容器
		pincrease.add(increaseboxbase); // 面板中加入总容器

		setVisible(true);// 可见
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 确定 按钮 按下 发生事件
		increasecertain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (tfroomno.getText().equals("")
						|| tfbedno.getText().equals("")
						|| tfpatientno.equals("")) {
					JFrame fincreasenull = new JFrame();
					fincreasenull.setSize(100, 100);
					fincreasenull.setLocation(100, 20);
					fincreasenull.add(new Label("文本框不能为空！！"));
					fincreasenull.setVisible(true);
				} else {
					InsertDB(tfbedno.getText(),tfroomno.getText(),
							tfpatientno.getText());

					dispose();
					//SumMenu menu = new SumMenu();
					//menu.setOperate(op);
					
				}
			}

		});

		// 取消 按钮按下发生事件
		increaseclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}

		});

	}

	// 获取操作数据库对象
	public void setOperate(Operate op) {
		this.op = op;
	}

	// 插入数据库
	public void InsertDB(String BedId, String HosId, String PaId) {
		op.Insert("insert into BedInfo values(\"" + BedId + "\",\"" + HosId
				+ "\",\"" + PaId + "\");");

	}
}
