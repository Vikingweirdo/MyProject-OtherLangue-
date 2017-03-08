package BedInfo;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Connect.Operate;

//���Ӵ�λ ����
class Increase extends JFrame {

	Box increaseBox;
	Box increasetext;
	Box increaseboxbase; // ʹ�� ��ʽ���� ������

	JTextField tfroomno = new JTextField(10); // ����һ������10�е��µĿ��ı���
	JTextField tfbedno = new JTextField(10); // ����һ������10�е��µĿ��ı���
	JTextField tfpatientno = new JTextField(10); // ����һ������10�е��µĿ��ı���

	JButton increasecertain = new JButton("ȷ��"); // ������ȷ������ť
	JButton increaseclear = new JButton("ȡ��"); // ������ȡ������ť

	JPanel pincrease = new JPanel(); // �������

	// �������ݿ�׼��
	private Operate op = null;

	public Increase() {
		setTitle("���Ӵ�λ");
		add(pincrease); // ���� �������
		setBounds(100, 100, 310, 260); // �������ô�С

		increaseBox = Box.createVerticalBox(); // ��ʾ ��ʽ���� ����ʽ ���� �� ����

		increaseBox.add(new Label("������:")); // �������� ������ ��ť
		increaseBox.add(Box.createVerticalStrut(8));// �����߶�Ϊ16�Ĳ��ɼ����
		increaseBox.add(new Label("������:")); // �������� ������ ��ǩ
		increaseBox.add(Box.createVerticalStrut(8));// �����߶�Ϊ16�Ĳ��ɼ����
		increaseBox.add(new Label("���˺�:")); // �������� ������ ��ǩ
		increaseBox.add(Box.createVerticalStrut(8)); // �����߶�Ϊ16�Ĳ��ɼ����
		increaseBox.add(increasecertain); // �������� ��ť

		increasetext = Box.createVerticalBox(); // ʹ�� ��ʽ���� ����

		increasetext.add(tfroomno); // �������� ���ı���
		increasetext.add(Box.createVerticalStrut(16));
		increasetext.add(tfbedno); // �������� ���ı���
		increasetext.add(Box.createVerticalStrut(16));
		increasetext.add(tfpatientno);
		increasetext.add(Box.createVerticalStrut(16));
		increasetext.add(increaseclear); // �������� ��ȡ����ť��

		increaseboxbase = Box.createHorizontalBox(); // ʹ�� ��ʽ���� ������

		increaseboxbase.add(increaseBox); // �������м��� ���� ����
		increaseboxbase.add(Box.createHorizontalStrut(16)); // �������Ϊ 16 �Ĳ��ɼ����

		increaseboxbase.add(increasetext);// ������������
		pincrease.add(increaseboxbase); // ����м���������

		setVisible(true);// �ɼ�
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ȷ�� ��ť ���� �����¼�
		increasecertain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (tfroomno.getText().equals("")
						|| tfbedno.getText().equals("")
						|| tfpatientno.equals("")) {
					JFrame fincreasenull = new JFrame();
					fincreasenull.setSize(100, 100);
					fincreasenull.setLocation(100, 20);
					fincreasenull.add(new Label("�ı�����Ϊ�գ���"));
					fincreasenull.setVisible(true);
				} else {
					InsertDB(tfbedno.getText(),tfroomno.getText(),
							tfpatientno.getText());

					dispose();
					//SumMenu menu = new SumMenu();
					//menu.setOperate(op);
					
				}
			}

		});

		// ȡ�� ��ť���·����¼�
		increaseclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}

		});

	}

	// ��ȡ�������ݿ����
	public void setOperate(Operate op) {
		this.op = op;
	}

	// �������ݿ�
	public void InsertDB(String BedId, String HosId, String PaId) {
		op.Insert("insert into BedInfo values(\"" + BedId + "\",\"" + HosId
				+ "\",\"" + PaId + "\");");

	}
}
