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

//删除床位 窗体
class Drop extends JFrame {

	Box dropBox;
	Box droptext;
	Box dropboxbase; // 使用 盒式布局 的容器

	JTextField tfroomno = new JTextField(10); // 构造一个具有10列的新的空文本框
	JTextField tfbedno = new JTextField(10); // 构造一个具有10列的新的空文本框
	JTextField tfpatientno = new JTextField(10); // 构造一个具有10列的新的空文本框

	JButton dropcertain = new JButton("确定"); // 创建“确定”按钮
	JButton dropclear = new JButton("取消"); // 创建“取消”按钮

	JPanel pdrop = new JPanel(); // 创建面板

	// 数据准备
	private Operate op = null;

	private Object[][] obj = null; // 解压容器
	private int rowCount = 0; // 获取行
	private int columnCount = 0; // 获取列
	private Object[] data = null;

	public Drop() {
		setTitle("删除床位");
		add(pdrop); // 界面 套入面板
		setBounds(100, 100, 310, 260); // 界面设置大小

		dropBox = Box.createVerticalBox(); // 表示 盒式布局 的列式 布局 的 容器

		dropBox.add(new Label("病房号:")); // 容器加入 病房号 按钮
		dropBox.add(Box.createVerticalStrut(8));// 创建高度为16的不可见组件
		dropBox.add(new Label("病床号:")); // 容器加入 病床号 标签
		dropBox.add(Box.createVerticalStrut(8));// 创建高度为16的不可见组件
		dropBox.add(new Label("病人号:")); // 容器加入 病床号 标签
		dropBox.add(Box.createVerticalStrut(8)); // 创建高度为16的不可见组件
		dropBox.add(dropcertain); // 容器加入 按钮

		droptext = Box.createVerticalBox(); // 使用 列式布局 容器

		droptext.add(tfroomno); // 容器加入 该文本框
		droptext.add(Box.createVerticalStrut(16));
		droptext.add(tfbedno); // 容器加入 该文本框
		droptext.add(Box.createVerticalStrut(16));
		droptext.add(tfpatientno);
		droptext.add(Box.createVerticalStrut(16));
		droptext.add(dropclear); // 容器加入 “取消按钮”

		dropboxbase = Box.createHorizontalBox(); // 使用 盒式布局 的容器

		dropboxbase.add(dropBox); // 该容器中加入 以上 容器
		dropboxbase.add(Box.createHorizontalStrut(16)); // 创建宽度为 16 的不可见组件

		dropboxbase.add(droptext);// 加入以上容器
		pdrop.add(dropboxbase); // 面板中加入总容器

		setVisible(true);// 可见
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 取消 按钮按下发生事件
		dropclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // 取消 事件 ：清楚文本框 ，关闭“病房号查询”界面
			}
		});

		// 确定 按钮按下发生事件
		dropcertain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfroomno.getText().equals("")
						|| tfbedno.getText().equals("")
						|| tfpatientno.getText().equals("")) {
					JFrame fdropnull = new JFrame();
					fdropnull.setSize(100, 100);
					fdropnull.setLocation(100, 20);
					fdropnull.add(new Label("文本框不能为空！！"));
					fdropnull.setVisible(true);
				} else {
					DeleteData(tfbedno.getText(),tfroomno.getText(),tfpatientno.getText());
					dispose();
				}
			}
		});
	}

	// 删除数据
	public void DeleteData(String BedId, String HosId, String PaId) {
		op.Delete("delete from BedInfo where BedId = '" + BedId
				+ "' and HosId = '" + HosId + "' and PaId = '" + PaId + "';");
	}

	// 获取数据
	public void setObject(Object[][] obj) {
		this.obj = obj;
	}

	// 获取操作对象
	public void setOperate(Operate op) {
		this.op = op;
	}

}
