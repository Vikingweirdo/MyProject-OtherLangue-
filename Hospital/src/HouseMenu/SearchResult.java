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

	private Operate op = null; // ���ݿ��������
	private Object[][] obj = null; // ��ѹ����
	private int rowCount = 0; // ��ȡ��
	private int columnCount = 0; // ��ȡ��
	private Object[] data = null;

	private String columName = null;	//��ȡ��ǰ��ͷ
	private String newData = null;		//������
	private String oldData = null;		//������
	private int pointX;		//ȷ���޸�����
	private int pointY;		
	private String finalName = null;	//ת�����ݿ����������
	private String keyName = null;		//����ֵ

	public void Init() {
		jF = new JFrame("��������ϵͳ");
		Object[] title = { "������", "��������", "����" };// ���ò������ԣ�
		dTm = new DefaultTableModel(title, 0);// �������

		LoadData();

		data = new Object[columnCount];// ������������

		for (int x = 0; x <= obj.length - 1; x++) {

			for (int y = 0; y < obj[x].length; y++) {
				System.out.println(obj[x][y]);
				data[y] = obj[x][y];
			}
			dTm.addRow(data);
		}

		jT = new JTable(dTm);

		// ������
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
					System.out.println(oldData + "������");
					System.out.println(columName + "le");
				}
			}
		});

		jS = new JScrollPane(jT);// �½�һ����������

		jMb = new JMenuBar(); // �½��˵�����
		jMe0 = new JMenu("����");// ���Ӳ˵�ѡ�
		jMe1 = new JMenu("��ѯ");
		jMe2 = new JMenu("ɾ��");
		jMe3 = new JMenu("����");
		// �޸Ĳ˵�
		JMenu update = new JMenu("����");
		JMenuItem updateItem = new JMenuItem("���±�");
		update.add(updateItem);

		jM0 = new JMenuItem("��ѯ����");// �����Ӳ˵�ѡ�
		jM1 = new JMenuItem("��ѯ������");
		jM2 = new JMenuItem("�����²���");
		jM3 = new JMenuItem("ɾ������");
		jM4 = new JMenuItem("������һ���˵�");
		jMe1.add(jM0);// ����Ӳ˵�����
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

		// ���±����
		updateItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				newData = jT.getValueAt(pointX, pointY).toString();
				if (columName.equals("������")) {
					finalName = "HosId";
					Update();
					
					
					jF.dispose();
					SearchResult jf = new SearchResult();
					jf.setOperate(op);
					jf.Init();
				} else if (columName.equals("��������")) {
					finalName = "BedSum";
					keyName = jT.getValueAt(pointX, 0).toString();
					System.out.println(keyName);
					System.out.println(newData);
					UpdateNoKey();
					
					
					jF.dispose();
					SearchResult jf = new SearchResult();
					jf.setOperate(op);
					jf.Init();
				} else if (columName.equals("����")) {
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

		// �Ӳ˵�����ѯ���ҡ����¼�������
		jM0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "��ѯ����";
				Search search = new Search();
				search.setTableFlag("Dodeparment");
				search.setOperate(op);
				search.Init1(title);
			}
		});
		// �Ӳ˵�����ѯ�����š����¼�������
		jM1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "��ѯ������";
				Search search = new Search();
				search.setTableFlag("HosID");
				search.setOperate(op);
				search.Init1(title);
			}
		});
		// �Ӳ˵�ѡ������²��������¼�������
		jM2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dTm.addRow(new Object[] {});
				Increase increase = new Increase();
				increase.setOperate(op);
				increase.Init();
			}
		});
		// �Ӳ˵���ɾ�����������¼�������
		jM3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = "ɾ������";
				Search search = new Search();
				search.setTableFlag("HosID");
				search.setOperate(op);
				search.Init1(title);
			}
		});
		// �Ӳ˵���������һ���˵������¼�������
		jM4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jF.dispose();
			}
		});
	}

	// ��ѹ����
	public void LoadData() {
		ResultSet result = null;
		op.Select("select * from house;"); // ʹ��sql���
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

	// ��������
	public void Update() {
		op.Update("update house set " + finalName + " = '" + newData
				+ "' where " + finalName + " = '" + oldData + "' ;");
	}

	// ��������
	public void UpdateNoKey() {
		op.Update("update house set " + finalName + " = '" + newData
				+ "' where HosId = '" + keyName + "' ;");
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
