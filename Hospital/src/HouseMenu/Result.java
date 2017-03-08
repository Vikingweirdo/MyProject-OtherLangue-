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
	JFrame jF;// �½����ڣ�ģ�壬��񣬹�����������������ť��
	JPanel jP;
	DefaultTableModel dTable;
	JTable jTable;
	JScrollPane jScr;
	JButton jB;

	private String editext = null; // ��ѯ����
	private String tableFlag = null;	//��Ĳ�ѯ����
	private Operate op = null;

	private Object[][] obj = null; // ��ѹ����
	private int rowCount = 0; // ��ȡ��
	private int columnCount = 0; // ��ȡ��
	private Object[] data = null;

	public void Init(final String s) {

		// ʵ�������ڣ�ģ�壬��񣬹���������������
		jF = new JFrame("���ݲ�ѯ" + s + "�õ��Ľ��");
		jP = new JPanel();
		Object[] searchResult = { "������", "��������", "����" };
		dTable = new DefaultTableModel(searchResult, 0);
		jTable = new JTable(dTable);
		jScr = new JScrollPane(jTable);
		jB = new JButton("ȷ��");

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
		// �����ϱ����֣�
		jF.add(jScr, BorderLayout.CENTER);
		jF.add(jP, BorderLayout.SOUTH);

		// ȷ����ť��ģ�壬���ڵȵĴ�С��
		jB.setBounds(350, 250, 50, 50);
		jP.setSize(50, 50);
		jP.add(jB);
		jF.setLocation(900, 300);
		jF.setSize(400, 300);
		jF.setResizable(false);
		jF.setVisible(true);
		jF.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jF.setLayout(new BorderLayout());

		// ��ȷ������ť���¼�������
		jB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dTable.addRow(new Object[]{});
				
				if ("ɾ������".equals(s)){
					if(rowCount == 0){
						jF.dispose();
						return ;
					}
					DeleteData();	//ɾ������
					jF.dispose();
					//ˢ�½���
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

	// ��ѹ����
	public void LoadData() {
		ResultSet result = null;
		op.Select("select * from house where " + tableFlag+" = '" + editext + "';"); // ʹ��sql���
		result = op.getResult(); // ��ȡ��ѯ�Ľ����
		// װ������flag
		int i = 1;
		try {
			result.last();// �ƶ������һ��
			rowCount = result.getRow(); // ��ȡ����

			// ��ȡ����
			columnCount = result.getMetaData().getColumnCount();
			
			if(rowCount == 0){
				return ;
			}
			
			this.obj = new Object[rowCount][columnCount];// ʵ��������
			result.first(); // �ƶ�����һ��
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
	
	//ɾ������
	public void DeleteData(){
		op.Delete("delete from house where HosId = '" + editext +"';");
	}

	// ��ȡ����
	public void setObject(Object[][] obj) {
		this.obj = obj;
	}

	// ��ȡ��ѯ������
	public void setEditText(String editext) {
		this.editext = editext;
	}

	// ��ȡ��������
	public void setOperate(Operate op) {
		this.op = op;
	}
	//��ȡ������
	public void setTableFlag(String tableFlag){
		this.tableFlag = tableFlag;
	}
}
