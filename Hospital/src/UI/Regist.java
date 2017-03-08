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
	
	public JFrame frame = null;	//�����齨
	private JPanel mJp = null;	//����
	private JLabel mUser = null;	//�û�����ʾ
	private JLabel mPass = null;	//������ʾ
	private JLabel mSurePa = null;	//ȷ��������ʾ
	private JLabel mShowInfo = null;	//��ʾ״̬��Ϣ
	private JButton mSure = null;	//ȷ����ť
	private JButton mCancel = null ;//ȡ����ť
	private JTextField mEdiName = null;//�û�����EDIT
	private JPasswordField mEdiPass = null;//��������EDIT
	private JPasswordField mSurePass = null;//ȷ������EDIT
	private JComboBox mCbox = null;	//���������
	
	private Operate op = null;
	
	public Regist(Operate op){
		
		//ʵ�����������
		frame = new JFrame("ע��");
		mJp = new JPanel();
		mUser = new JLabel("�û���");
		mPass = new JLabel("��  ��");
		mSurePa = new JLabel("ȷ������");
		mShowInfo = new JLabel();
		mSure = new JButton("ȷ��");
		mCancel = new JButton("ȡ��");
		mEdiName = new JTextField();
		mEdiPass = new JPasswordField();
		mSurePass = new JPasswordField();
		mCbox = new JComboBox();//ʵ����������
		
		this.op = op;
		
		//���ñ�ǩ���Բ���
		mUser.setBounds(100,80,100,100);
		mPass.setBounds(100,150,100,100);
		mSurePa.setBounds(100,210,100,100);
		mShowInfo.setBounds(100,330,300,150);
		
		//����������ʽ
		mUser.setFont(new Font("����",Font.PLAIN,20));
		mPass.setFont(new Font("����",Font.PLAIN,20));
		mSurePa.setFont(new Font("����",Font.PLAIN,20));
		mShowInfo.setFont(new Font("����",Font.PLAIN,20));
		
		
		//���ð�ť��λ
		mSure.setBounds(100,330,100,30);
		mCancel.setBounds(210,330,100,30);
		
		//����EDIT��λ
		mEdiName.setBounds(200,120,100,20);
		mEdiPass.setBounds(200,190,100,20);
		mSurePass.setBounds(200,250,100,20);
		
		//���������
		mCbox.setBounds(100, 370, 80, 20);
		mCbox.addItem("����Ա");
		mCbox.addItem("ҽ��");
		
		//��Ӱ�ť����
		mSure.addActionListener(this);
		mCancel.addActionListener(this);
		
		// �������������
		mCbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String item = (String) cb.getSelectedItem();
				System.out.println(item);

			}
		});
		
		//���ò���Ϊnull
		mJp.setLayout(null);
		
		//������
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
		
		frame.setTitle("ע��");
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
			//����Ϊ��
			if(isEmpty(name,pass)){
				mShowInfo.setText("�û��������벻��Ϊ��");
				return ;
			}
			//�����Ƿ�һ��
			if(isComent(pass,checkPass)){
				op.Insert("insert into user values(null,\""+name+"\",\""+pass+"\");");
				//System.out.println("insert into user values(\""+name+"\",\""+pass+"\");");
				mShowInfo.setText("ע��ɹ�");
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
				mShowInfo.setText("�û��������벻һ��");
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
	
	//��ѯ�Ƿ�Ϊ��
	public boolean isEmpty(String name , String pass){
		
		if(name.equals("")||pass.equals("")){
			System.out.println("is empty");
			return true;
		}
		
		return false;
	}
	
	//��֤���������Ƿ�һ��
	public boolean isComent(String firstPass , String secondPass){
		if(firstPass.equals(secondPass)){
			return true;
		}
		return false;
	}
}
