package PaDoInfo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Connect.Operate;

class AddPatient {
	public JFrame frame = new JFrame("������Ϣ������");// ��������
	JPanel panel = new JPanel();

	JLabel patlabel = new JLabel("������", JLabel.CENTER);// ������ǩ
	JLabel namelabel = new JLabel("����", JLabel.CENTER);
	JLabel sexlabel = new JLabel("�Ա�", JLabel.CENTER);
	JLabel agelabel = new JLabel("����", JLabel.CENTER);
	JLabel idlabel = new JLabel("���֤��", JLabel.CENTER);
	JLabel addresslabel = new JLabel("��ͥסַ", JLabel.CENTER);
	JLabel phonelabel = new JLabel("��ϵ��ʽ", JLabel.CENTER);
	JLabel doclabel = new JLabel("����ҽ��", JLabel.CENTER);
	JLabel intimelabel = new JLabel("��Ժʱ��", JLabel.CENTER);
	JLabel outtimelabel = new JLabel("��Ժʱ��", JLabel.CENTER);
	JLabel resultShow = new JLabel();
	Font font = new Font("serief", Font.BOLD, 25);

	JTextField pattext = new JTextField();// �����ı���
	JTextField nametext = new JTextField();
	JTextField sextext = new JTextField();
	JTextField agetext = new JTextField();
	JTextField idtext = new JTextField();
	JTextField addresstext = new JTextField();
	JTextField phonetext = new JTextField();
	JTextField doctext = new JTextField();
	JTextField intimetext = new JTextField();
	JTextField outtimetext = new JTextField();

	// ��ȡ���ݿ��������
	private Operate op = null;

	public void init() {

		panel.setLayout(null);
		patlabel.setFont(font);// ���ñ�ǩ��������ĸ�ʽ�ʹ�С
		namelabel.setFont(font);
		sexlabel.setFont(font);
		agelabel.setFont(font);
		idlabel.setFont(font);
		addresslabel.setFont(font);
		phonelabel.setFont(font);
		doclabel.setFont(font);
		intimelabel.setFont(font);
		outtimelabel.setFont(font);
		resultShow.setFont(font);
		patlabel.setBounds(70, 100, 150, 50);// ���ñ�ǩ�Ĵ�С��λ��
		namelabel.setBounds(70, 150, 150, 50);
		sexlabel.setBounds(70, 200, 150, 50);
		agelabel.setBounds(70, 250, 150, 50);
		idlabel.setBounds(70, 300, 150, 50);
		addresslabel.setBounds(70, 350, 150, 50);
		phonelabel.setBounds(70, 400, 150, 50);
		doclabel.setBounds(70, 450, 150, 50);
		intimelabel.setBounds(70, 500, 150, 50);
		outtimelabel.setBounds(70, 550, 150, 50);
		resultShow.setBounds(150, 710, 300, 50);

		pattext.setBounds(280, 110, 150, 30);// �����ı���Ĵ�С��λ��
		nametext.setBounds(280, 160, 150, 30);
		sextext.setBounds(280, 210, 150, 30);
		agetext.setBounds(280, 260, 150, 30);
		idtext.setBounds(280, 310, 150, 30);
		addresstext.setBounds(280, 360, 150, 30);
		phonetext.setBounds(280, 410, 150, 30);
		doctext.setBounds(280, 460, 150, 30);
		intimetext.setBounds(280, 510, 150, 30);
		outtimetext.setBounds(280, 560, 150, 30);

		JButton surebutton = new JButton("ȷ��");// ���ð�ť
		JButton retbutton = new JButton("����");
		surebutton.setBounds(150, 630, 80, 30);// ���ð�ť�Ĵ�С��λ��
		retbutton.setBounds(300, 630, 80, 30);

		frame.add(panel);// �������봰��
		panel.add(patlabel);// ����ǩ�������
		panel.add(namelabel);
		panel.add(sexlabel);
		panel.add(agelabel);
		panel.add(idlabel);
		panel.add(addresslabel);
		panel.add(phonelabel);
		panel.add(doclabel);
		panel.add(intimelabel);
		panel.add(outtimelabel);
		panel.add(resultShow);
		panel.add(pattext);// ���ı���������
		panel.add(nametext);
		panel.add(sextext);
		panel.add(agetext);
		panel.add(idtext);
		panel.add(addresstext);
		panel.add(phonetext);
		panel.add(doctext);
		panel.add(intimetext);
		panel.add(outtimetext);

		panel.add(surebutton);// ����ť�������
		panel.add(retbutton);
		frame.setSize(580, 900);// ���ô���Ĵ�С
		frame.setResizable(false);// ȷ�����ڵĴ�С�����ٸı�
		frame.setVisible(true);// ��ʾ����
		// ��ȷ����ť�����¼�����
		surebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ((pattext.getText().isEmpty())
						|| (nametext.getText().isEmpty())
						|| // ����ı�������һ��Ϊ�գ����������
						(sextext.getText().isEmpty())
						|| (agetext.getText().isEmpty())
						|| (idtext.getText().isEmpty())
						|| (addresstext.getText().isEmpty())
						|| (phonetext.getText().isEmpty())
						|| (doctext.getText().isEmpty())
						|| (intimetext.getText().isEmpty())
						|| (outtimetext.getText().isEmpty())) {
					resultShow.setText("���棺���벻��Ϊ�գ�");
				} else {
					InsertDB(pattext.getText(),nametext.getText(),
							sextext.getText(),
							Integer.parseInt(agetext.getText()),
							phonetext.getText(),
							addresstext.getText(),
							idtext.getText(),
							intimetext.getText(),
							outtimetext.getText());
					
					InsertDB(pattext.getText(),doctext.getText());
					frame.dispose();
					UI.jframe.dispose();
					UI patient = new UI();
					patient.setOperate(op);
					patient.Init();
				}
			}

		});
		// �����ذ�ť�����¼�����
		retbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}

		});
	}

	// �������ݿ��������
	public void setOperate(Operate op) {
		this.op = op;
	}

	// �������ݿ�
	public void InsertDB(String Paid, String PaName, String sex, int age,
			String phone, String PaAdr, String PapNum , String dataIn , String dataOut) {
		
		op.Insert("insert into patient values(\"" + Paid + "\" , \"" + PaName
				+ "\",\"" + sex + "\"," + age + ",\"" + phone + "\",\""
				+ PaAdr + "\",\"" + PapNum + "\",\""+dataIn+"\",\""+dataOut+"\");");

	}
	//�������ݿ�
	public void InsertDB(String Paid , String Doid){
		op.Insert("insert into relapado values(\""+Paid+"\",\""+Doid+"\");");
	}
}