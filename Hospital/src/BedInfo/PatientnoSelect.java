package BedInfo;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connect.Operate;

//根据病人号查询窗口
class PatientnoSelect extends JFrame{

	Box patientnoBox;
	Box patientnotext;
	Box patientnoboxbase; // 使用 盒式布局 的容器

	JTextField tfpatientno = new JTextField(10); // 构造一个具有10列的新的空文本框

	JButton bpatientnocertain = new JButton("确定"); // 创建“确定”按钮
	JButton bpatientnoclear = new JButton("取消"); // 创建“取消”按钮
	JPanel ppatientno = new JPanel(); // 创建面板
	
	private Operate op = null;

	public void Init() {

		setTitle("病人号查询");
		setBounds(100, 100, 310, 260); // 界面设置大小
		add(ppatientno); // 界面 套入面板
		patientnoBox = Box.createVerticalBox(); // 表示 盒式布局 的列式 布局 的 容器
		patientnoBox.add(new Label("病人号:")); // 容器加入 病房号 按钮
		patientnoBox.add(Box.createVerticalStrut(16)); // 创建高度为16的不可见组件
		patientnoBox.add(bpatientnocertain); // 容器加入 按钮
		patientnotext = Box.createVerticalBox(); // 使用 列式布局 容器
		patientnotext.add(tfpatientno); // 容器加入 该文本框
		patientnotext.add(Box.createVerticalStrut(16)); // 创建高度为16的不可见组件
		patientnotext.add(bpatientnoclear); // 容器加入 “取消按钮”
		patientnoboxbase = Box.createHorizontalBox(); // 使用 盒式布局 的容器
		patientnoboxbase.add(patientnoBox); // 该容器中加入 以上 容器
		patientnoboxbase.add(Box.createHorizontalStrut(16)); // 创建宽度为 16 的不可见组件
		patientnoboxbase.add(patientnotext); // 加入以上容器
		ppatientno.add(patientnoboxbase); // 面板中加入总容器
		setVisible(true); // 可见
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 按确定按钮 发生事件
		bpatientnocertain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfpatientno.getText().equals("")) {
					JFrame fnull = new JFrame();
					fnull.setSize(100, 100);
					fnull.setLocation(100, 20);
					fnull.add(new Label("文本框不能为空！！"));
					fnull.setVisible(true);
				} else {
					dispose();
					InquryResult qury = new InquryResult();
					qury.setOperate(op);
					qury.setTableFlag("PaId");
					qury.setEditText(tfpatientno.getText());
					qury.Inint();
				} // 根据病人号查询结果
			}
		});

		// 取消 按钮 按下 发生事件
		bpatientnoclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

	}
	
	//获取操作对象
	public void setOperate(Operate op){
		this.op = op;
	}
}
