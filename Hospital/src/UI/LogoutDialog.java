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
	private Font font = new Font("����",Font.PLAIN,25);
	
	//��ʼ������
	public LogoutDialog(){
		frame = new JFrame("ע��");
		mPanel = new JPanel();
		mSure = new JButton("ȷ��");
		mCancel = new JButton("ȡ��");
		mNotify = new JLabel("ȷ��Ҫע����");
		
		//���ñ�ǩ��ʾλ��
		mNotify.setBounds(80,20,200,20);
		mNotify.setFont(font);
		
		//���ð�ťλ��
		mSure.setBounds(50,100,100,30);
		mSure.setFont(font);
		mCancel.setBounds(180,100,100,30);
		mCancel.setFont(font);
		
		
		//���Ӽ���
		mCancel.addActionListener(this);
		mSure.addActionListener(this);
		
		//������
		mPanel.add(mNotify);
		mPanel.add(mSure);
		mPanel.add(mCancel);
		
		
		//����layout
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
