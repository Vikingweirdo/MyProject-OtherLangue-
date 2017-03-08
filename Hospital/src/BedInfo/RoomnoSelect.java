package BedInfo;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connect.Operate;

//�����Ų�ѯ ����
class RoomnoSelect extends JFrame {

	Box roomnoBox;
	Box roomnotext;
	Box roomnoboxbase; // ʹ�� ��ʽ���� ������

	JTextField tfroomno = new JTextField(10); // ����һ������10�е��µĿ��ı���

	JButton broomnocertain = new JButton("ȷ��"); // ������ȷ������ť
	JButton broomnoclear = new JButton("ȡ��"); // ������ȡ������ť
	JPanel proomno = new JPanel(); // �������

	// ���ݿ��������׼��
	private Operate op = null;

	public void Init() {

		setTitle("�����Ų�ѯ");
		setBounds(100, 100, 310, 260); // �������ô�С
		add(proomno); // ���� �������
		roomnoBox = Box.createVerticalBox(); // ��ʾ ��ʽ���� ����ʽ ���� �� ����
		roomnoBox.add(new Label("������:")); // �������� ������ ��ť
		roomnoBox.add(Box.createVerticalStrut(16)); // �����߶�Ϊ16�Ĳ��ɼ����
		roomnoBox.add(broomnocertain); // �������� ��ť
		roomnotext = Box.createVerticalBox(); // ʹ�� ��ʽ���� ����
		roomnotext.add(tfroomno); // �������� ���ı���
		roomnotext.add(Box.createVerticalStrut(16)); // �����߶�Ϊ16�Ĳ��ɼ����
		roomnotext.add(broomnoclear); // �������� ��ȡ����ť��
		roomnoboxbase = Box.createHorizontalBox(); // ʹ�� ��ʽ���� ������
		roomnoboxbase.add(roomnoBox); // �������м��� ���� ����
		roomnoboxbase.add(Box.createHorizontalStrut(16)); // �������Ϊ 16 �Ĳ��ɼ����
		roomnoboxbase.add(roomnotext); // ������������
		proomno.add(roomnoboxbase); // ����м���������
		setVisible(true); // �ɼ�
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ���ȷ����ť�����¼�
		broomnocertain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (tfroomno.getText().equals("")) {
					JFrame froomonull = new JFrame();
					froomonull.setSize(100, 100);
					froomonull.setLocation(100, 20);
					froomonull.add(new Label("�ı�����Ϊ�գ���"));
					froomonull.setVisible(true);
				} else {
					dispose();
					InquryResult qury = new InquryResult();
					qury.setOperate(op);
					qury.setTableFlag("HosId");
					qury.setEditText(tfroomno.getText());
					qury.Inint();
				}
			}
		});

		// ȡ�� ��ť ���� �����¼�
		broomnoclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}

	// ��ȡ��������
	public void setOperate(Operate op) {
		this.op = op;
	}

}
