package BedInfo;

//用的时候可以直接 new SumMenu(str)  str 表示String 的二维数组 // 具体录入数据操作在133行的for循环语句中

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connect.Operate;
import HouseMenu.SearchResult;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

//主菜单界面
public class SumMenu extends JFrame {

	JMenu mmchange = new JMenu("修改"); // 创建"修改"菜单
	JMenuBar mmbtitle = new JMenuBar(); // 创建菜单栏
	JMenu mmoperate = new JMenu("操作"); // 创建 操作 菜单
	JMenuItem miopselect = new JMenuItem("查询"); // 创建 查询 子菜单项
	JButton mbfreshen = new JButton("刷新"); // 创建“刷新”按钮
	JButton mbexit = new JButton("退出");
	JMenuItem mmincrase = new JMenuItem("增加床位"); // 创建 ‘增加床位’ 子菜单项
	JMenuItem mmdrop = new JMenuItem("删除床位"); // 创建 ‘删除床位’ 子菜单项

	Object[] bedcolumn = { "病床号", "病房号", "病人号", "科室" }; // 创建 表格 各项目 属性名称
	DefaultTableModel bedtable = new DefaultTableModel(bedcolumn, 0);
	JTable sbedtable = new JTable(bedtable);
	String[][] pobject = new String[1000][4];
	JScrollPane jbed = null;
	JPanel panel = new JPanel(); // 创建面板

	// 数据加载准备
	private Operate op = null; // 数据库操作对象
	private Object[][] obj = null; // 解压容器
	private int rowCount = 0; // 获取行
	private int columnCount = 0; // 获取列
	private Object[] data = null;

	private String columName = null; // 获取当前表头
	private String newData = null; // 新数据
	private String oldData = null; // 旧数据
	private int pointX; // 确定修改坐标
	private int pointY;
	private String finalName = null; // 转换数据库表设置属性
	private String keyName = null; // 主码值
	private String otherKey = null;// 第二个主码值

	public void Init() {
		setTitle("病床管理"); // 设置的主标题为“病床管理”
		add(panel); // 将面板增加到窗口上
		setSize(630, 670); // 设置窗口初始大小
		setLocation(900, 300);
		setLayout(null); // 无布局设置

		// 查询菜单 按下 发生事件
		miopselect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectAction selectaction = new SelectAction();
				selectaction.setOperate(op);
				selectaction.Init();
			}
		});

		// 增加床位 菜单 按下 发生事件
		mmincrase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Increase increase = new Increase();
				increase.setOperate(op);

			}
		});

		// 删除床位 菜单 按下 发生事件
		mmdrop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Drop drop = new Drop();
				drop.setOperate(op);
			}
		});

		// 按钮按下发生事件
		mbfreshen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				SumMenu summenu = new SumMenu();
				summenu.setOperate(op);
				summenu.Init();

			}

		});

		// 退出 按钮 按下 发生事件
		mbexit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		// 菜单栏中增加组件
		mmoperate.add(miopselect); // 在 操作主菜单中加入 查询 子菜单
		mmbtitle.add(mmoperate); // 将操作主菜单加入到菜单栏中
		mmchange.add(mmincrase); // 在 修改 主菜单加入 ‘增加床位’ 主菜单
		mmchange.addSeparator(); // 将分隔符添加到该操作末尾
		mmchange.add(mmdrop); // 在 修改 主菜单加入 ‘删除床位’ 主菜单
		mmbtitle.add(mmchange); // 将 修改 主菜单加入到菜单栏中
		mmbtitle.add(mbfreshen); // 增加‘刷新’按钮到菜单栏中（此按钮刷新修改之后或查询之后重新排版的结果）
		mmbtitle.add(mbexit);
		setJMenuBar(mmbtitle); // 主窗体中放置该菜单栏

		bedtable = new DefaultTableModel(bedcolumn, 0);

		// 加载所需要的数据
		/*
		 * for (int i = 0; i < str.length; i++) { bedtable.addRow(str[i]); }
		 */
		LoadData();

		data = new Object[columnCount];// 加载数据容器

		for (int x = 0; x <= obj.length - 1; x++) {

			for (int y = 0; y < obj[x].length; y++) {
				System.out.println(obj[x][y]);
				data[y] = obj[x][y];
			}
			bedtable.addRow(data);
		}

		sbedtable = new JTable(bedtable);
		jbed = new JScrollPane(sbedtable); // 往表格中加入滚动条
		jbed.setSize(600, 600); // 设置表格大小
		add(jbed); // 窗体中加入表格

		// 鼠标监听
		sbedtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int c = e.getButton();
				if (c == MouseEvent.BUTTON1) {
					pointX = sbedtable.getSelectedRow();
					pointY = sbedtable.getSelectedColumn();
					System.out.println(pointX + "," + pointY);
					oldData = sbedtable.getValueAt(pointX, pointY).toString();
					columName = bedtable.getColumnName(pointY);
					System.out.println(oldData + "就数据");
					System.out.println(columName + "le");
				}
			}
		});

		// 修改菜单
		JMenu update = new JMenu("更新");
		JMenuItem updateItem = new JMenuItem("更新表");
		update.add(updateItem);

		// 更新表监听
		updateItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				newData = sbedtable.getValueAt(pointX, pointY).toString();

				System.out.println(newData);
				//System.out.println(otherKey);
				if (columName.equals("病床号")) {
					finalName = "BedId";
					otherKey = sbedtable.getValueAt(pointX, 1).toString();
					Update();

					dispose();
					SumMenu jf = new SumMenu();
					jf.setOperate(op);
					jf.Init();
				} else if (columName.equals("病房号")) {
					finalName = "HosId";
					keyName = sbedtable.getValueAt(pointX, 0).toString();
					System.out.println(keyName);
					System.out.println(oldData);
					// UpdateNoKey();
					UpdateOtherKey();

					dispose();
					SumMenu jf = new SumMenu();
					jf.setOperate(op);
					jf.Init();
				} else if (columName.equals("病人号")) {
					finalName = "PaId";
					keyName = sbedtable.getValueAt(pointX, 0).toString();
					otherKey = sbedtable.getValueAt(pointX, 1).toString();
					UpdateNoKey();
					System.out.println(keyName);
					System.out.println(otherKey);
					dispose();
					SumMenu jf = new SumMenu();
					jf.setOperate(op);
					jf.Init();
				}

			}
		});

		mmbtitle.add(update);

		setVisible(true); // 主窗体可见
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭时使用 system.exit(0)方式
														// 关闭
	}

	// 更新数据
	public void Update() { // BedInfo
		op.Update("update BedInfo set " + finalName + " = '" + newData
				+ "' where BedId = '" + oldData + "' and HosId = '" + otherKey
				+ "' ;");
	}

	// 更新数据
	public void UpdateOtherKey() { // BedInfo
		op.Update("update BedInfo set " + finalName + " = '" + newData
				+ "' where BedId = '" + keyName + "' and HosId = '" + oldData
				+ "' ;");
	}

	// 更新数据
	public void UpdateNoKey() {
		op.Update("update BedInfo set " + finalName + " = '" + newData
				+ "' where BedId = '" + keyName + "' and HosId = '" + otherKey
				+ "' ;");
	}

	// 解压数据
	public void LoadData() {
		ResultSet result = null;
		op.Select("select bedID,bedInfo.HosId,PaID,dodeparment from bedInfo,house where house.hosid = bedInfo.hosid;"); // 使用sql语句
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
