package HouseMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Connect.Operate;

public class SearchResult extends JFrame /* implements ActionListener */{
	public static JFrame jF;
	DefaultTableModel dTm;
	JTable jT;
	JScrollPane jS;

	JMenuBar jMb;
	JMenu jMe0;
	JMenu jMe1;
	JMenu jMe2;
	JMenu jMe3;
	JMenuItem jM0;
	JMenuItem jM1;
	JMenuItem jM2;
	JMenuItem jM3;
	JMenuItem jM4;

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
		jF = new JFrame("病房管理系统");
		Object[] title = { "病房号", "病房类型", "科室" };// 设置病房属性；
		dTm = new DefaultTableModel(title, 0);// 建立表格；

		LoadData();

		data = new Object[columnCount];// 加载数据容器

		for (int x = 0; x <= obj.length - 1; x++) {

			for (int y = 0; y < obj[x].length; y++) {
				System.out.println(obj[x][y]);
				data[y] = obj[x][y];
			}
			dTm.addRow(data);
		}

		jT = new JTable(dTm);

		// 鼠标监听
		jT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int c = e.getButton();
				if (c == MouseEvent.BUTTON1) {
					pointX = jT.getSelectedRow();
					pointY = jT.getSelectedColumn();
					System.out.println(pointX + "," + pointY);
					oldData = jT.getValueAt(pointX, pointY).toString();
					columName = dTm.getColumnName(pointY);
					System.out.println(oldData + "就数据");
					System.out.println(columName + "le");
				}
			}
		});

		jS = new JScrollPane(jT);// 新建一个滚动条；

		jMb = new JMenuBar(); // 新建菜单栏；
		jMe0 = new JMenu("增加");// 增加菜单选项；
		jMe1 = new JMenu("查询");
		jMe2 = new JMenu("删除");
		jMe3 = new JMenu("返回");
		// 修改菜单
		JMenu update = new JMenu("更新");
		JMenuItem updateItem = new JMenuItem("更新表");
		update.add(updateItem);

		jM0 = new JMenuItem("查询科室");// 设置子菜单选项；
		jM1 = new JMenuItem("查询病房号");
		jM2 = new JMenuItem("增加新病房");
		jM3 = new JMenuItem("删除病房");
		jM4 = new JMenuItem("返回上一级菜单");
		jMe1.add(jM0);// 添加子菜单栏；
		jMe1.add(jM1);
		jMe0.add(jM2);
		jMe2.add(jM3);
		jMe3.add(jM4);
		jMb.add(jMe0);
		jMb.add(jMe1);
		jMb.add(jMe2);
		jMb.add(jMe3);

		jMb.add(update);

		jF.setJMenuBar(jMb);
		jF.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jF.add(jS, BorderLayout.CENTER);
		jF.setLocation(900, 300);
		jF.setSize(800, 600);
		jF.setVisible(true);

		// 更新表监听
		updateItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				newData = jT.getValueAt(pointX, pointY).toString();
				if (columName.equals("病房号")) {
					finalName = "HosId";
					Update();
					
					
					jF.dispose();
					SearchResult jf = new SearchResult();
					jf.setOperate(op);
					jf.Init();
				} else if (columName.equals("病房类型")) {
					finalName = "BedSum";
					keyName = jT.getValueAt(pointX, 0).toString();
					System.out.println(keyName);
					System.out.println(newData);
					UpdateNoKey();
					
					
					jF.dispose();
					SearchResult jf = new SearchResult();
					jf.setOperate(op);
					jf.Init();
				} else if (columName.equals("科室")) {
					finalName = "dodeparment";
					keyName = jT.getValueAt(pointX, 0).toString();
					UpdateNoKey();
					
					jF.dispose();
					SearchResult jf = new SearchResult();
					jf.setOperate(op);
					jf.Init();
				}

			}
		});

		// 子菜单“查询科室”的事件监听；
		jM0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "查询科室";
				Search search = new Search();
				search.setTableFlag("Dodeparment");
				search.setOperate(op);
				search.Init1(title);
			}
		});
		// 子菜单“查询病房号”的事件监听；
		jM1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "查询病房号";
				Search search = new Search();
				search.setTableFlag("HosID");
				search.setOperate(op);
				search.Init1(title);
			}
		});
		// 子菜单选项“增加新病房”的事件监听；
		jM2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dTm.addRow(new Object[] {});
				Increase increase = new Increase();
				increase.setOperate(op);
				increase.Init();
			}
		});
		// 子菜单“删除病房”的事件监听；
		jM3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "删除病房";
				Search search = new Search();
				search.setTableFlag("HosID");
				search.setOperate(op);
				search.Init1(title);
			}
		});
		// 子菜单“返回上一级菜单”的事件监听；
		jM4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jF.dispose();
			}
		});
	}

	// 解压数据
	public void LoadData() {
		ResultSet result = null;
		op.Select("select * from house;"); // 使用sql语句
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

			while (result.next()) {
				this.obj[i][0] = result.getString(1);
				this.obj[i][1] = result.getString(2);
				this.obj[i][2] = result.getString(3);
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

	// 更新数据
	public void Update() {
		op.Update("update house set " + finalName + " = '" + newData
				+ "' where " + finalName + " = '" + oldData + "' ;");
	}

	// 更新数据
	public void UpdateNoKey() {
		op.Update("update house set " + finalName + " = '" + newData
				+ "' where HosId = '" + keyName + "' ;");
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
