package UI;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import BedInfo.SumMenu;
import Connect.Operate;
import HouseMenu.SearchResult;
import PaDoInfo.DoctorUI;
import PaDoInfo.UI;

public class MainMenu extends WindowAdapter implements ActionListener {

	public static JFrame frame = null;
	private JPanel mPanel = null;
	private JButton mPatBtu = null; // ������Ϣģ��
	private JButton mDocBtu = null; // ҽ����Ϣģ��
	private JButton mBedInfo = null; // ����Ϣģ��
	private JButton mHosInfo = null; // ������Ϣģ��
	private JLabel mWelcome = null; // ���û�ӭ����
	private JLabel mRegOut = null; // ע����ǩ

	private Operate op = null;

	// ��ʼ������
	public MainMenu() {

		frame = new JFrame("��������ϵͳ");
		mPanel = new JPanel();
		mPatBtu = new JButton("������Ϣ");
		mDocBtu = new JButton("ҽ����Ϣ");
		mBedInfo = new JButton("��λ��Ϣ");
		mHosInfo = new JButton("������Ϣ");
		mWelcome = new JLabel("��ӭʹ�ò�������ϵͳ");
		mRegOut = new JLabel("ע��");
		// ���ñ�ǩ���Բ���
		mWelcome.setBounds(100, 20, 400, 20);
		// ����������ʽ
		mWelcome.setFont(new Font("����", Font.PLAIN, 30));

		// ���ð�ť
		mPatBtu.setBounds(60, 130, 150, 150);
		mDocBtu.setBounds(270, 130, 150, 150);
		mBedInfo.setBounds(60, 330, 150, 150);
		mHosInfo.setBounds(270, 330, 150, 150);
		// ��������
		Font font = new Font("����", Font.PLAIN, 25);
		mPatBtu.setFont(font);
		mDocBtu.setFont(font);
		mBedInfo.setFont(font);
		mHosInfo.setFont(font);

		// ���ñ�ǩ��ʾ
		mRegOut.setBounds(330, 500, 50, 50);
		mRegOut.setFont(font);
		// ���ñ�ǩ����
		mRegOut.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {// ������
				mRegOut.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) { // ����Ƴ�
				mRegOut.setCursor(Cursor.getDefaultCursor());
			}
		});

		// ���ñ�ǩ����
		mRegOut.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new LogoutDialog();
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// ���ð�ť����
		mHosInfo.addActionListener(this);
		mDocBtu.addActionListener(this);
		mPatBtu.addActionListener(this);
		mBedInfo.addActionListener(this);

		mPanel.setLayout(null);
		mPanel.add(mWelcome);
		mPanel.add(mPatBtu);
		mPanel.add(mDocBtu);
		mPanel.add(mBedInfo);
		mPanel.add(mHosInfo);

		mPanel.add(mRegOut);

		frame.add(mPanel);

		frame.setLocation(900, 200);
		frame.setSize(500, 600);
		frame.setVisible(true);

		// System.out.println("++++" +op);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == mHosInfo) {
			// System.out.println("����");
			 SearchResult houseMenu = new SearchResult();
			 houseMenu.setOperate(op);
			 houseMenu.Init();
			
		}
		
		if(e.getSource() == mDocBtu){
			//System.out.println("����");
			DoctorUI doctor = new DoctorUI();
			doctor.setOperate(op);
			doctor.Init();
		}
		
		if(e.getSource() == mBedInfo){
			SumMenu sumMenu = new SumMenu();
			sumMenu.setOperate(op);
			sumMenu.Init();
		}
		
		if(e.getSource() == mPatBtu){
			UI ui = new UI();
			ui.setOperate(op);
			ui.Init();
		}

	}

	@Override
	public void windowClosed(WindowEvent e) {
		frame.dispose();
		System.gc();
		System.exit(0);
	}

	// ��ȡ��������
	public void setOperate(Operate op) {
		this.op = op;
	}

	public Operate getOperate() {
		return this.op;
	}


}
