package BedInfo;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connect.Operate;

//根据病床号查询 的窗体
class BednoSelect extends JFrame {

	Box bednoBox;
	Box bednotext;
	Box bednoboxbase; // 使用 盒式布局 的容器

	JTextField tfbedno = new JTextField(10); // 构造一个具有10列的新的空文本框

	JButton bbednocertain = new JButton("确定"); // 创建“确定”按钮
	JButton bbednoclear = new JButton("取消"); // 创建“取消”按钮
	JFrame fbedno = new JFrame("病床号查询"); // 创建 “病床号查询” 界面
	JPanel pbedno = new JPanel(); // 创建面板

	// 数据库操作对象准备
	private Operate op = null;


	public void Init() {

		fbedno.setBounds(100, 100, 310, 260); // 界面设置大小
		fbedno.add(pbedno); // 界面 套入面板
		bednoBox = Box.createVerticalBox(); // 表示 盒式布局 的列式 布局 的 容器
		bednoBox.add(new Label("病床号:")); // 容器加入 病房号 按钮
		bednoBox.add(Box.createVerticalStrut(16)); // 创建高度为16的不可见组件
		bednoBox.add(bbednocertain); // 容器加入 按钮
		bednotext = Box.createVerticalBox(); // 使用 列式布局 容器
		bednotext.add(tfbedno); // 容器加入 该文本框
		bednotext.add(Box.createVerticalStrut(16)); // 创建高度为16的不可见组件
		bednotext.add(bbednoclear); // 容器加入 “取消按钮”
		bednoboxbase = Box.createHorizontalBox(); // 使用 盒式布局 的容器
		bednoboxbase.add(bednoBox); // 该容器中加入 以上 容器
		bednoboxbase.add(Box.createHorizontalStrut(16)); // 创建宽度为 16 的不可见组件
		bednoboxbase.add(bednotext); // 加入以上容器
		pbedno.add(bednoboxbase); // 面板中加入总容器
		fbedno.setVisible(true); // 可见
		fbedno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 按确定按钮 发生事件
		bbednocertain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfbedno.getText().equals("")) {
					JFrame fbednonull = new JFrame();
					fbednonull.setSize(100, 100);
					fbednonull.setLocation(100, 20);
					fbednonull.add(new Label("文本框不能为空！！"));
					fbednonull.setVisible(true);
				} else {
					fbedno.dispose();
					InquryResult qury = new InquryResult();
					qury.setOperate(op);
					qury.setTableFlag("BedId");
					qury.setEditText(tfbedno.getText());
					qury.Inint();
				}
			}
		});

		bbednoclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fbedno.dispose();
			}
		});

	}


	// 获取操作对象
	public void setOperate(Operate op) {
		this.op = op;
	}

}
