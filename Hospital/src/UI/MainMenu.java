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
	private JButton mPatBtu = null; // 病人信息模块
	private JButton mDocBtu = null; // 医生信息模块
	private JButton mBedInfo = null; // 床信息模块
	private JButton mHosInfo = null; // 病房信息模块
	private JLabel mWelcome = null; // 设置欢迎界面
	private JLabel mRegOut = null; // 注销标签

	private Operate op = null;

	// 初始化界面
	public MainMenu() {

		frame = new JFrame("病房管理系统");
		mPanel = new JPanel();
		mPatBtu = new JButton("病人信息");
		mDocBtu = new JButton("医生信息");
		mBedInfo = new JButton("床位信息");
		mHosInfo = new JButton("病房信息");
		mWelcome = new JLabel("欢迎使用病房管理系统");
		mRegOut = new JLabel("注销");
		// 设置标签绝对布局
		mWelcome.setBounds(100, 20, 400, 20);
		// 设置字体样式
		mWelcome.setFont(new Font("隶书", Font.PLAIN, 30));

		// 设置按钮
		mPatBtu.setBounds(60, 130, 150, 150);
		mDocBtu.setBounds(270, 130, 150, 150);
		mBedInfo.setBounds(60, 330, 150, 150);
		mHosInfo.setBounds(270, 330, 150, 150);
		// 设置字体
		Font font = new Font("隶书", Font.PLAIN, 25);
		mPatBtu.setFont(font);
		mDocBtu.setFont(font);
		mBedInfo.setFont(font);
		mHosInfo.setFont(font);

		// 设置标签显示
		mRegOut.setBounds(330, 500, 50, 50);
		mRegOut.setFont(font);
		// 设置标签监听
		mRegOut.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {// 鼠标进入
				mRegOut.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) { // 鼠标移除
				mRegOut.setCursor(Cursor.getDefaultCursor());
			}
		});

		// 设置标签监听
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

		// 设置按钮监听
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
			// System.out.println("并发");
			 SearchResult houseMenu = new SearchResult();
			 houseMenu.setOperate(op);
			 houseMenu.Init();
			
		}
		
		if(e.getSource() == mDocBtu){
			//System.out.println("测试");
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

	// 获取操作对象
	public void setOperate(Operate op) {
		this.op = op;
	}

	public Operate getOperate() {
		return this.op;
	}


}
