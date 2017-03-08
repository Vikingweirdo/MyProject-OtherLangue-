package UI;
import javax.swing.*;

import Connect.Operate;
import HouseMenu.SearchResult;
import PaDoInfo.DoctorUI;
import BedInfo.*;


public class MainActivity {
	private static MyWindow myWin = null;
	private static MainMenu mMenu = null;
	private static Operate op = new Operate();//数据库操作对象
	public static void main(String[] args){
			
		myWin = new MyWindow();
		myWin.init();	//显示窗口
		myWin.setOperate(op);
		
		//mMenu = new MainMenu();
		//SearchResult sr = new SearchResult();
		//sr.Init();
		//DoctorUI doctor = new DoctorUI();
		//doctor.Init();
		
		/*String[][] strss = new String[100][4];
		for (int i = 0 ; i<100 ; i++)
		{
			for (int j = 0 ; j<4; j++)
			{
				strss[i][j] = i+" "+j;
			}
		}
		SumMenu summenu = new SumMenu(strss);*/
		
	}
	public static Operate getOperate(){
		return op;
	}
}
