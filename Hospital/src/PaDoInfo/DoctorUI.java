package PaDoInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Connect.Operate;
import HouseMenu.SearchResult;

public class DoctorUI {
	public static JFrame jframe;
	JPanel jpanel;
	JLabel jlabel;
	JTextField text;
	JButton searchbutton;
	JButton retbutton;
	Font font;
	DefaultTableModel dtable;
	JTable table;
	JScrollPane jscr;
	JComboBox sexList;
	//行坐标
	private int number ;
	private int point ;//获取坐标

	// 解压数据准备
	private Operate op = null; // 数据库操作对象
	private Object[][] obj = null; // 解压容器
	private int rowCount = 0; // 获取行
	private int columnCount = 0; // 获取列
	private Object[] data = null;
	
	private String columName = null;	//获取当前表头
	private String newData = null;		//新数据
	private String oldData = null;		//旧数据
	private int pointX;		//确定修改坐标
	private int pointY;		
	private String finalName = null;	//转换数据库表设置属性
	private String keyName = null;		//主码值

	public void Init() {
		jframe = new JFrame("Doctor");// 创建窗体
		jframe.setLayout(null);// 取消窗体的页面布局
		jpanel = new JPanel();// 创建面板
		jpanel.setLayout(null);// 取消面板的页面布局
		jpanel.setBounds(0, 50, 320, 600);// 设置窗体的位置的大小
		jlabel = new JLabel("请输入职工号", JLabel.CENTER);// 创建标签
		text = new JTextField();// 创建文本框
		searchbutton = new JButton("查询");// 创建查询按钮
		retbutton = new JButton("返回");// 创建返回按钮
		font = new Font("", Font.BOLD, 30);
		jlabel.setBounds(50, 100, 200, 50);
		jlabel.setFont(font);
		text.setBounds(50, 190, 200, 50);
		searchbutton.setBounds(100, 300, 100, 50);
		retbutton.setBounds(100, 410, 100, 50);

		Object[] columnNames = { "职工号", "姓名", "性别", "年龄", "联系方式" ,"科室",  "职称"};
		dtable = new DefaultTableModel(columnNames, 0);
		table = new JTable(dtable);// 创建表格
		jscr = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// 加入滚动条
		jscr.setBounds(350, 120, 380, 450);
		sexList = new JComboBox();// 定义下拉列表框
		sexList.addItem("男");// 增加下拉选项
		sexList.addItem("女");
		table.getColumnModel().getColumn(2)
				.setCellEditor(new DefaultCellEditor(sexList));

		jframe.add(jpanel);// 将面板加入到窗体
		jframe.add(jscr);// 将表格加入到 窗体
		jpanel.add(jlabel);
		jpanel.add(text);
		jpanel.add(searchbutton);
		jpanel.add(retbutton);
		//刷新按钮
		JButton flush = new JButton("刷新");
		flush.setBounds(100, 500, 100, 50);
		jpanel.add(flush);
		
		
		jframe.setLocation(900, 300);
		jframe.setSize(800, 800);// 定义窗体大小
		jframe.setVisible(true);// 显示窗体
		
		// 录入数据
		LoadData();
		data = new Object[columnCount];//加载数据容器
		
		for (int x = 0; x <= obj.length - 1; x++) {

			for (int y = 0; y < obj[x].length; y++) {
				System.out.println(obj[x][y]);
				data[y] = obj[x][y];
			}
			dtable.addRow(data);
		}

		
		// 给返回按钮加入事件监听
		retbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jframe.dispose();// 返回菜单
			}

		});
		
		flush.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				newData = table.getValueAt(pointX, pointY).toString();
				if (columName.equals("职工号")) {
					finalName = "DoId";
					Update();

					jframe.dispose();// 返回菜单
					DoctorUI ui = new DoctorUI();
					ui.setOperate(op);
					ui.Init();
				} else if (columName.equals("姓名")) {
					finalName = "DoName";
					keyName = table.getValueAt(pointX, 0).toString();
					System.out.println(keyName);
					System.out.println(newData);
					UpdateNoKey();

					jframe.dispose();// 返回菜单
					DoctorUI ui = new DoctorUI();
					ui.setOperate(op);
					ui.Init();
				} else if (columName.equals("联系方式")) {
					finalName = "DoContact";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();// 返回菜单
					DoctorUI ui = new DoctorUI();
					ui.setOperate(op);
					ui.Init();
				}else if (columName.equals("科室")) {
						finalName = "Dodeparment";
						keyName = table.getValueAt(pointX, 0).toString();
						UpdateNoKey();

						jframe.dispose();// 返回菜单
						DoctorUI ui = new DoctorUI();
						ui.setOperate(op);
						ui.Init();
				}else if (columName.equals("职称")) {
					finalName = "DoTitle";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();// 返回菜单
					DoctorUI ui = new DoctorUI();
					ui.setOperate(op);
					ui.Init();
				}else if (columName.equals("年龄")) {
					finalName = "DoAge";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateAgeKey();

					jframe.dispose();// 返回菜单
					DoctorUI ui = new DoctorUI();
					ui.setOperate(op);
					ui.Init();
				}
			}
		});

		// 给查询按钮加入事件监听
		searchbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (text.getText().isEmpty()) {
					JFrame frame = new JFrame();
					JLabel label = new JLabel("输入错误", JLabel.CENTER);
					label.setFont(font);
					frame.add(label);
					frame.setLocation(900, 300);
					frame.setSize(500, 200);
					frame.setVisible(true);
				} else {
					
					DoctorResult result = new DoctorResult();
					result.setOperate(op);
					result.setEdit(text.getText());
					result.Init();
				}
			}

		});

		// 给表格加入鼠标的事件监听
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int c = e.getButton();
				if (c == MouseEvent.BUTTON1) {
					pointX = table.getSelectedRow();
					pointY = table.getSelectedColumn();
					System.out.println(pointX + "," + pointY);
					oldData = table.getValueAt(pointX, pointY).toString();
					columName = dtable.getColumnName(pointY);
					System.out.println(oldData + "就数据");
					System.out.println(columName + "le");
				}
				
				
				
				if (e.isMetaDown()) {
					JPopupMenu menu = new JPopupMenu();// 弹出菜单的实现
					JMenuItem addmenuItem = new JMenuItem("     add       ");// 创建菜单项
					JMenuItem deletemenuItem = new JMenuItem("     删除        ");
					menu.add(addmenuItem);// 将菜单项加入弹出菜单
					menu.add(deletemenuItem);
					menu.show(table, e.getX(), e.getY());// 在坐标（x，y）处弹出显示菜单
					
					number = table.getSelectedRows().length; // 取得用户所选行的行数
					point = table.getSelectedRow();
					
					System.out.println(point);
					// 给"增加"菜单项加入事件监听
					addmenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							
							///Object[] rowData = new Object[] { "123456" };
							//dtable.insertRow(table.getSelectedRow(), rowData);// 在选定行的上方加入数据
							AddDoctor addDo = new AddDoctor();
							addDo.setOperate(op);
							addDo.init();
						}
					});

					// 给"删除"菜单项加入事件监听
					deletemenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							
							//System.out.println(point);
							//System.out.println(table.getValueAt(point, 0));
							DeleteData(table.getValueAt(point, 0).toString());
							dtable.removeRow(point); // 删除所选行的医生信息;
							/*if (number > 1) {// 删除所选的多行医生信息
								for (int i = 0; i < number; i++) {
									dtable.removeRow(table.getSelectedRow());
								}
							}*/
						}
					});
				}
			}
		});

	}
	
	// 更新数据
	public void Update() {
		op.Update("update doctor set " + finalName + " = '" + newData
				+ "' where " + finalName + " = '" + oldData + "' ;");
	}

	// 更新数据
	public void UpdateNoKey() {
		op.Update("update doctor set " + finalName + " = '" + newData
				+ "' where DoId = '" + keyName + "' ;");
	}
	// 更新数据
	public void UpdateAgeKey() {
		op.Update("update doctor set " + finalName + " = " + newData
				+ " where DoId = '" + keyName + "' ;");
	}
	
	// 删除数据
	public void DeleteData(String point) {
		op.Delete("delete from doctor where DoId = '" + point + "';");
	}

	// 解压数据
	public void LoadData() {
		ResultSet result = null;
		op.Select("select * from doctor;"); // 使用sql语句
		result = op.getResult(); // 获取查询的结果集
		// 装载数据flag
		int i = 1;
		try {
			result.last();// 移动到最后一行
			rowCount = result.getRow(); // 获取行数

			// 获取列数
			columnCount = result.getMetaData().getColumnCount();

			this.obj = new Object[rowCount][columnCount];// 实例化数据
			result.first(); // 移动到第一行
			this.obj[0][0] = result.getString(1);
			this.obj[0][1] = result.getString(2);
			this.obj[0][2] = result.getString(3);
			this.obj[0][3] = result.getString(4);
			this.obj[0][4] = result.getString(5);
			this.obj[0][5] = result.getString(6);
			this.obj[0][6] = result.getString(7);

			while (result.next()) {
				this.obj[i][0] = result.getString(1);
				this.obj[i][1] = result.getString(2);
				this.obj[i][2] = result.getString(3);
				this.obj[i][3] = result.getString(4);
				this.obj[i][4] = result.getString(5);
				this.obj[i][5] = result.getString(6);
				this.obj[i][6] = result.getString(7);
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

	// 获取操作对象
	public void setOperate(Operate op) {
		this.op = op;
	}

	public Operate getOperate() {
		return this.op;
	}

	// 获取数据
	public Object[][] getObject() {
		return obj;
	}
}
