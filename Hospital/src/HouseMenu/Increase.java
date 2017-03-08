package HouseMenu;

import javax.swing.*;

import Connect.Operate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Increase {
	
	//新建窗口，模板，字体，标签，按钮，文本框；
	JFrame jF;
	JPanel jP;
	Font font;
	JLabel jL1;
	JLabel jL2;
	JLabel jL3;
	JButton jB1;
	JButton jB2;
	JTextField jText1;
	JTextField jText2;
	JTextField jText3;
	private JLabel mShowInfo = null;
	
	private Operate op = null;
	
	public void Init(){
		//实例化窗口，模板，字体，标签，按钮，文本框；
		jF = new JFrame("病房的增加");
		jP = new JPanel();
		font = new Font("Serief",Font.BOLD,20);
		jL1 = new JLabel("病  房 号:");
		jL2 = new JLabel("科       室:");
		jL3 = new JLabel("病  床  数:");
		mShowInfo = new JLabel();
		jB1 = new JButton("确认");
		jB2 = new JButton("返回");
		jText1 = new JTextField();
		jText2 = new JTextField();
		jText3 = new JTextField();
		//设置标签，单选按钮，按钮，文本框，的位置
		jL1.setBounds(50,50,150,100);
		jL2.setBounds(50,90,100,100);
		jL3.setBounds(50,130,180,100);
		jB1.setBounds(50,200,80,30);
 		jB2.setBounds(148,200,80,30);
 		mShowInfo.setBounds(50,250,180,20);
		jText1.setBounds(150,90,70,20);
		jText2.setBounds(150,130,70,20);
		jText3.setBounds(150, 170, 70, 20);
		//设置标签的字体；
		jL1.setFont(font);
		jL2.setFont(font);
		jL3.setFont(font);
		mShowInfo.setFont(font);
		//将布局格式设置为绝对布局，并将标签，单选按钮，文本框，按钮添加进模版，将模板加进窗口；
		jP.setLayout(null);
		jP.add(jL1);
		jP.add(jL2);
		jP.add(jL3);
		jP.add(mShowInfo);
		jP.add(jText1);
		jP.add(jText2);
		jP.add(jText3);
		jP.add(jB1);
		jP.add(jB2);
		jF.add(jP);
		jF.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jF.setResizable(false);
		jF.setLocation(900,300);
		jF.setSize(300,400);
		jF.setVisible(true);
		//“确定”按钮的事件监听
		jB1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				if (jText1.getText().equals("") || jText2.getText().equals("")
						|| jText3.getText().equals("")) {
					mShowInfo.setText("输入内容不能为空");
				} else {

					/*System.out.println(jText1.getText());
					System.out.println(jText2.getText());
					System.out.println(jText3.getText());*/
					InsertDB(jText1.getText(),
							jText3.getText(),
							jText2.getText());
					jF.dispose();
					SearchResult.jF.dispose();
					SearchResult searchResult = new SearchResult();
					searchResult.setOperate(op);
					searchResult.Init();
				}
				
			}});
		//“返回”按钮的事件监听
		jB2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				jF.dispose();		
			}});
	}
	
	//获取操作数据库对象
	public void setOperate(Operate op){
		this.op = op;
	}
	
	// 插入数据库
	public void InsertDB(String hosID, String BedSum, String Dodeparment) {
		op.Insert("insert into house values(\"" + hosID + "\"," + BedSum + ",\""
				+ Dodeparment + "\");");
		mShowInfo.setText("增加成功");
	}
}
