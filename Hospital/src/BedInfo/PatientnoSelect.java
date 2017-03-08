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

//���ݲ��˺Ų�ѯ����
class PatientnoSelect extends JFrame{

	Box patientnoBox;
	Box patientnotext;
	Box patientnoboxbase; // ʹ�� ��ʽ���� ������

	JTextField tfpatientno = new JTextField(10); // ����һ������10�е��µĿ��ı���

	JButton bpatientnocertain = new JButton("ȷ��"); // ������ȷ������ť
	JButton bpatientnoclear = new JButton("ȡ��"); // ������ȡ������ť
	JPanel ppatientno = new JPanel(); // �������
	
	private Operate op = null;

	public void Init() {

		setTitle("���˺Ų�ѯ");
		setBounds(100, 100, 310, 260); // �������ô�С
		add(ppatientno); // ���� �������
		patientnoBox = Box.createVerticalBox(); // ��ʾ ��ʽ���� ����ʽ ���� �� ����
		patientnoBox.add(new Label("���˺�:")); // �������� ������ ��ť
		patientnoBox.add(Box.createVerticalStrut(16)); // �����߶�Ϊ16�Ĳ��ɼ����
		patientnoBox.add(bpatientnocertain); // �������� ��ť
		patientnotext = Box.createVerticalBox(); // ʹ�� ��ʽ���� ����
		patientnotext.add(tfpatientno); // �������� ���ı���
		patientnotext.add(Box.createVerticalStrut(16)); // �����߶�Ϊ16�Ĳ��ɼ����
		patientnotext.add(bpatientnoclear); // �������� ��ȡ����ť��
		patientnoboxbase = Box.createHorizontalBox(); // ʹ�� ��ʽ���� ������
		patientnoboxbase.add(patientnoBox); // �������м��� ���� ����
		patientnoboxbase.add(Box.createHorizontalStrut(16)); // �������Ϊ 16 �Ĳ��ɼ����
		patientnoboxbase.add(patientnotext); // ������������
		ppatientno.add(patientnoboxbase); // ����м���������
		setVisible(true); // �ɼ�
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ��ȷ����ť �����¼�
		bpatientnocertain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfpatientno.getText().equals("")) {
					JFrame fnull = new JFrame();
					fnull.setSize(100, 100);
					fnull.setLocation(100, 20);
					fnull.add(new Label("�ı�����Ϊ�գ���"));
					fnull.setVisible(true);
				} else {
					dispose();
					InquryResult qury = new InquryResult();
					qury.setOperate(op);
					qury.setTableFlag("PaId");
					qury.setEditText(tfpatientno.getText());
					qury.Inint();
				} // ���ݲ��˺Ų�ѯ���
			}
		});

		// ȡ�� ��ť ���� �����¼�
		bpatientnoclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

	}
	
	//��ȡ��������
	public void setOperate(Operate op){
		this.op = op;
	}
}
