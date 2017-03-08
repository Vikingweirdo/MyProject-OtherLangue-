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
	public JFrame frame = null;	//��Ӵ���
	private JPanel mJp = null;	//�������
	private JButton mLog = null;	//��ӵ�¼��ť
	private JButton mReg = null;	//���ע�ᰴť
	private JComboBox mCbox = null;	//���������
	private JLabel mUser = null;	//�û���
	private JLabel mPass = null;	//����
	private JLabel mTitle = null;
	public JLabel mShowInfo = null;//��ʾ��½״̬
	private JTextField mName = null;//������û�����editText
	private JPasswordField mPassWord = null;//�����û������editText
	
	private Operate op = null;	//��ȡ��������
	private ResultSet result = null;
	
	public void init(){		//��ʼ������
		frame = new JFrame("��¼");	//ʵ��������
		mJp = new JPanel();	//ʵ��������
		mLog = new JButton("��¼");//ʵ������½��ť
		mReg = new JButton("ע��");//ʵ����ע�ᰴť
		mCbox = new JComboBox();//ʵ����������
		mUser = new JLabel("�û���");	//�û�����ʾ
		mPass = new JLabel("��  ��");	//������ʾ
		mTitle = new JLabel("ҽԺ����ϵͳ");
		mShowInfo = new JLabel();	//ʵ������Ϣ
		mName = new JTextField();//ʵ����EDIT
		mPassWord = new JPasswordField();//ʵ����EDIT
		
		
		mJp.setLayout(null);//�����ò��֣�ʹ�þ��Բ���
		
		//����label���Զ�λ
		mUser.setBounds(100,80,100,100);	
		mPass.setBounds(100,150,100,100);
		mShowInfo.setBounds(100,240,300,150);
		mTitle.setBounds(150,20,300,100);
		//���������С����ʽ
		mUser.setFont(new Font("����",Font.PLAIN,20));
		mPass.setFont(new Font("����",Font.PLAIN,20));
		mShowInfo.setFont(new Font("����",Font.PLAIN,20));
		mTitle.setFont(new Font("����",Font.PLAIN,30));
		//����EDIT
		mName.setBounds(180,120,100,20);
		mPassWord.setBounds(180,190,100,20);
		
		//����JComboBox
		mCbox.setBounds(300, 120, 80, 20);
		mCbox.addItem("����Ա");
		mCbox.addItem("ҽ��");
		
		//���ð�ť
		mLog.setBounds(100,250,110,30);
		mReg.setBounds(280,250,110,30);
		
		//���ð�ť����
		mLog.addActionListener(this);
		mReg.addActionListener(this);
		
		//�������������
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
		
		frame.add(mJp);	//�������
		frame.setSize(500,400);	//�����С
		frame.setLocation(800,200);	//������ʾ��λ��
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean flag = false;
		//��½��ťʵ��
		if(e.getSource() == mLog){
			//System.out.println("��½");
			String name = mName.getText();
			String pass = mPassWord.getText();
			System.out.println(name +" "+ pass);
			op.Select("select * from user;");	//ʹ��sql���
			this.result = op.getResult();	//��ȡ��ѯ�Ľ����
			System.out.println(result);
			if(name.equals("")||pass.equals("")){
				System.out.println("empty");
				mShowInfo.setText("�û��������벻��Ϊ��");
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
					System.out.println("��½�ɹ�!!!");
					mShowInfo.setText("��½�ɹ�");
					new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								Thread.sleep(2000); // ��½�ɹ�����2����ʧ

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
					mShowInfo.setText("��½ʧ�ܣ��û������������");
				}
				
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
		//ע�ᰴťʵ��
		if(mReg == e.getSource()){
			new Regist(op);	//����ע�����
		}
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		frame.dispose();
		System.gc();
		System.exit(0);
	}
	
	//��ȡ��������
	public void setOperate(Operate op){
		this.op = op;
	}
}
