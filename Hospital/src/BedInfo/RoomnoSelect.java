package BedInfo;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connect.Operate;

//病房号查询 窗体
class RoomnoSelect extends JFrame {

	Box roomnoBox;
	Box roomnotext;
	Box roomnoboxbase; // 使用 盒式布局 的容器

	JTextField tfroomno = new JTextField(10); // 构造一个具有10列的新的空文本框

	JButton broomnocertain = new JButton("确定"); // 创建“确定”按钮
	JButton broomnoclear = new JButton("取消"); // 创建“取消”按钮
	JPanel proomno = new JPanel(); // 创建面板

	// 数据库操作对象准备
	private Operate op = null;

	public void Init() {

		setTitle("病房号查询");
		setBounds(100, 100, 310, 260); // 界面设置大小
		add(proomno); // 界面 套入面板
		roomnoBox = Box.createVerticalBox(); // 表示 盒式布局 的列式 布局 的 容器
		roomnoBox.add(new Label("病房号:")); // 容器加入 病房号 按钮
		roomnoBox.add(Box.createVerticalStrut(16)); // 创建高度为16的不可见组件
		roomnoBox.add(broomnocertain); // 容器加入 按钮
		roomnotext = Box.createVerticalBox(); // 使用 列式布局 容器
		roomnotext.add(tfroomno); // 容器加入 该文本框
		roomnotext.add(Box.createVerticalStrut(16)); // 创建高度为16的不可见组件
		roomnotext.add(broomnoclear); // 容器加入 “取消按钮”
		roomnoboxbase = Box.createHorizontalBox(); // 使用 盒式布局 的容器
		roomnoboxbase.add(roomnoBox); // 该容器中加入 以上 容器
		roomnoboxbase.add(Box.createHorizontalStrut(16)); // 创建宽度为 16 的不可见组件
		roomnoboxbase.add(roomnotext); // 加入以上容器
		proomno.add(roomnoboxbase); // 面板中加入总容器
		setVisible(true); // 可见
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 点击确定按钮发生事件
		broomnocertain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (tfroomno.getText().equals("")) {
					JFrame froomonull = new JFrame();
					froomonull.setSize(100, 100);
					froomonull.setLocation(100, 20);
					froomonull.add(new Label("文本框不能为空！！"));
					froomonull.setVisible(true);
				} else {
					dispose();
					InquryResult qury = new InquryResult();
					qury.setOperate(op);
					qury.setTableFlag("HosId");
					qury.setEditText(tfroomno.getText());
					qury.Inint();
				}
			}
		});

		// 取消 按钮 按下 发生事件
		broomnoclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}

	// 获取操作对象
	public void setOperate(Operate op) {
		this.op = op;
	}

}
