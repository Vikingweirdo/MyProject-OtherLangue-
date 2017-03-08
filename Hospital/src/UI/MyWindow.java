package UI;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import java.lang.Thread;

import Connect.Operate;
public class MyWindow extends WindowAdapter implements ActionListener {
	public JFrame frame = null;	//添加窗口
	private JPanel mJp = null;	//添加容器
	private JButton mLog = null;	//添加登录按钮
	private JButton mReg = null;	//添加注册按钮
	private JComboBox mCbox = null;	//添加下拉框
	private JLabel mUser = null;	//用户名
	private JLabel mPass = null;	//密码
	private JLabel mTitle = null;
	public JLabel mShowInfo = null;//显示登陆状态
	private JTextField mName = null;//输入的用户名的editText
	private JPasswordField mPassWord = null;//输入用户密码的editText
	
	private Operate op = null;	//获取操作对象
	private ResultSet result = null;
	
	public void init(){		//初始化窗口
		frame = new JFrame("登录");	//实例化窗口
		mJp = new JPanel();	//实例化容器
		mLog = new JButton("登录");//实例化登陆按钮
		mReg = new JButton("注册");//实例化注册按钮
		mCbox = new JComboBox();//实例化下拉框
		mUser = new JLabel("用户名");	//用户名提示
		mPass = new JLabel("密  码");	//密码提示
		mTitle = new JLabel("医院病房系统");
		mShowInfo = new JLabel();	//实例化信息
		mName = new JTextField();//实例化EDIT
		mPassWord = new JPasswordField();//实例化EDIT
		
		
		mJp.setLayout(null);//不设置布局，使用绝对布局
		
		//设置label绝对定位
		mUser.setBounds(100,80,100,100);	
		mPass.setBounds(100,150,100,100);
		mShowInfo.setBounds(100,240,300,150);
		mTitle.setBounds(150,20,300,100);
		//设置字体大小，样式
		mUser.setFont(new Font("隶书",Font.PLAIN,20));
		mPass.setFont(new Font("隶书",Font.PLAIN,20));
		mShowInfo.setFont(new Font("隶书",Font.PLAIN,20));
		mTitle.setFont(new Font("隶书",Font.PLAIN,30));
		//设置EDIT
		mName.setBounds(180,120,100,20);
		mPassWord.setBounds(180,190,100,20);
		
		//设置JComboBox
		mCbox.setBounds(300, 120, 80, 20);
		mCbox.addItem("管理员");
		mCbox.addItem("医生");
		
		//设置按钮
		mLog.setBounds(100,250,110,30);
		mReg.setBounds(280,250,110,30);
		
		//设置按钮监听
		mLog.addActionListener(this);
		mReg.addActionListener(this);
		
		//设置下拉框监听
		mCbox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String item = (String)cb.getSelectedItem();
				System.out.println(item);
				
			}});
		
		mJp.add(mUser);
		mJp.add(mPass);
		mJp.add(mName);
		mJp.add(mPassWord);
		//mJp.add(mCbox);
		mJp.add(mLog);
		mJp.add(mReg);
		mJp.add(mShowInfo);
		mJp.add(mTitle);
		
		frame.add(mJp);	//添加容器
		frame.setSize(500,400);	//窗体大小
		frame.setLocation(800,200);	//设置显示的位置
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean flag = false;
		//登陆按钮实现
		if(e.getSource() == mLog){
			//System.out.println("登陆");
			String name = mName.getText();
			String pass = mPassWord.getText();
			System.out.println(name +" "+ pass);
			op.Select("select * from user;");	//使用sql语句
			this.result = op.getResult();	//获取查询的结果集
			System.out.println(result);
			if(name.equals("")||pass.equals("")){
				System.out.println("empty");
				mShowInfo.setText("用户名和密码不能为空");
				return;
			}
			//System.out.println("123");
			try {
				while(result.next()){
					if(name.equals(result.getString("username"))&&
							pass.equals(result.getString("password"))){
						flag = true;
						break;
					}else{
						flag = false;
					}
				}
				
				if (flag == true) {
					System.out.println("登陆成功!!!");
					mShowInfo.setText("登陆成功");
					new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								Thread.sleep(2000); // 登陆成功后窗体2秒消失

								MainMenu mainMenu = new MainMenu();
								mainMenu.setOperate(op);
								//System.out.println(mainMenu.getOperate()+"++++");
								frame.dispose();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					}).start();
				} else {
					mShowInfo.setText("登陆失败，用户名或密码错误");
				}
				
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
		//注册按钮实现
		if(mReg == e.getSource()){
			new Regist(op);	//出现注册表项
		}
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		frame.dispose();
		System.gc();
		System.exit(0);
	}
	
	//获取操作对象
	public void setOperate(Operate op){
		this.op = op;
	}
}
