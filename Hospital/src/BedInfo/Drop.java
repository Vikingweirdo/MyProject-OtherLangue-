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

//ɾ����λ ����
class Drop extends JFrame {

	Box dropBox;
	Box droptext;
	Box dropboxbase; // ʹ�� ��ʽ���� ������

	JTextField tfroomno = new JTextField(10); // ����һ������10�е��µĿ��ı���
	JTextField tfbedno = new JTextField(10); // ����һ������10�е��µĿ��ı���
	JTextField tfpatientno = new JTextField(10); // ����һ������10�е��µĿ��ı���

	JButton dropcertain = new JButton("ȷ��"); // ������ȷ������ť
	JButton dropclear = new JButton("ȡ��"); // ������ȡ������ť

	JPanel pdrop = new JPanel(); // �������

	// ����׼��
	private Operate op = null;

	private Object[][] obj = null; // ��ѹ����
	private int rowCount = 0; // ��ȡ��
	private int columnCount = 0; // ��ȡ��
	private Object[] data = null;

	public Drop() {
		setTitle("ɾ����λ");
		add(pdrop); // ���� �������
		setBounds(100, 100, 310, 260); // �������ô�С

		dropBox = Box.createVerticalBox(); // ��ʾ ��ʽ���� ����ʽ ���� �� ����

		dropBox.add(new Label("������:")); // �������� ������ ��ť
		dropBox.add(Box.createVerticalStrut(8));// �����߶�Ϊ16�Ĳ��ɼ����
		dropBox.add(new Label("������:")); // �������� ������ ��ǩ
		dropBox.add(Box.createVerticalStrut(8));// �����߶�Ϊ16�Ĳ��ɼ����
		dropBox.add(new Label("���˺�:")); // �������� ������ ��ǩ
		dropBox.add(Box.createVerticalStrut(8)); // �����߶�Ϊ16�Ĳ��ɼ����
		dropBox.add(dropcertain); // �������� ��ť

		droptext = Box.createVerticalBox(); // ʹ�� ��ʽ���� ����

		droptext.add(tfroomno); // �������� ���ı���
		droptext.add(Box.createVerticalStrut(16));
		droptext.add(tfbedno); // �������� ���ı���
		droptext.add(Box.createVerticalStrut(16));
		droptext.add(tfpatientno);
		droptext.add(Box.createVerticalStrut(16));
		droptext.add(dropclear); // �������� ��ȡ����ť��

		dropboxbase = Box.createHorizontalBox(); // ʹ�� ��ʽ���� ������

		dropboxbase.add(dropBox); // �������м��� ���� ����
		dropboxbase.add(Box.createHorizontalStrut(16)); // �������Ϊ 16 �Ĳ��ɼ����

		dropboxbase.add(droptext);// ������������
		pdrop.add(dropboxbase); // ����м���������

		setVisible(true);// �ɼ�
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ȡ�� ��ť���·����¼�
		dropclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // ȡ�� �¼� ������ı��� ���رա������Ų�ѯ������
			}
		});

		// ȷ�� ��ť���·����¼�
		dropcertain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfroomno.getText().equals("")
						|| tfbedno.getText().equals("")
						|| tfpatientno.getText().equals("")) {
					JFrame fdropnull = new JFrame();
					fdropnull.setSize(100, 100);
					fdropnull.setLocation(100, 20);
					fdropnull.add(new Label("�ı�����Ϊ�գ���"));
					fdropnull.setVisible(true);
				} else {
					DeleteData(tfbedno.getText(),tfroomno.getText(),tfpatientno.getText());
					dispose();
				}
			}
		});
	}

	// ɾ������
	public void DeleteData(String BedId, String HosId, String PaId) {
		op.Delete("delete from BedInfo where BedId = '" + BedId
				+ "' and HosId = '" + HosId + "' and PaId = '" + PaId + "';");
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
