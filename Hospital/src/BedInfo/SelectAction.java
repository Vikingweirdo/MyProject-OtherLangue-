package BedInfo;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import Connect.Operate;

// 总查询窗口，用来选择查询标准
class SelectAction extends JFrame{

	JRadioButton rbbedno = new JRadioButton("病床号"); // 创建‘病床号’单选按钮
	JRadioButton rbroomno = new JRadioButton("病房号"); // 创建‘病房号’单选按钮
	JRadioButton rbpatientno = new JRadioButton("病人号"); // 创建‘病人号’单选按钮
	JRadioButton rbroomno_bedno = new JRadioButton("病房号+病床号"); // 创建‘病房号+病人号’单选按钮
	Label lsymbol = new Label("请选择查询标准:"); // 创建 标识 以 直观解释操作内容
	ButtonGroup bgselect = new ButtonGroup(); // 创建按钮组，为了装载单选按钮
	
	//数据准备 
	private Operate op = null;

	public void Init() {

		setTitle("查询信息");
		setSize(400, 300); // 设置界面大小
		setLocation(400, 100);
		setLayout(null); // 无布局

		lsymbol.setBounds(0, 0, 100, 30); // 设置 标识 在界面 的位置
		rbbedno.setBounds(0, 30, 100, 30); // 设置 ‘病床号’按钮 在界面 的位置
		rbroomno.setBounds(0, 60, 100, 30); // 设置 ‘病房号’按钮 在界面 的位置
		rbpatientno.setBounds(0, 90, 100, 30); // 设置 ‘病人号’按钮 在界面 的位置
		rbroomno_bedno.setBounds(0, 120, 200, 30); // 设置 ‘病床号+病房号’按钮 在界面 的位置

		add(lsymbol); // 界面加入标识
		bgselect.add(rbbedno); // 按钮组加入 ‘病床号’ 按钮 表示 单选 下面三个 作用跟此相同
		bgselect.add(rbroomno);
		bgselect.add(rbpatientno);
		bgselect.add(rbroomno_bedno);
		add(rbbedno); // 窗体加入 “病床号”按钮，表示可见 下同
		add(rbroomno);
		add(rbpatientno);
		add(rbroomno_bedno);

		// “病床号”按钮按下发生事件
		rbbedno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				BednoSelect bednoaction = new BednoSelect();
				bednoaction.setOperate(op);
				bednoaction.Init();
			}
		});

		// “病房号”按钮按下 发生 事件
		rbroomno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				RoomnoSelect roomnoaction = new RoomnoSelect();
				roomnoaction.setOperate(op);
				roomnoaction.Init();
			}
		});

		// “病人号”按钮 按下 发生事件
		rbpatientno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				PatientnoSelect paientselect = new PatientnoSelect();
				paientselect.setOperate(op);
				paientselect.Init();
			}
		});

		// “病房号”按钮 按下 发生事件
		rbroomno_bedno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				RoomnoAndBednoSelect roomno_bednoselect = new RoomnoAndBednoSelect();
				roomno_bednoselect.setOperate(op);
				roomno_bednoselect.Init();
			}
		});

		setVisible(true); // 窗体可见
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭窗口时将该资源都释放
	}

	//获取数据库对象
	public void setOperate(Operate op){
		this.op = op;
	}

}