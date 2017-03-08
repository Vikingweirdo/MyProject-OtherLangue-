package UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LogoutDialog implements ActionListener {
	public JFrame frame = null;
	private JPanel mPanel = null;
	private JButton mSure = null;
	private JButton mCancel = null;
	private JLabel mNotify = null;
	private Font font = new Font("隶书",Font.PLAIN,25);
	
	//初始化界面
	public LogoutDialog(){
		frame = new JFrame("注销");
		mPanel = new JPanel();
		mSure = new JButton("确定");
		mCancel = new JButton("取消");
		mNotify = new JLabel("确定要注销吗");
		
		//设置标签显示位置
		mNotify.setBounds(80,20,200,20);
		mNotify.setFont(font);
		
		//设置按钮位置
		mSure.setBounds(50,100,100,30);
		mSure.setFont(font);
		mCancel.setBounds(180,100,100,30);
		mCancel.setFont(font);
		
		
		//增加监听
		mCancel.addActionListener(this);
		mSure.addActionListener(this);
		
		//添加组件
		mPanel.add(mNotify);
		mPanel.add(mSure);
		mPanel.add(mCancel);
		
		
		//设置layout
		mPanel.setLayout(null);
		
		frame.add(mPanel);
		frame.setLocation(950,300);
		frame.setSize(320,240);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mCancel){
			frame.dispose();
			System.gc();
		}
		if(e.getSource() == mSure){
			frame.dispose();
			MainMenu.frame.dispose();
			System.gc();
			MyWindow window = new MyWindow();
			window.init();
			window.setOperate(MainActivity.getOperate());
		}
	}
}
