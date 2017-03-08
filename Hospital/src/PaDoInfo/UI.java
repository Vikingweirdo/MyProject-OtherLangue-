package PaDoInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Connect.Operate;
import HouseMenu.SearchResult;

public class UI {
	public static JFrame jframe;
	JMenuBar jmenubar;
	JMenu inquiryjmenu;
	JButton retbutton;
	JMenu alterjmenu;
	JMenuItem casemenuItem;
	JMenuItem bedmenuItem;
	JMenuItem idcardmenuItem;
	JMenuItem depmenuItem;
	DefaultTableModel dtable;
	JTable table;
	JScrollPane jscr;
	JComboBox sexList;

	private Operate op = null; // 数据库操作对象
	private Object[][] obj = null; // 解压容器
	private int rowCount = 0; // 获取行
	private int columnCount = 0; // 获取列
	private Object[] data = null;

	// 行坐标
	private int number;
	private int point;// 获取坐标
	
	
	
	private String columName = null;	//获取当前表头
	private String newData = null;		//新数据
	private String oldData = null;		//旧数据
	private int pointX;		//确定修改坐标
	private int pointY;		
	private String finalName = null;	//转换数据库表设置属性
	private String keyName = null;		//主码值
	

	public void Init() {
		jframe = new JFrame("Patient");// 创建窗体
		jmenubar = new JMenuBar();// 创建菜单栏
		inquiryjmenu = new JMenu("查询病人信息");// 创建菜单
		alterjmenu = new JMenu("修改病人信息");
		
		JMenuItem Upitem = new JMenuItem("更新列表");
		alterjmenu.add(Upitem);
		
		
		
		
		
		
		
		
		
		
		
		retbutton = new JButton("返回");// 创建返回按钮
		casemenuItem = new JMenuItem("根据病历号查询");// 创建菜单项
		bedmenuItem = new JMenuItem("根据床位号查询");
		idcardmenuItem = new JMenuItem("根据身份证号查询");
		depmenuItem = new JMenuItem("根据科室查询");
		inquiryjmenu.add(casemenuItem);// 将菜单项加入菜单中
		inquiryjmenu.add(bedmenuItem);
		inquiryjmenu.add(idcardmenuItem);
		inquiryjmenu.add(depmenuItem);
		jmenubar.add(retbutton);// 将按钮加入菜单栏中
		jmenubar.add(inquiryjmenu);// 将菜单加入菜单栏中
		jmenubar.add(alterjmenu);

		Object[] columnNames = { "病历号", "姓名", "性别", "年龄", "联系方式", "家庭住址",
				"身份证号", "入院时间", "出院时间" };
		dtable = new DefaultTableModel(columnNames, 0);
		table = new JTable(dtable);// 创建表格
		jscr = new JScrollPane(table);// 加入滚动条
		sexList = new JComboBox();// 定义下拉列表框
		sexList.addItem("男");// 增加下拉选项
		sexList.addItem("女");
		table.getColumnModel().getColumn(2)
				.setCellEditor(new DefaultCellEditor(sexList));

		jframe.setJMenuBar(jmenubar);// 将菜单添加到窗体上
		jframe.add(jscr, BorderLayout.CENTER);// 将表格加入到 窗体
		jframe.setLocation(900, 200);
		jframe.setSize(1000, 700);// 定义窗体大小
		jframe.setVisible(true);// 显示窗体
		// 录入数据

		LoadData();

		data = new Object[columnCount];// 加载数据容器

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
				jframe.dispose();
			}

		});

		// 给"根据病历号查询"菜单项加入事件监听
		casemenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String titles = "病历号";
				Envidence en = new Envidence();
				en.setOperate(op);
				en.Init(titles);
				// new Envidence().envidenceShow(titles);// 调用查询依据显示函数
			}

		});

		// 给"根据床位号查询"菜单项加入事件监听
		bedmenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String titles = "床位号";
				Envidence en = new Envidence();
				en.setOperate(op);
				en.Init(titles);
				// new Envidence().envidenceShow(titles);
			}
		});

		// 给"根据身份证号查询"菜单项加入事件监听
		idcardmenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String titles = "身份证号";
				Envidence en = new Envidence();
				en.setOperate(op);
				en.Init(titles);
				// new Envidence().envidenceShow(titles);
			}
		});

		// 给"根据科室查询"菜单项加入事件监听
		depmenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String titles = "科室";
				Envidence en = new Envidence();
				en.setOperate(op);
				en.Init(titles);
				// new Envidence().envidenceShow(titles);
			}
		});

		// 更新表监听
		Upitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("哈哈");
				newData = table.getValueAt(pointX, pointY).toString();
				if (columName.equals("病历号")) {
					finalName = "PaID";
					Update();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				} else if (columName.equals("姓名")) {
					finalName = "PaName";
					keyName = table.getValueAt(pointX, 0).toString();
					System.out.println(keyName);
					System.out.println(newData);
					UpdateNoKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				} else if (columName.equals("联系方式")) {
					finalName = "Pacontact";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				}else if (columName.equals("家庭住址")) {
					finalName = "PaAdr";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				}else if (columName.equals("身份证号")) {
					finalName = "PapNum";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				}else if (columName.equals("入院时间")) {
					finalName = "DataIn";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				}else if (columName.equals("出院时间")) {
					finalName = "DataOut";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				}else if (columName.equals("年龄")) {
					finalName = "PaAge";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateAgeKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				}

			}
		});

		// 给表格加入鼠标的事件监
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
					JPopupMenu menu = new JPopupMenu();// 创建弹出菜单
					JMenuItem addmenuItem = new JMenuItem("     增加       ");// 创建菜单项
					JMenuItem deletemenuItem = new JMenuItem("     删除        ");
					menu.add(addmenuItem);// 将"增加"菜单项加入弹出菜单
					menu.add(deletemenuItem);// 将"删除"菜单项加入弹出菜单
					menu.show(table, e.getX(), e.getY());// 在坐标想（x，y）处弹出显示菜单

					number = table.getSelectedRows().length; // 取得用户所选行的行数
					point = table.getSelectedRow();

					// 给"增加"菜单项加入事件监听
					addmenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							AddPatient addPa = new AddPatient();
							addPa.setOperate(op);
							addPa.init();
						}
					});

					// 给"删除"菜单项加入事件监听
					deletemenuItem.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							DeleteData(table.getValueAt(point, 0).toString());
							DeleteRelaData(table.getValueAt(point, 0)
									.toString());
							dtable.removeRow(point); // 删除所选行的医生信息;
						}
					});
				}
			}
		});
		
		
		
	}

	// 更新数据
	public void Update() {
		op.Update("update patient set " + finalName + " = '" + newData
				+ "' where " + finalName + " = '" + oldData + "' ;");
	}

	// 更新数据
	public void UpdateNoKey() {
		op.Update("update patient set " + finalName + " = '" + newData
				+ "' where PaId = '" + keyName + "' ;");
	}
	
	// 更新数据
	public void UpdateAgeKey() {
		op.Update("update patient set " + finalName + " = " + newData
				+ " where PaId = '" + keyName + "' ;");
	}
	
	// 删除数据
	public void DeleteData(String point) {
		op.Delete("delete from patient where PaId = '" + point + "';");
	}

	// 删除数据
	public void DeleteRelaData(String point) {
		op.Delete("delete from relapado where PaId = '" + point + "';");
	}

	// 解压数据
	public void LoadData() {
		ResultSet result = null;
		op.Select("select * from patient;"); // 使用sql语句
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
			this.obj[0][7] = result.getString(8);
			this.obj[0][8] = result.getString(9);

			while (result.next()) {
				this.obj[i][0] = result.getString(1);
				this.obj[i][1] = result.getString(2);
				this.obj[i][2] = result.getString(3);
				this.obj[i][3] = result.getString(4);
				this.obj[i][4] = result.getString(5);
				this.obj[i][5] = result.getString(6);
				this.obj[i][6] = result.getString(7);
				this.obj[i][7] = result.getString(8);
				this.obj[i][8] = result.getString(9);
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

class Envidence {
	public JFrame frame1 = null;
	private Operate op = null;

	public void Init(final String s) {
		frame1 = new JFrame("根据" + s + "查询");// 创建窗体
		frame1.setLayout(null);// 取消窗体布局
		class Demo {// 创建一个类,目地是决定标签的内容
			public String labeldemo() {
				String labelcontent = "请输入" + s;
				return labelcontent;
			}
		}
		JLabel jlabel = new JLabel(new Demo().labeldemo(), JLabel.CENTER);// 创建标签并使标签内容居中
		final JLabel label = new JLabel();// 创建一个空内容
		final JTextField text = new JTextField(null);// 创建文本框
		JButton searchbutton = new JButton("查询");// 创建查询按钮
		jlabel.setBounds(80, 100, 300, 80);// 设置标签的位置和大小
		label.setBounds(285, 380, 200, 50);
		text.setBounds(400, 125, 170, 35);// 设置文本框的位置和大小
		searchbutton.setBounds(300, 230, 100, 50);// 设置按钮的位置和大小
		Font font1 = new Font("serief", Font.BOLD, 30);// 设置字体的名称，样式和大小
		Font font2 = new Font("serief", Font.BOLD, 20);
		jlabel.setFont(font1);// 改变标签的字体
		label.setFont(font1);
		searchbutton.setFont(font2);// 改变按钮的字体
		frame1.add(jlabel);// 向窗体添加标签
		frame1.add(label);
		frame1.add(text);// 向窗体添加文本框
		frame1.add(searchbutton);// 向窗体添加按钮
		text.setEditable(true);
		String string = text.getText();
		System.out.println(string);

		frame1.setLocation(900, 200);
		frame1.setSize(700, 600);// 定义窗体大小
		frame1.setVisible(true);// 显示窗体

		// 给按钮加入事件监听
		searchbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (text.getText().isEmpty()) {// 判断文本框的内容是否为空
					label.setText("输入错误"); // 设置标签内容

				} else {
					Inquiry qury = new Inquiry();// 弹出另一个界面显示所要查询的界面
					qury.setOperate(op);

					if ("病历号".equals(s)) {
						qury.setEditText(text.getText());
						qury.setTableFlag("PaId");
					} else if ("床位号".equals(s)) {
						qury.setEditText(text.getText());
						qury.setTableFlag("BedID");
					} else if ("身份证号".equals(s)) {
						System.out.println(s);
						qury.setEditText(text.getText());
						qury.setTableFlag("papnum");
					} else if ("科室".equals(s)) {// 科室
						qury.setEditText(text.getText());
						qury.setTableFlag("dodeparment");
					}
					System.out.println(op + "123456789");
					qury.setS(s);
					qury.Init();
					frame1.dispose();// 关闭并销毁窗体frame1
				}
			}
		});
	}

	// 获取操作对象
	public void setOperate(Operate op) {
		this.op = op;
	}
}

class Inquiry extends WindowAdapter {
	public JFrame frame2 = null;
	private String s = null;

	private Operate op = null; // 数据库操作对象
	private Object[][] obj = null; // 解压容器
	private int rowCount = 0; // 获取行
	private int columnCount = 0; // 获取列
	private Object[] data = null;

	private String editext = null; // 查询条件
	private String tableFlag = null; // 表的查询条件s

	public void Init() {
		frame2 = new JFrame("根据" + s + "查询的结果");// 创建窗体
		Object[] columnNames = { "病历号", "姓名", "性别", "年龄", "身份证号", "家庭住址",
				"联系方式", "主治医生", "入院时间", "出院时间" };
		DefaultTableModel dtable = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(dtable);// 创建表格
		JScrollPane jscr = new JScrollPane(table);// 加入滚动条
		JComboBox sexList = new JComboBox();// 定义下拉列表框
		sexList.addItem("男");// 增加下拉选项
		sexList.addItem("女");
		table.getColumnModel().getColumn(2)
				.setCellEditor(new DefaultCellEditor(sexList));
		System.out.println(s);

		if ("病历号".equals(s)) {
			LoadData();
		} else if ("床位号".equals(s)) {
			LoadDataBedId();
		} else if ("身份证号".equals(s)) {
			LoadData();
		} else if ("科室".equals(s)) {
			LoadDeparData();
		}
		/**
		 * LoadDataBedId();
		 */
		/*
		 * LoadData(); System.out.println(editext);
		 * System.out.println(tableFlag);
		 */
		data = new Object[columnCount];// 加载数据容器

		for (int x = 0; x <= obj.length - 1; x++) {

			for (int y = 0; y < obj[x].length; y++) {
				System.out.println(obj[x][y]);
				data[y] = obj[x][y];
			}
			dtable.addRow(data);
		}

		frame2.add(jscr, BorderLayout.CENTER);// 将表格加入到 窗体
		frame2.setLocation(900, 200);
		frame2.setSize(1000, 700);// 定义窗体大小
		frame2.setVisible(true);// 显示窗体
	}

	public void setS(String s) {
		this.s = s;
	}

	// 解压数据
	public void LoadData() {
		ResultSet result = null;
		op.Select("select Patient.Paid,paname,pasex,paage,pacontact,paadr,papnum,doid,datain,dataout from patient,relapado where patient.Paid = relapado.Paid and Patient."
				+ tableFlag + " = '" + editext + "';"); // 使用sql语句
		result = op.getResult(); // 获取查询的结果集
		// 装载数据flag
		int i = 1;

		try {
			result.last();// 移动到最后一行
			rowCount = result.getRow(); // 获取行数
			System.out.println(rowCount + "------行数");
			columnCount = result.getMetaData().getColumnCount();

			this.obj = new Object[rowCount][columnCount];// 实例化数据
			// 获取列数
			if (rowCount == 0) {
				return;
			}

			result.first(); // 移动到第一行
			this.obj[0][0] = result.getString(1);
			this.obj[0][1] = result.getString(2);
			this.obj[0][2] = result.getString(3);
			this.obj[0][3] = result.getString(4);
			this.obj[0][4] = result.getString(5);
			this.obj[0][5] = result.getString(6);
			this.obj[0][6] = result.getString(7);
			this.obj[0][7] = result.getString(8);
			this.obj[0][8] = result.getString(9);
			this.obj[0][9] = result.getString(10);

			while (result.next()) {
				this.obj[i][0] = result.getString(1);
				this.obj[i][1] = result.getString(2);
				this.obj[i][2] = result.getString(3);
				this.obj[i][3] = result.getString(4);
				this.obj[i][4] = result.getString(5);
				this.obj[i][5] = result.getString(6);
				this.obj[i][6] = result.getString(7);
				this.obj[i][7] = result.getString(8);
				this.obj[i][8] = result.getString(9);
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

	// 解压数据
	public void LoadDeparData() {
		ResultSet result = null;
		op.Select("select Patient.Paid,paname,pasex,paage,pacontact,paadr,papnum,doid,datain,dataout from patient,relapado where patient.Paid = relapado.Paid and relapado.doid in (select doid from doctor where "
				+ tableFlag + " = '" + editext + "');"); // 使用sql语句
		result = op.getResult(); // 获取查询的结果集
		// 装载数据flag
		int i = 1;

		try {
			result.last();// 移动到最后一行
			rowCount = result.getRow(); // 获取行数
			System.out.println(rowCount + "------行数");
			columnCount = result.getMetaData().getColumnCount();

			this.obj = new Object[rowCount][columnCount];// 实例化数据
			// 获取列数
			if (rowCount == 0) {
				return;
			}

			result.first(); // 移动到第一行
			this.obj[0][0] = result.getString(1);
			this.obj[0][1] = result.getString(2);
			this.obj[0][2] = result.getString(3);
			this.obj[0][3] = result.getString(4);
			this.obj[0][4] = result.getString(5);
			this.obj[0][5] = result.getString(6);
			this.obj[0][6] = result.getString(7);
			this.obj[0][7] = result.getString(8);
			this.obj[0][8] = result.getString(9);
			this.obj[0][9] = result.getString(10);

			while (result.next()) {
				this.obj[i][0] = result.getString(1);
				this.obj[i][1] = result.getString(2);
				this.obj[i][2] = result.getString(3);
				this.obj[i][3] = result.getString(4);
				this.obj[i][4] = result.getString(5);
				this.obj[i][5] = result.getString(6);
				this.obj[i][6] = result.getString(7);
				this.obj[i][7] = result.getString(8);
				this.obj[i][8] = result.getString(9);
				this.obj[i][9] = result.getString(10);
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

	// 解压数据
	public void LoadDataBedId() {
		ResultSet result = null;
		op.Select("select Patient.Paid,paname,pasex,paage,pacontact,paadr,papnum,doid,datain,dataout from patient,relapado,bedinfo where patient.Paid = bedinfo.Paid and patient.Paid = relapado.Paid and BedInfo."
				+ tableFlag + "= '" + editext + "';"); // 使用sql语句
		result = op.getResult(); // 获取查询的结果集
		// 装载数据flag
		int i = 1;
		try {
			result.last();// 移动到最后一行
			rowCount = result.getRow(); // 获取行数

			// 获取列数
			columnCount = result.getMetaData().getColumnCount();

			if (result == null) {
				return;
			}

			this.obj = new Object[rowCount][columnCount];// 实例化数据
			result.first(); // 移动到第一行
			this.obj[0][0] = result.getString(1);
			this.obj[0][1] = result.getString(2);
			this.obj[0][2] = result.getString(3);
			this.obj[0][3] = result.getString(4);
			this.obj[0][4] = result.getString(5);
			this.obj[0][5] = result.getString(6);
			this.obj[0][6] = result.getString(7);
			this.obj[0][7] = result.getString(8);
			this.obj[0][8] = result.getString(9);
			this.obj[0][9] = result.getString(10);

			while (result.next()) {
				this.obj[i][0] = result.getString(1);
				this.obj[i][1] = result.getString(2);
				this.obj[i][2] = result.getString(3);
				this.obj[i][3] = result.getString(4);
				this.obj[i][4] = result.getString(5);
				this.obj[i][5] = result.getString(6);
				this.obj[i][6] = result.getString(7);
				this.obj[i][7] = result.getString(8);
				this.obj[i][8] = result.getString(9);
				this.obj[i][9] = result.getString(10);
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

	// 获取查询的条件
	public void setEditText(String editext) {
		this.editext = editext;
	}

	// 获取表条件
	public void setTableFlag(String tableFlag) {
		this.tableFlag = tableFlag;
	}

	@Override
	public void windowClosed(WindowEvent e) {
		frame2.dispose();
		System.gc();
		// System.exit(0);
	}
}
