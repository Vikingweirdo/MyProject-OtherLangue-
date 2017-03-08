package BedInfo;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import Connect.Operate;

// �ܲ�ѯ���ڣ�����ѡ���ѯ��׼
class SelectAction extends JFrame{

	JRadioButton rbbedno = new JRadioButton("������"); // �����������š���ѡ��ť
	JRadioButton rbroomno = new JRadioButton("������"); // �����������š���ѡ��ť
	JRadioButton rbpatientno = new JRadioButton("���˺�"); // ���������˺š���ѡ��ť
	JRadioButton rbroomno_bedno = new JRadioButton("������+������"); // ������������+���˺š���ѡ��ť
	Label lsymbol = new Label("��ѡ���ѯ��׼:"); // ���� ��ʶ �� ֱ�۽��Ͳ�������
	ButtonGroup bgselect = new ButtonGroup(); // ������ť�飬Ϊ��װ�ص�ѡ��ť
	
	//����׼�� 
	private Operate op = null;

	public void Init() {

		setTitle("��ѯ��Ϣ");
		setSize(400, 300); // ���ý����С
		setLocation(400, 100);
		setLayout(null); // �޲���

		lsymbol.setBounds(0, 0, 100, 30); // ���� ��ʶ �ڽ��� ��λ��
		rbbedno.setBounds(0, 30, 100, 30); // ���� �������š���ť �ڽ��� ��λ��
		rbroomno.setBounds(0, 60, 100, 30); // ���� �������š���ť �ڽ��� ��λ��
		rbpatientno.setBounds(0, 90, 100, 30); // ���� �����˺š���ť �ڽ��� ��λ��
		rbroomno_bedno.setBounds(0, 120, 200, 30); // ���� ��������+�����š���ť �ڽ��� ��λ��

		add(lsymbol); // ��������ʶ
		bgselect.add(rbbedno); // ��ť����� �������š� ��ť ��ʾ ��ѡ �������� ���ø�����ͬ
		bgselect.add(rbroomno);
		bgselect.add(rbpatientno);
		bgselect.add(rbroomno_bedno);
		add(rbbedno); // ������� �������š���ť����ʾ�ɼ� ��ͬ
		add(rbroomno);
		add(rbpatientno);
		add(rbroomno_bedno);

		// �������š���ť���·����¼�
		rbbedno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				BednoSelect bednoaction = new BednoSelect();
				bednoaction.setOperate(op);
				bednoaction.Init();
			}
		});

		// �������š���ť���� ���� �¼�
		rbroomno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				RoomnoSelect roomnoaction = new RoomnoSelect();
				roomnoaction.setOperate(op);
				roomnoaction.Init();
			}
		});

		// �����˺š���ť ���� �����¼�
		rbpatientno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				PatientnoSelect paientselect = new PatientnoSelect();
				paientselect.setOperate(op);
				paientselect.Init();
			}
		});

		// �������š���ť ���� �����¼�
		rbroomno_bedno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				RoomnoAndBednoSelect roomno_bednoselect = new RoomnoAndBednoSelect();
				roomno_bednoselect.setOperate(op);
				roomno_bednoselect.Init();
			}
		});

		setVisible(true); // ����ɼ�
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // �رմ���ʱ������Դ���ͷ�
	}

	//��ȡ���ݿ����
	public void setOperate(Operate op){
		this.op = op;
	}

}