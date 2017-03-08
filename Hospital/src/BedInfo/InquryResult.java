package BedInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connect.Operate;

public class InquryResult extends JFrame {
	Object[] inquryresult = { "病房号", "病床号", "病人号", "科室" };
	Object[][] str = new Object[100][4];
	DefaultTableModel inqurytable = new DefaultTableModel(inquryresult, 0);
	JTable sinqurytable = new JTable(inqurytable);

	// 数据库操作对象准备
	private Operate op = null;

	private Object[][] obj = null; // 解压容器
	private int rowCount = 0; // 获取行
	private int columnCount = 0; // 获取列
	private Object[] data = null;

	// 获取查询条件
	private String editext = null; // 查询条件
	private String tableFlag = null; // 表的查询条件

	public void Inint() {
		setLocation(500, 80);
		setSize(630, 670);

		// 数据填充
		LoadData();
		this.data = new Object[columnCount];

		if (rowCount != 0) {
			for (int x = 0; x <= obj.length - 1; x++) {

				for (int y = 0; y < obj[x].length; y++) {
					System.out.println(obj[x][y]);
					data[y] = obj[x][y];
				}
				inqurytable.addRow(data);
			}
		}
		JScrollPane sresult = new JScrollPane(sinqurytable);
		add(sresult);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// 解压数据
	public void LoadData() {
		ResultSet result = null;
		//System.out.println(op + ".......");
		if(!tableFlag.equals("HosId")){
		op.Select("select BedId,BedInfo.HosId,PaId,Dodeparment from BedInfo ,House where BedInfo.HosId = House.HosId and "+tableFlag+" = '"
				+ editext + "';"); // 使用sql语句
		}else{
			op.Select("select BedId,BedInfo.HosId,PaId,Dodeparment from BedInfo ,House where BedInfo.HosId = House.HosId and BedInfo."+tableFlag+" = '"
					+ editext + "';");
		}
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

	// 获取查询的条件
	public void setEditText(String editext) {
		this.editext = editext;
	}

	// 获取操作对象
	public void setOperate(Operate op) {
		this.op = op;
	}

	// 获取表条件
	public void setTableFlag(String tableFlag) {
		this.tableFlag = tableFlag;
	}
}