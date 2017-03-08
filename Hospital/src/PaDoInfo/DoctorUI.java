package PaDoInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Connect.Operate;
import HouseMenu.SearchResult;

public class DoctorUI {
	public static JFrame jframe;
	JPanel jpanel;
	JLabel jlabel;
	JTextField text;
	JButton searchbutton;
	JButton retbutton;
	Font font;
	DefaultTableModel dtable;
	JTable table;
	JScrollPane jscr;
	JComboBox sexList;
	//������
	private int number ;
	private int point ;//��ȡ����

	// ��ѹ����׼��
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
		jframe = new JFrame("Doctor");// ��������
		jframe.setLayout(null);// ȡ�������ҳ�沼��
		jpanel = new JPanel();// �������
		jpanel.setLayout(null);// ȡ������ҳ�沼��
		jpanel.setBounds(0, 50, 320, 600);// ���ô����λ�õĴ�С
		jlabel = new JLabel("������ְ����", JLabel.CENTER);// ������ǩ
		text = new JTextField();// �����ı���
		searchbutton = new JButton("��ѯ");// ������ѯ��ť
		retbutton = new JButton("����");// �������ذ�ť
		font = new Font("", Font.BOLD, 30);
		jlabel.setBounds(50, 100, 200, 50);
		jlabel.setFont(font);
		text.setBounds(50, 190, 200, 50);
		searchbutton.setBounds(100, 300, 100, 50);
		retbutton.setBounds(100, 410, 100, 50);

		Object[] columnNames = { "ְ����", "����", "�Ա�", "����", "��ϵ��ʽ" ,"����",  "ְ��"};
		dtable = new DefaultTableModel(columnNames, 0);
		table = new JTable(dtable);// �������
		jscr = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// ���������
		jscr.setBounds(350, 120, 380, 450);
		sexList = new JComboBox();// ���������б��
		sexList.addItem("��");// ��������ѡ��
		sexList.addItem("Ů");
		table.getColumnModel().getColumn(2)
				.setCellEditor(new DefaultCellEditor(sexList));

		jframe.add(jpanel);// �������뵽����
		jframe.add(jscr);// �������뵽 ����
		jpanel.add(jlabel);
		jpanel.add(text);
		jpanel.add(searchbutton);
		jpanel.add(retbutton);
		//ˢ�°�ť
		JButton flush = new JButton("ˢ��");
		flush.setBounds(100, 500, 100, 50);
		jpanel.add(flush);
		
		
		jframe.setLocation(900, 300);
		jframe.setSize(800, 800);// ���崰���С
		jframe.setVisible(true);// ��ʾ����
		
		// ¼������
		LoadData();
		data = new Object[columnCount];//������������
		
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
				jframe.dispose();// ���ز˵�
			}

		});
		
		flush.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				newData = table.getValueAt(pointX, pointY).toString();
				if (columName.equals("ְ����")) {
					finalName = "DoId";
					Update();

					jframe.dispose();// ���ز˵�
					DoctorUI ui = new DoctorUI();
					ui.setOperate(op);
					ui.Init();
				} else if (columName.equals("����")) {
					finalName = "DoName";
					keyName = table.getValueAt(pointX, 0).toString();
					System.out.println(keyName);
					System.out.println(newData);
					UpdateNoKey();

					jframe.dispose();// ���ز˵�
					DoctorUI ui = new DoctorUI();
					ui.setOperate(op);
					ui.Init();
				} else if (columName.equals("��ϵ��ʽ")) {
					finalName = "DoContact";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();// ���ز˵�
					DoctorUI ui = new DoctorUI();
					ui.setOperate(op);
					ui.Init();
				}else if (columName.equals("����")) {
						finalName = "Dodeparment";
						keyName = table.getValueAt(pointX, 0).toString();
						UpdateNoKey();

						jframe.dispose();// ���ز˵�
						DoctorUI ui = new DoctorUI();
						ui.setOperate(op);
						ui.Init();
				}else if (columName.equals("ְ��")) {
					finalName = "DoTitle";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateNoKey();

					jframe.dispose();// ���ز˵�
					DoctorUI ui = new DoctorUI();
					ui.setOperate(op);
					ui.Init();
				}else if (columName.equals("����")) {
					finalName = "DoAge";
					keyName = table.getValueAt(pointX, 0).toString();
					UpdateAgeKey();

					jframe.dispose();// ���ز˵�
					DoctorUI ui = new DoctorUI();
					ui.setOperate(op);
					ui.Init();
				}
			}
		});

		// ����ѯ��ť�����¼�����
		searchbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (text.getText().isEmpty()) {
					JFrame frame = new JFrame();
					JLabel label = new JLabel("�������", JLabel.CENTER);
					label.setFont(font);
					frame.add(label);
					frame.setLocation(900, 300);
					frame.setSize(500, 200);
					frame.setVisible(true);
				} else {
					
					DoctorResult result = new DoctorResult();
					result.setOperate(op);
					result.setEdit(text.getText());
					result.Init();
				}
			}

		});

		// �������������¼�����
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
					JPopupMenu menu = new JPopupMenu();// �����˵���ʵ��
					JMenuItem addmenuItem = new JMenuItem("     add       ");// �����˵���
					JMenuItem deletemenuItem = new JMenuItem("     ɾ��        ");
					menu.add(addmenuItem);// ���˵�����뵯���˵�
					menu.add(deletemenuItem);
					menu.show(table, e.getX(), e.getY());// �����꣨x��y����������ʾ�˵�
					
					number = table.getSelectedRows().length; // ȡ���û���ѡ�е�����
					point = table.getSelectedRow();
					
					System.out.println(point);
					// ��"����"�˵�������¼�����
					addmenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							
							///Object[] rowData = new Object[] { "123456" };
							//dtable.insertRow(table.getSelectedRow(), rowData);// ��ѡ���е��Ϸ���������
							AddDoctor addDo = new AddDoctor();
							addDo.setOperate(op);
							addDo.init();
						}
					});

					// ��"ɾ��"�˵�������¼�����
					deletemenuItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							
							//System.out.println(point);
							//System.out.println(table.getValueAt(point, 0));
							DeleteData(table.getValueAt(point, 0).toString());
							dtable.removeRow(point); // ɾ����ѡ�е�ҽ����Ϣ;
							/*if (number > 1) {// ɾ����ѡ�Ķ���ҽ����Ϣ
								for (int i = 0; i < number; i++) {
									dtable.removeRow(table.getSelectedRow());
								}
							}*/
						}
					});
				}
			}
		});

	}
	
	// ��������
	public void Update() {
		op.Update("update doctor set " + finalName + " = '" + newData
				+ "' where " + finalName + " = '" + oldData + "' ;");
	}

	// ��������
	public void UpdateNoKey() {
		op.Update("update doctor set " + finalName + " = '" + newData
				+ "' where DoId = '" + keyName + "' ;");
	}
	// ��������
	public void UpdateAgeKey() {
		op.Update("update doctor set " + finalName + " = " + newData
				+ " where DoId = '" + keyName + "' ;");
	}
	
	// ɾ������
	public void DeleteData(String point) {
		op.Delete("delete from doctor where DoId = '" + point + "';");
	}

	// ��ѹ����
	public void LoadData() {
		ResultSet result = null;
		op.Select("select * from doctor;"); // ʹ��sql���
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

			while (result.next()) {
				this.obj[i][0] = result.getString(1);
				this.obj[i][1] = result.getString(2);
				this.obj[i][2] = result.getString(3);
				this.obj[i][3] = result.getString(4);
				this.obj[i][4] = result.getString(5);
				this.obj[i][5] = result.getString(6);
				this.obj[i][6] = result.getString(7);
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
