package HouseMenu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connect.Operate;

public class Result {
	JFrame jF;// 新建窗口，模板，表格，滚动条，下拉栏，按钮；
	JPanel jP;
	DefaultTableModel dTable;
	JTable jTable;
	JScrollPane jScr;
	JButton jB;

	private String editext = null; // 查询条件
	private String tableFlag = null;	//表的查询条件
	private Operate op = null;

	private Object[][] obj = null; // 解压容器
	private int rowCount = 0; // 获取行
	private int columnCount = 0; // 获取列
	private Object[] data = null;

	public void Init(final String s) {

		// 实例化窗口，模板，表格，滚动条，下拉栏，
		jF = new JFrame("根据查询" + s + "得到的结果");
		jP = new JPanel();
		Object[] searchResult = { "病房号", "房间类型", "科室" };
		dTable = new DefaultTableModel(searchResult, 0);
		jTable = new JTable(dTable);
		jScr = new JScrollPane(jTable);
		jB = new JButton("确定");

		LoadData();
		this.data = new Object[columnCount];
		
		if (rowCount != 0 ){
			for (int x = 0; x <= obj.length - 1; x++) {
	
				for (int y = 0; y < obj[x].length; y++) {
					System.out.println(obj[x][y]);
					data[y] = obj[x][y];
				}
				dTable.addRow(data);
			}
		}
		// 设置南北布局；
		jF.add(jScr, BorderLayout.CENTER);
		jF.add(jP, BorderLayout.SOUTH);

		// 确定按钮，模板，窗口等的大小；
		jB.setBounds(350, 250, 50, 50);
		jP.setSize(50, 50);
		jP.add(jB);
		jF.setLocation(900, 300);
		jF.setSize(400, 300);
		jF.setResizable(false);
		jF.setVisible(true);
		jF.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jF.setLayout(new BorderLayout());

		// “确定”按钮的事件监听；
		jB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dTable.addRow(new Object[]{});
				
				if ("删除病房".equals(s)){
					if(rowCount == 0){
						jF.dispose();
						return ;
					}
					DeleteData();	//删除数据
					jF.dispose();
					//刷新界面
					SearchResult.jF.dispose();
					SearchResult searchResult = new SearchResult();
					searchResult.setOperate(op);
					searchResult.Init();
					//System.out.println("shanchu");
				}else{
				
					jF.dispose();
				}
			}
		});
	}

	// 解压数据
	public void LoadData() {
		ResultSet result = null;
		op.Select("select * from house where " + tableFlag+" = '" + editext + "';"); // 使用sql语句
		result = op.getResult(); // 获取查询的结果集
		// 装载数据flag
		int i = 1;
		try {
			result.last();// 移动到最后一行
			rowCount = result.getRow(); // 获取行数

			// 获取列数
			columnCount = result.getMetaData().getColumnCount();
			
			if(rowCount == 0){
				return ;
			}
			
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
	
	//删除数据
	public void DeleteData(){
		op.Delete("delete from house where HosId = '" + editext +"';");
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
	//获取表条件
	public void setTableFlag(String tableFlag){
		this.tableFlag = tableFlag;
	}
}
