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

//病房号 和 病床号 查询窗体
class RoomnoAndBednoSelect extends JFrame {

	JTextField tfroomno_ = new JTextField(10); // 文本框 列数 为10
	JTextField tfbedno_ = new JTextField(10); // 文本框 列数 为10

	JButton broomno_bednocertain = new JButton("确定"); // 创建“确定”按钮
	JButton broomno_bednoclear = new JButton("取消"); // 创建“取消”按钮

	Box roomno_bednoBox;
	Box roomno_bednotext;
	Box roomno_bednoboxbase; // 使用 盒式布局 的容器

	JPanel proomno_bedno = new JPanel(); // 创建面板

	// 数据库操作对象准备
	private Operate op = null;

	private Object[][] obj = null; // 解压容器
	private int rowCount = 0; // 获取行
	private int columnCount = 0; // 获取列
	private Object[] data = null;


	public void Init() {

		setTitle("病床号+病床号  查询");
		add(proomno_bedno);
		setBounds(100, 100, 310, 260);

		roomno_bednoBox = Box.createVerticalBox(); // 表示 盒式布局 的列式 布局 的 容器

		roomno_bednoBox.add(new Label("病房号:")); // 容器加入 病房号 按钮
		roomno_bednoBox.add(Box.createVerticalStrut(8));// 创建高度为16的不可见组件
		roomno_bednoBox.add(new Label("病床号:")); // 容器加入 病床号 标签
		roomno_bednoBox.add(Box.createVerticalStrut(8)); // 创建高度为16的不可见组件
		roomno_bednoBox.add(broomno_bednocertain); // 容器加入 按钮
		roomno_bednotext = Box.createVerticalBox(); // 使用 列式布局 容器
		roomno_bednotext.add(tfroomno_); // 容器加入 该文本框
		roomno_bednotext.add(Box.createVerticalStrut(16));
		roomno_bednotext.add(Box.createVerticalStrut(16));
		roomno_bednotext.add(tfbedno_); // 容器加入 该文本框
		roomno_bednotext.add(Box.createVerticalStrut(16)); // 创建高度为16的不可见组件
		roomno_bednotext.add(broomno_bednoclear); // 容器加入 “取消按钮”
		roomno_bednoboxbase = Box.createHorizontalBox(); // 使用 盒式布局 的容器
		roomno_bednoboxbase.add(roomno_bednoBox); // 该容器中加入 以上 容器
		roomno_bednoboxbase.add(Box.createHorizontalStrut(16)); // 创建宽度为 16
																// 的不可见组件
		roomno_bednoboxbase.add(roomno_bednotext);// 加入以上容器
		proomno_bedno.add(roomno_bednoboxbase); // 面板中加入总容器
		setVisible(true);// 可见
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 按确定按钮发生事件
		broomno_bednocertain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfroomno_.getText().equals("")
						|| tfbedno_.getText().equals("")) {
					JFrame froomno_bednonull = new JFrame();
					froomno_bednonull.setSize(100, 100);
					froomno_bednonull.setLocation(100, 20);
					froomno_bednonull.add(new Label("文本框不能为空！！"));
					froomno_bednonull.setVisible(true);
				} else {
					dispose();
					
					LoadData(tfbedno_.getText(),tfroomno_.getText());
					
					data = new Object[columnCount];

					
					
					JFrame froomno_bedno = new JFrame("根据床号查询结果");
					Object[] room_nobednocolumn = { "病房号", "病床号", "病人号", "科室" }; // 创建
																					// 表格
																					// 各项目
																					// 属性名称
					DefaultTableModel roomno_bednobednotable = new DefaultTableModel(
							room_nobednocolumn, 0);
					
					if (rowCount != 0) {
						for (int x = 0; x <= obj.length - 1; x++) {

							for (int y = 0; y < obj[x].length; y++) {
								System.out.println(obj[x][y]);
								data[y] = obj[x][y];
							}
							roomno_bednobednotable.addRow(data);
						}
					}
					
					JTable sroomno_bednotable = new JTable(
							roomno_bednobednotable);
					JScrollPane jroomno_bedno = new JScrollPane(
							sroomno_bednotable); // 往表格中加入滚动条
					froomno_bedno.setSize(600, 600); // 设置窗口初始大小
					froomno_bedno.setLocation(500, 100);
					jroomno_bedno.setSize(600, 600);
					froomno_bedno.add(jroomno_bedno);
					froomno_bedno.setVisible(true); // 主窗体可见
					froomno_bedno
							.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 关闭时使用
																				// system.exit(0)方式
																				// 关闭
				}
			}
		});

		// 取消按钮按下发生事件
		broomno_bednoclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}

	// 解压数据
	public void LoadData(String editext , String tableFlag) {
		ResultSet result = null;
		// System.out.println(op + ".......");
		op.Select("select BedId,BedInfo.HosId,PaId,Dodeparment from BedInfo ,House where BedInfo.HosId = House.HosId and BedId = '"
				+editext+"' and BedInfo.HosId = '" +tableFlag+"';");
		
		/*select BedId,BedInfo.HosId,PaId,Dodeparment 
		from BedInfo ,House 
		where BedInfo.HosId = House.HosId and BedId = '01' and BedInfo.HosId = '#402';
		*/
		result = op.getResult(); // 获取查询的结果集
		// 装载数据flag
		int i = 1;
		try {
			result.last();// 移动到最后一行
			rowCount = result.getRow(); // 获取行数

			if (rowCount == 0) {
				return;
			}

			// 获取列数
			columnCount = result.getMetaData().getColumnCount();

			if (rowCount == 0) {
				return;
			}

			this.obj = new Object[rowCount][columnCount];// 实例化数据
			result.first(); // 移动到第一行
			this.obj[0][0] = result.getString(1);
			this.obj[0][1] = result.getString(2);
			this.obj[0][2] = result.getString(3);
			this.obj[0][3] = result.getString(4);

			while (result.next()) {
				this.obj[i][0] = result.getString(1);
				this.obj[i][1] = result.getString(2);
				this.obj[i][2] = result.getString(3);
				this.obj[i][3] = result.getString(4);
				/*
				 * System.out.println(this.obj[i][0]);
				 * System.out.println(this.obj[i][1]);
				 * System.out.println(this.obj[i][2]);
				 * System.out.println("=======  " + i);
				 */
				i++;
			}

			for (int x = 0; x <= obj.length - 1; x++) {
				for (int y = 0; y < obj[x].length; y++) {

					System.out.println(obj[x][y]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
