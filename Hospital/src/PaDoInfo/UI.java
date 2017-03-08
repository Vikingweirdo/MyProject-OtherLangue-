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

	private Operate op = null; // ���ݿ��������
	private Object[][] obj = null; // ��ѹ����
	private int rowCount = 0; // ��ȡ��
	private int columnCount = 0; // ��ȡ��
	private Object[] data = null;

	// ������
	private int number;
	private int point;// ��ȡ����
	
	
	
	private String columName = null;	//��ȡ��ǰ��ͷ
	private String newData = null;		//������
	private String oldData = null;		//������
	private int pointX;		//ȷ���޸�����
	private int pointY;		
	private String finalName = null;	//ת�����ݿ����������
	private String keyName = null;		//����ֵ
	

	public void Init() {
		jframe = new JFrame("Patient");// ��������
		jmenubar = new JMenuBar();// �����˵���
		inquiryjmenu = new JMenu("��ѯ������Ϣ");// �����˵�
		alterjmenu = new JMenu("�޸Ĳ�����Ϣ");
		
		JMenuItem Upitem = new JMenuItem("�����б�");
		alterjmenu.add(Upitem);
		
		
		
		
		
		
		
		
		
		
		
		retbutton = new JButton("����");// �������ذ�ť
		casemenuItem = new JMenuItem("���ݲ����Ų�ѯ");// �����˵���
		bedmenuItem = new JMenuItem("���ݴ�λ�Ų�ѯ");
		idcardmenuItem = new JMenuItem("�������֤�Ų�ѯ");
		depmenuItem = new JMenuItem("���ݿ��Ҳ�ѯ");
		inquiryjmenu.add(casemenuItem);// ���˵������˵���
		inquiryjmenu.add(bedmenuItem);
		inquiryjmenu.add(idcardmenuItem);
		inquiryjmenu.add(depmenuItem);
		jmenubar.add(retbutton);// ����ť����˵�����
		jmenubar.add(inquiryjmenu);// ���˵�����˵�����
		jmenubar.add(alterjmenu);

		Object[] columnNames = { "������", "����", "�Ա�", "����", "��ϵ��ʽ", "��ͥסַ",
				"���֤��", "��Ժʱ��", "��Ժʱ��" };
		dtable = new DefaultTableModel(columnNames, 0);
		table = new JTable(dtable);// �������
		jscr = new JScrollPane(table);// ���������
		sexList = new JComboBox();// ���������б��
		sexList.addItem("��");// ��������ѡ��
		sexList.addItem("Ů");
		table.getColumnModel().getColumn(2)
				.setCellEditor(new DefaultCellEditor(sexList));

		jframe.setJMenuBar(jmenubar);// ���˵���ӵ�������
		jframe.add(jscr, BorderLayout.CENTER);// �������뵽 ����
		jframe.setLocation(900, 200);
		jframe.setSize(1000, 700);// ���崰���С
		jframe.setVisible(true);// ��ʾ����
		// ¼������

		LoadData();

		data = new Object[columnCount];// ������������

		for (int x = 0; x <= obj.length - 1; x++) {

			for (int y = 0; y < obj[x].length; y++) {
				System.out.println(obj[x][y]);
				data[y] = obj[x][y];
			}
			dtable.addRow(data);
		}

		// �����ذ�ť�����¼�����
		retbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jframe.dispose();
			}

		});

		// ��"���ݲ����Ų�ѯ"�˵�������¼�����
		casemenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String titles = "������";
				Envidence en = new Envidence();
				en.setOperate(op);
				en.Init(titles);
				// new Envidence().envidenceShow(titles);// ���ò�ѯ������ʾ����
			}

		});

		// ��"���ݴ�λ�Ų�ѯ"�˵�������¼�����
		bedmenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String titles = "��λ��";
				Envidence en = new Envidence();
				en.setOperate(op);
				en.Init(titles);
				// new Envidence().envidenceShow(titles);
			}
		});

		// ��"�������֤�Ų�ѯ"�˵�������¼�����
		idcardmenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String titles = "���֤��";
				Envidence en = new Envidence();
				en.setOperate(op);
				en.Init(titles);
				// new Envidence().envidenceShow(titles);
			}
		});

		// ��"���ݿ��Ҳ�ѯ"�˵�������¼�����
		depmenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String titles = "����";
				Envidence en = new Envidence();
				en.setOperate(op);
				en.Init(titles);
				// new Envidence().envidenceShow(titles);
			}
		});

		// ���±����
		Upitem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("����");
				newData = table.getValueAt(pointX, pointY).toString();
				if (columName.equals("������")) {
					finalName = "PaID";
					Update();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				} else if (columName.equals("����")) {
					finalName = "PaName";
					keyName = table.getValueAt(pointX, 0).toString();
					System.out.println(keyName);
					System.out.println(newData);
					UpdateNoKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				} else if (columName.equals("��ϵ��ʽ")) {
					finalName = "Pacontact";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				}else if (columName.equals("��ͥסַ")) {
					finalName = "PaAdr";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				}else if (columName.equals("���֤��")) {
					finalName = "PapNum";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				}else if (columName.equals("��Ժʱ��")) {
					finalName = "DataIn";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				}else if (columName.equals("��Ժʱ��")) {
					finalName = "DataOut";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();
					UI jf = new UI();
					jf.setOperate(op);
					jf.Init();
				}else if (columName.equals("����")) {
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

		// �������������¼���
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
					System.out.println(oldData + "������");
					System.out.println(columName + "le");
				}
				
				
				
				if (e.isMetaDown()) {
					JPopupMenu menu = new JPopupMenu();// ���������˵�
					JMenuItem addmenuItem = new JMenuItem("     ����       ");// �����˵���
					JMenuItem deletemenuItem = new JMenuItem("     ɾ��        ");
					menu.add(addmenuItem);// ��"����"�˵�����뵯���˵�
					menu.add(deletemenuItem);// ��"ɾ��"�˵�����뵯���˵�
					menu.show(table, e.getX(), e.getY());// �������루x��y����������ʾ�˵�

					number = table.getSelectedRows().length; // ȡ���û���ѡ�е�����
					point = table.getSelectedRow();

					// ��"����"�˵�������¼�����
					addmenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							AddPatient addPa = new AddPatient();
							addPa.setOperate(op);
							addPa.init();
						}
					});

					// ��"ɾ��"�˵�������¼�����
					deletemenuItem.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							DeleteData(table.getValueAt(point, 0).toString());
							DeleteRelaData(table.getValueAt(point, 0)
									.toString());
							dtable.removeRow(point); // ɾ����ѡ�е�ҽ����Ϣ;
						}
					});
				}
			}
		});
		
		
		
	}

	// ��������
	public void Update() {
		op.Update("update patient set " + finalName + " = '" + newData
				+ "' where " + finalName + " = '" + oldData + "' ;");
	}

	// ��������
	public void UpdateNoKey() {
		op.Update("update patient set " + finalName + " = '" + newData
				+ "' where PaId = '" + keyName + "' ;");
	}
	
	// ��������
	public void UpdateAgeKey() {
		op.Update("update patient set " + finalName + " = " + newData
				+ " where PaId = '" + keyName + "' ;");
	}
	
	// ɾ������
	public void DeleteData(String point) {
		op.Delete("delete from patient where PaId = '" + point + "';");
	}

	// ɾ������
	public void DeleteRelaData(String point) {
		op.Delete("delete from relapado where PaId = '" + point + "';");
	}

	// ��ѹ����
	public void LoadData() {
		ResultSet result = null;
		op.Select("select * from patient;"); // ʹ��sql���
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

class Envidence {
	public JFrame frame1 = null;
	private Operate op = null;

	public void Init(final String s) {
		frame1 = new JFrame("����" + s + "��ѯ");// ��������
		frame1.setLayout(null);// ȡ�����岼��
		class Demo {// ����һ����,Ŀ���Ǿ�����ǩ������
			public String labeldemo() {
				String labelcontent = "������" + s;
				return labelcontent;
			}
		}
		JLabel jlabel = new JLabel(new Demo().labeldemo(), JLabel.CENTER);// ������ǩ��ʹ��ǩ���ݾ���
		final JLabel label = new JLabel();// ����һ��������
		final JTextField text = new JTextField(null);// �����ı���
		JButton searchbutton = new JButton("��ѯ");// ������ѯ��ť
		jlabel.setBounds(80, 100, 300, 80);// ���ñ�ǩ��λ�úʹ�С
		label.setBounds(285, 380, 200, 50);
		text.setBounds(400, 125, 170, 35);// �����ı����λ�úʹ�С
		searchbutton.setBounds(300, 230, 100, 50);// ���ð�ť��λ�úʹ�С
		Font font1 = new Font("serief", Font.BOLD, 30);// ������������ƣ���ʽ�ʹ�С
		Font font2 = new Font("serief", Font.BOLD, 20);
		jlabel.setFont(font1);// �ı��ǩ������
		label.setFont(font1);
		searchbutton.setFont(font2);// �ı䰴ť������
		frame1.add(jlabel);// ������ӱ�ǩ
		frame1.add(label);
		frame1.add(text);// ��������ı���
		frame1.add(searchbutton);// ������Ӱ�ť
		text.setEditable(true);
		String string = text.getText();
		System.out.println(string);

		frame1.setLocation(900, 200);
		frame1.setSize(700, 600);// ���崰���С
		frame1.setVisible(true);// ��ʾ����

		// ����ť�����¼�����
		searchbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (text.getText().isEmpty()) {// �ж��ı���������Ƿ�Ϊ��
					label.setText("�������"); // ���ñ�ǩ����

				} else {
					Inquiry qury = new Inquiry();// ������һ��������ʾ��Ҫ��ѯ�Ľ���
					qury.setOperate(op);

					if ("������".equals(s)) {
						qury.setEditText(text.getText());
						qury.setTableFlag("PaId");
					} else if ("��λ��".equals(s)) {
						qury.setEditText(text.getText());
						qury.setTableFlag("BedID");
					} else if ("���֤��".equals(s)) {
						System.out.println(s);
						qury.setEditText(text.getText());
						qury.setTableFlag("papnum");
					} else if ("����".equals(s)) {// ����
						qury.setEditText(text.getText());
						qury.setTableFlag("dodeparment");
					}
					System.out.println(op + "123456789");
					qury.setS(s);
					qury.Init();
					frame1.dispose();// �رղ����ٴ���frame1
				}
			}
		});
	}

	// ��ȡ��������
	public void setOperate(Operate op) {
		this.op = op;
	}
}

class Inquiry extends WindowAdapter {
	public JFrame frame2 = null;
	private String s = null;

	private Operate op = null; // ���ݿ��������
	private Object[][] obj = null; // ��ѹ����
	private int rowCount = 0; // ��ȡ��
	private int columnCount = 0; // ��ȡ��
	private Object[] data = null;

	private String editext = null; // ��ѯ����
	private String tableFlag = null; // ��Ĳ�ѯ����s

	public void Init() {
		frame2 = new JFrame("����" + s + "��ѯ�Ľ��");// ��������
		Object[] columnNames = { "������", "����", "�Ա�", "����", "���֤��", "��ͥסַ",
				"��ϵ��ʽ", "����ҽ��", "��Ժʱ��", "��Ժʱ��" };
		DefaultTableModel dtable = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(dtable);// �������
		JScrollPane jscr = new JScrollPane(table);// ���������
		JComboBox sexList = new JComboBox();// ���������б��
		sexList.addItem("��");// ��������ѡ��
		sexList.addItem("Ů");
		table.getColumnModel().getColumn(2)
				.setCellEditor(new DefaultCellEditor(sexList));
		System.out.println(s);

		if ("������".equals(s)) {
			LoadData();
		} else if ("��λ��".equals(s)) {
			LoadDataBedId();
		} else if ("���֤��".equals(s)) {
			LoadData();
		} else if ("����".equals(s)) {
			LoadDeparData();
		}
		/**
		 * LoadDataBedId();
		 */
		/*
		 * LoadData(); System.out.println(editext);
		 * System.out.println(tableFlag);
		 */
		data = new Object[columnCount];// ������������

		for (int x = 0; x <= obj.length - 1; x++) {

			for (int y = 0; y < obj[x].length; y++) {
				System.out.println(obj[x][y]);
				data[y] = obj[x][y];
			}
			dtable.addRow(data);
		}

		frame2.add(jscr, BorderLayout.CENTER);// �������뵽 ����
		frame2.setLocation(900, 200);
		frame2.setSize(1000, 700);// ���崰���С
		frame2.setVisible(true);// ��ʾ����
	}

	public void setS(String s) {
		this.s = s;
	}

	// ��ѹ����
	public void LoadData() {
		ResultSet result = null;
		op.Select("select Patient.Paid,paname,pasex,paage,pacontact,paadr,papnum,doid,datain,dataout from patient,relapado where patient.Paid = relapado.Paid and Patient."
				+ tableFlag + " = '" + editext + "';"); // ʹ��sql���
		result = op.getResult(); // ��ȡ��ѯ�Ľ����
		// װ������flag
		int i = 1;

		try {
			result.last();// �ƶ������һ��
			rowCount = result.getRow(); // ��ȡ����
			System.out.println(rowCount + "------����");
			columnCount = result.getMetaData().getColumnCount();

			this.obj = new Object[rowCount][columnCount];// ʵ��������
			// ��ȡ����
			if (rowCount == 0) {
				return;
			}

			result.first(); // �ƶ�����һ��
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

	// ��ѹ����
	public void LoadDeparData() {
		ResultSet result = null;
		op.Select("select Patient.Paid,paname,pasex,paage,pacontact,paadr,papnum,doid,datain,dataout from patient,relapado where patient.Paid = relapado.Paid and relapado.doid in (select doid from doctor where "
				+ tableFlag + " = '" + editext + "');"); // ʹ��sql���
		result = op.getResult(); // ��ȡ��ѯ�Ľ����
		// װ������flag
		int i = 1;

		try {
			result.last();// �ƶ������һ��
			rowCount = result.getRow(); // ��ȡ����
			System.out.println(rowCount + "------����");
			columnCount = result.getMetaData().getColumnCount();

			this.obj = new Object[rowCount][columnCount];// ʵ��������
			// ��ȡ����
			if (rowCount == 0) {
				return;
			}

			result.first(); // �ƶ�����һ��
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

	// ��ѹ����
	public void LoadDataBedId() {
		ResultSet result = null;
		op.Select("select Patient.Paid,paname,pasex,paage,pacontact,paadr,papnum,doid,datain,dataout from patient,relapado,bedinfo where patient.Paid = bedinfo.Paid and patient.Paid = relapado.Paid and BedInfo."
				+ tableFlag + "= '" + editext + "';"); // ʹ��sql���
		result = op.getResult(); // ��ȡ��ѯ�Ľ����
		// װ������flag
		int i = 1;
		try {
			result.last();// �ƶ������һ��
			rowCount = result.getRow(); // ��ȡ����

			// ��ȡ����
			columnCount = result.getMetaData().getColumnCount();

			if (result == null) {
				return;
			}

			this.obj = new Object[rowCount][columnCount];// ʵ��������
			result.first(); // �ƶ�����һ��
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

	// ��ȡ��ѯ������
	public void setEditText(String editext) {
		this.editext = editext;
	}

	// ��ȡ������
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
