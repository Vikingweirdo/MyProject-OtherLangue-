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

//������ �� ������ ��ѯ����
class RoomnoAndBednoSelect extends JFrame {

	JTextField tfroomno_ = new JTextField(10); // �ı��� ���� Ϊ10
	JTextField tfbedno_ = new JTextField(10); // �ı��� ���� Ϊ10

	JButton broomno_bednocertain = new JButton("ȷ��"); // ������ȷ������ť
	JButton broomno_bednoclear = new JButton("ȡ��"); // ������ȡ������ť

	Box roomno_bednoBox;
	Box roomno_bednotext;
	Box roomno_bednoboxbase; // ʹ�� ��ʽ���� ������

	JPanel proomno_bedno = new JPanel(); // �������

	// ���ݿ��������׼��
	private Operate op = null;

	private Object[][] obj = null; // ��ѹ����
	private int rowCount = 0; // ��ȡ��
	private int columnCount = 0; // ��ȡ��
	private Object[] data = null;


	public void Init() {

		setTitle("������+������  ��ѯ");
		add(proomno_bedno);
		setBounds(100, 100, 310, 260);

		roomno_bednoBox = Box.createVerticalBox(); // ��ʾ ��ʽ���� ����ʽ ���� �� ����

		roomno_bednoBox.add(new Label("������:")); // �������� ������ ��ť
		roomno_bednoBox.add(Box.createVerticalStrut(8));// �����߶�Ϊ16�Ĳ��ɼ����
		roomno_bednoBox.add(new Label("������:")); // �������� ������ ��ǩ
		roomno_bednoBox.add(Box.createVerticalStrut(8)); // �����߶�Ϊ16�Ĳ��ɼ����
		roomno_bednoBox.add(broomno_bednocertain); // �������� ��ť
		roomno_bednotext = Box.createVerticalBox(); // ʹ�� ��ʽ���� ����
		roomno_bednotext.add(tfroomno_); // �������� ���ı���
		roomno_bednotext.add(Box.createVerticalStrut(16));
		roomno_bednotext.add(Box.createVerticalStrut(16));
		roomno_bednotext.add(tfbedno_); // �������� ���ı���
		roomno_bednotext.add(Box.createVerticalStrut(16)); // �����߶�Ϊ16�Ĳ��ɼ����
		roomno_bednotext.add(broomno_bednoclear); // �������� ��ȡ����ť��
		roomno_bednoboxbase = Box.createHorizontalBox(); // ʹ�� ��ʽ���� ������
		roomno_bednoboxbase.add(roomno_bednoBox); // �������м��� ���� ����
		roomno_bednoboxbase.add(Box.createHorizontalStrut(16)); // �������Ϊ 16
																// �Ĳ��ɼ����
		roomno_bednoboxbase.add(roomno_bednotext);// ������������
		proomno_bedno.add(roomno_bednoboxbase); // ����м���������
		setVisible(true);// �ɼ�
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ��ȷ����ť�����¼�
		broomno_bednocertain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfroomno_.getText().equals("")
						|| tfbedno_.getText().equals("")) {
					JFrame froomno_bednonull = new JFrame();
					froomno_bednonull.setSize(100, 100);
					froomno_bednonull.setLocation(100, 20);
					froomno_bednonull.add(new Label("�ı�����Ϊ�գ���"));
					froomno_bednonull.setVisible(true);
				} else {
					dispose();
					
					LoadData(tfbedno_.getText(),tfroomno_.getText());
					
					data = new Object[columnCount];

					
					
					JFrame froomno_bedno = new JFrame("���ݴ��Ų�ѯ���");
					Object[] room_nobednocolumn = { "������", "������", "���˺�", "����" }; // ����
																					// ���
																					// ����Ŀ
																					// ��������
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
							sroomno_bednotable); // ������м��������
					froomno_bedno.setSize(600, 600); // ���ô��ڳ�ʼ��С
					froomno_bedno.setLocation(500, 100);
					jroomno_bedno.setSize(600, 600);
					froomno_bedno.add(jroomno_bedno);
					froomno_bedno.setVisible(true); // ������ɼ�
					froomno_bedno
							.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// �ر�ʱʹ��
																				// system.exit(0)��ʽ
																				// �ر�
				}
			}
		});

		// ȡ����ť���·����¼�
		broomno_bednoclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}

	// ��ѹ����
	public void LoadData(String editext , String tableFlag) {
		ResultSet result = null;
		// System.out.println(op + ".......");
		op.Select("select BedId,BedInfo.HosId,PaId,Dodeparment from BedInfo ,House where BedInfo.HosId = House.HosId and BedId = '"
				+editext+"' and BedInfo.HosId = '" +tableFlag+"';");
		
		/*select BedId,BedInfo.HosId,PaId,Dodeparment 
		from BedInfo ,House 
		where BedInfo.HosId = House.HosId and BedId = '01' and BedInfo.HosId = '#402';
		*/
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

	// ��ȡ��������
	public void setOperate(Operate op) {
		this.op = op;
	}

}
