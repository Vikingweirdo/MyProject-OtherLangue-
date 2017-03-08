package UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import Connect.Operate;
import java.lang.Thread;

public class Regist extends WindowAdapter implements ActionListener {
	
	public JFrame frame = null;	//窗体组建
	private JPanel mJp = null;	//画板
	private JLabel mUser = null;	//用户名提示
	private JLabel mPass = null;	//密码提示
	private JLabel mSurePa = null;	//确认密码提示
	private JLabel mShowInfo = null;	//显示状态信息
	private JButton mSure = null;	//确定按钮
	private JButton mCancel = null ;//取消按钮
	private JTextField mEdiName = null;//用户输入EDIT
	private JPasswordField mEdiPass = null;//密码输入EDIT
	private JPasswordField mSurePass = null;//确认密码EDIT
	private JComboBox mCbox = null;	//添加下拉框
	
	private Operate op = null;
	
	public Regist(Operate op){
		
		//实例化各个组件
		frame = new JFrame("注册");
		mJp = new JPanel();
		mUser = new JLabel("用户名");
		mPass = new JLabel("密  码");
		mSurePa = new JLabel("确认密码");
		mShowInfo = new JLabel();
		mSure = new JButton("确定");
		mCancel = new JButton("取消");
		mEdiName = new JTextField();
		mEdiPass = new JPasswordField();
		mSurePass = new JPasswordField();
		mCbox = new JComboBox();//实例化下拉框
		
		this.op = op;
		
		//设置标签绝对布局
		mUser.setBounds(100,80,100,100);
		mPass.setBounds(100,150,100,100);
		mSurePa.setBounds(100,210,100,100);
		mShowInfo.setBounds(100,330,300,150);
		
		//设置字体样式
		mUser.setFont(new Font("隶书",Font.PLAIN,20));
		mPass.setFont(new Font("隶书",Font.PLAIN,20));
		mSurePa.setFont(new Font("隶书",Font.PLAIN,20));
		mShowInfo.setFont(new Font("隶书",Font.PLAIN,20));
		
		
		//设置按钮定位
		mSure.setBounds(100,330,100,30);
		mCancel.setBounds(210,330,100,30);
		
		//设置EDIT定位
		mEdiName.setBounds(200,120,100,20);
		mEdiPass.setBounds(200,190,100,20);
		mSurePass.setBounds(200,250,100,20);
		
		//添加下拉框
		mCbox.setBounds(100, 370, 80, 20);
		mCbox.addItem("管理员");
		mCbox.addItem("医生");
		
		//添加按钮监听
		mSure.addActionListener(this);
		mCancel.addActionListener(this);
		
		// 设置下拉框监听
		mCbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String item = (String) cb.getSelectedItem();
				System.out.println(item);

			}
		});
		
		//设置布局为null
		mJp.setLayout(null);
		
		//添加组件
		mJp.add(mUser);
		mJp.add(mPass);
		mJp.add(mSurePa);
		mJp.add(mShowInfo);
		
		mJp.add(mSure);
		mJp.add(mCancel);
		
		mJp.add(mEdiName);
		mJp.add(mEdiPass);
		mJp.add(mSurePass);
		
		mJp.add(mCbox);
		
		frame.add(mJp);
		
		frame.setTitle("注册");
		frame.setSize(400,500);
		frame.setLocation(900,200);
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == mSure){
			String name = mEdiName.getText();
			String pass = mEdiPass.getText();
			String checkPass = mSurePass.getText();
			//不能为空
			if(isEmpty(name,pass)){
				mShowInfo.setText("用户名和密码不能为空");
				return ;
			}
			//密码是否一致
			if(isComent(pass,checkPass)){
				op.Insert("insert into user values(null,\""+name+"\",\""+pass+"\");");
				//System.out.println("insert into user values(\""+name+"\",\""+pass+"\");");
				mShowInfo.setText("注册成功");
				new Thread(new Runnable(){

					@Override
					public void run() {
						try {
							Thread.sleep(2000);
							frame.dispose();
							System.gc();
							//System.exit(0);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
					}
					
					
				}).start();
			}else{
				mShowInfo.setText("用户名和密码不一致");
			}
		}
		
		if(e.getSource() == mCancel){
			frame.dispose();
			
		}
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		frame.dispose();
		System.gc();
		System.exit(0);
		
	}
	
	//查询是否为空
	public boolean isEmpty(String name , String pass){
		
		if(name.equals("")||pass.equals("")){
			System.out.println("is empty");
			return true;
		}
		
		return false;
	}
	
	//验证两次密码是否一致
	public boolean isComent(String firstPass , String secondPass){
		if(firstPass.equals(secondPass)){
			return true;
		}
		return false;
	}
}
