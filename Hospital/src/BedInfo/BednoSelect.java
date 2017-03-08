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

//���ݲ����Ų�ѯ �Ĵ���
class BednoSelect extends JFrame {

	Box bednoBox;
	Box bednotext;
	Box bednoboxbase; // ʹ�� ��ʽ���� ������

	JTextField tfbedno = new JTextField(10); // ����һ������10�е��µĿ��ı���

	JButton bbednocertain = new JButton("ȷ��"); // ������ȷ������ť
	JButton bbednoclear = new JButton("ȡ��"); // ������ȡ������ť
	JFrame fbedno = new JFrame("�����Ų�ѯ"); // ���� �������Ų�ѯ�� ����
	JPanel pbedno = new JPanel(); // �������

	// ���ݿ��������׼��
	private Operate op = null;


	public void Init() {

		fbedno.setBounds(100, 100, 310, 260); // �������ô�С
		fbedno.add(pbedno); // ���� �������
		bednoBox = Box.createVerticalBox(); // ��ʾ ��ʽ���� ����ʽ ���� �� ����
		bednoBox.add(new Label("������:")); // �������� ������ ��ť
		bednoBox.add(Box.createVerticalStrut(16)); // �����߶�Ϊ16�Ĳ��ɼ����
		bednoBox.add(bbednocertain); // �������� ��ť
		bednotext = Box.createVerticalBox(); // ʹ�� ��ʽ���� ����
		bednotext.add(tfbedno); // �������� ���ı���
		bednotext.add(Box.createVerticalStrut(16)); // �����߶�Ϊ16�Ĳ��ɼ����
		bednotext.add(bbednoclear); // �������� ��ȡ����ť��
		bednoboxbase = Box.createHorizontalBox(); // ʹ�� ��ʽ���� ������
		bednoboxbase.add(bednoBox); // �������м��� ���� ����
		bednoboxbase.add(Box.createHorizontalStrut(16)); // �������Ϊ 16 �Ĳ��ɼ����
		bednoboxbase.add(bednotext); // ������������
		pbedno.add(bednoboxbase); // ����м���������
		fbedno.setVisible(true); // �ɼ�
		fbedno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ��ȷ����ť �����¼�
		bbednocertain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfbedno.getText().equals("")) {
					JFrame fbednonull = new JFrame();
					fbednonull.setSize(100, 100);
					fbednonull.setLocation(100, 20);
					fbednonull.add(new Label("�ı�����Ϊ�գ���"));
					fbednonull.setVisible(true);
				} else {
					fbedno.dispose();
					InquryResult qury = new InquryResult();
					qury.setOperate(op);
					qury.setTableFlag("BedId");
					qury.setEditText(tfbedno.getText());
					qury.Inint();
				}
			}
		});

		bbednoclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fbedno.dispose();
			}
		});

	}


	// ��ȡ��������
	public void setOperate(Operate op) {
		this.op = op;
	}

}
