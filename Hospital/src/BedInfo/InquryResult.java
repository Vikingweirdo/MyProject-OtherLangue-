package BedInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connect.Operate;

public class InquryResult extends JFrame {
	Object[] inquryresult = { "������", "������", "���˺�", "����" };
	Object[][] str = new Object[100][4];
	DefaultTableModel inqurytable = new DefaultTableModel(inquryresult, 0);
	JTable sinqurytable = new JTable(inqurytable);

	// ���ݿ��������׼��
	private Operate op = null;

	private Object[][] obj = null; // ��ѹ����
	private int rowCount = 0; // ��ȡ��
	private int columnCount = 0; // ��ȡ��
	private Object[] data = null;

	// ��ȡ��ѯ����
	private String editext = null; // ��ѯ����
	private String tableFlag = null; // ��Ĳ�ѯ����

	public void Inint() {
		setLocation(500, 80);
		setSize(630, 670);

		// �������
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

	// ��ѹ����
	public void LoadData() {
		ResultSet result = null;
		//System.out.println(op + ".......");
		if(!tableFlag.equals("HosId")){
		op.Select("select BedId,BedInfo.HosId,PaId,Dodeparment from BedInfo ,House where BedInfo.HosId = House.HosId and "+tableFlag+" = '"
				+ editext + "';"); // ʹ��sql���
		}else{
			op.Select("select BedId,BedInfo.HosId,PaId,Dodeparment from BedInfo ,House where BedInfo.HosId = House.HosId and BedInfo."+tableFlag+" = '"
					+ editext + "';");
		}
		result = op.getResult(); // ��ȡ��ѯ�Ľ����
		// װ������flag
		int i = 1;
		try {
			result.last();// �ƶ������һ��
			rowCount = result.getRow(); // ��ȡ����

			if (rowCount == 0) {
				return;
			}

			// ��ȡ����
			columnCount = result.getMetaData().getColumnCount();

			if (rowCount == 0) {
				return;
			}

			this.obj = new Object[rowCount][columnCount];// ʵ��������
			result.first(); // �ƶ�����һ��
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

	// ��ȡ������
	public void setTableFlag(String tableFlag) {
		this.tableFlag = tableFlag;
	}
}