package BedInfo;

//�õ�ʱ�����ֱ�� new SumMenu(str)  str ��ʾString �Ķ�ά���� // ����¼�����ݲ�����133�е�forѭ�������

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connect.Operate;
import HouseMenu.SearchResult;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

//���˵�����
public class SumMenu extends JFrame {

	JMenu mmchange = new JMenu("�޸�"); // ����"�޸�"�˵�
	JMenuBar mmbtitle = new JMenuBar(); // �����˵���
	JMenu mmoperate = new JMenu("����"); // ���� ���� �˵�
	JMenuItem miopselect = new JMenuItem("��ѯ"); // ���� ��ѯ �Ӳ˵���
	JButton mbfreshen = new JButton("ˢ��"); // ������ˢ�¡���ť
	JButton mbexit = new JButton("�˳�");
	JMenuItem mmincrase = new JMenuItem("���Ӵ�λ"); // ���� �����Ӵ�λ�� �Ӳ˵���
	JMenuItem mmdrop = new JMenuItem("ɾ����λ"); // ���� ��ɾ����λ�� �Ӳ˵���

	Object[] bedcolumn = { "������", "������", "���˺�", "����" }; // ���� ��� ����Ŀ ��������
	DefaultTableModel bedtable = new DefaultTableModel(bedcolumn, 0);
	JTable sbedtable = new JTable(bedtable);
	String[][] pobject = new String[1000][4];
	JScrollPane jbed = null;
	JPanel panel = new JPanel(); // �������

	// ���ݼ���׼��
	private Operate op = null; // ���ݿ��������
	private Object[][] obj = null; // ��ѹ����
	private int rowCount = 0; // ��ȡ��
	private int columnCount = 0; // ��ȡ��
	private Object[] data = null;

	private String columName = null; // ��ȡ��ǰ��ͷ
	private String newData = null; // ������
	private String oldData = null; // ������
	private int pointX; // ȷ���޸�����
	private int pointY;
	private String finalName = null; // ת�����ݿ����������
	private String keyName = null; // ����ֵ
	private String otherKey = null;// �ڶ�������ֵ

	public void Init() {
		setTitle("��������"); // ���õ�������Ϊ����������
		add(panel); // ��������ӵ�������
		setSize(630, 670); // ���ô��ڳ�ʼ��С
		setLocation(900, 300);
		setLayout(null); // �޲�������

		// ��ѯ�˵� ���� �����¼�
		miopselect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectAction selectaction = new SelectAction();
				selectaction.setOperate(op);
				selectaction.Init();
			}
		});

		// ���Ӵ�λ �˵� ���� �����¼�
		mmincrase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Increase increase = new Increase();
				increase.setOperate(op);

			}
		});

		// ɾ����λ �˵� ���� �����¼�
		mmdrop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Drop drop = new Drop();
				drop.setOperate(op);
			}
		});

		// ��ť���·����¼�
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

		// �˳� ��ť ���� �����¼�
		mbexit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		// �˵������������
		mmoperate.add(miopselect); // �� �������˵��м��� ��ѯ �Ӳ˵�
		mmbtitle.add(mmoperate); // ���������˵����뵽�˵�����
		mmchange.add(mmincrase); // �� �޸� ���˵����� �����Ӵ�λ�� ���˵�
		mmchange.addSeparator(); // ���ָ�����ӵ��ò���ĩβ
		mmchange.add(mmdrop); // �� �޸� ���˵����� ��ɾ����λ�� ���˵�
		mmbtitle.add(mmchange); // �� �޸� ���˵����뵽�˵�����
		mmbtitle.add(mbfreshen); // ���ӡ�ˢ�¡���ť���˵����У��˰�ťˢ���޸�֮����ѯ֮�������Ű�Ľ����
		mmbtitle.add(mbexit);
		setJMenuBar(mmbtitle); // �������з��øò˵���

		bedtable = new DefaultTableModel(bedcolumn, 0);

		// ��������Ҫ������
		/*
		 * for (int i = 0; i < str.length; i++) { bedtable.addRow(str[i]); }
		 */
		LoadData();

		data = new Object[columnCount];// ������������

		for (int x = 0; x <= obj.length - 1; x++) {

			for (int y = 0; y < obj[x].length; y++) {
				System.out.println(obj[x][y]);
				data[y] = obj[x][y];
			}
			bedtable.addRow(data);
		}

		sbedtable = new JTable(bedtable);
		jbed = new JScrollPane(sbedtable); // ������м��������
		jbed.setSize(600, 600); // ���ñ���С
		add(jbed); // �����м�����

		// ������
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
					System.out.println(oldData + "������");
					System.out.println(columName + "le");
				}
			}
		});

		// �޸Ĳ˵�
		JMenu update = new JMenu("����");
		JMenuItem updateItem = new JMenuItem("���±�");
		update.add(updateItem);

		// ���±����
		updateItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				newData = sbedtable.getValueAt(pointX, pointY).toString();

				System.out.println(newData);
				//System.out.println(otherKey);
				if (columName.equals("������")) {
					finalName = "BedId";
					otherKey = sbedtable.getValueAt(pointX, 1).toString();
					Update();

					dispose();
					SumMenu jf = new SumMenu();
					jf.setOperate(op);
					jf.Init();
				} else if (columName.equals("������")) {
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
				} else if (columName.equals("���˺�")) {
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

		setVisible(true); // ������ɼ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �ر�ʱʹ�� system.exit(0)��ʽ
														// �ر�
	}

	// ��������
	public void Update() { // BedInfo
		op.Update("update BedInfo set " + finalName + " = '" + newData
				+ "' where BedId = '" + oldData + "' and HosId = '" + otherKey
				+ "' ;");
	}

	// ��������
	public void UpdateOtherKey() { // BedInfo
		op.Update("update BedInfo set " + finalName + " = '" + newData
				+ "' where BedId = '" + keyName + "' and HosId = '" + oldData
				+ "' ;");
	}

	// ��������
	public void UpdateNoKey() {
		op.Update("update BedInfo set " + finalName + " = '" + newData
				+ "' where BedId = '" + keyName + "' and HosId = '" + otherKey
				+ "' ;");
	}

	// ��ѹ����
	public void LoadData() {
		ResultSet result = null;
		op.Select("select bedID,bedInfo.HosId,PaID,dodeparment from bedInfo,house where house.hosid = bedInfo.hosid;"); // ʹ��sql���
		result = op.getResult(); // ��ȡ��ѯ�Ľ����
		// װ������flag
		int i = 1;
		try {
			result.last();// �ƶ������һ��
			rowCount = result.getRow(); // ��ȡ����

			// ��ȡ����
			columnCount = result.getMetaData().getColumnCount();

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

	// ��ȡ��������
	public void setOperate(Operate op) {
		this.op = op;
	}

	public Operate getOperate() {
		return this.op;
	}

	// ��ȡ����
	public Object[][] getObject() {
		return obj;
	}
}
