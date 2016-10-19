#include<graphics.h>
#include<conio.h>


int main(){
					initgraph(680,680);

					char s1[]="成都";
					char s2[]="雅安";
					char s3[]="乐山";
					char s4[]="内江";
				    char s5[]="南充";
				    char s6[]="绵阳"; 
					char s7[]="汶川";
  
					outtextxy(140,190,s1);circle(140,190,5);
					outtextxy(90,338,s2);circle(90,338,5);
					outtextxy(110,400,s3);circle(110,400,5);
					outtextxy(310,390,s4);circle(310,390,5);
					outtextxy(390,170,s5);circle(390,170,5);
					outtextxy(280,110,s6);circle(280,110,5);
					outtextxy(100,100,s7);circle(100,100,5);

					outtextxy(70,220,"238");outtextxy(115,265,"144");
					outtextxy(225,270,"192");outtextxy(235,180,"255");
					outtextxy(210,150,"122");outtextxy(120,135,"149");
					outtextxy(100,368,"123");outtextxy(220,395,"173");
					outtextxy(185,365,"269");outtextxy(295,210,"261");
					outtextxy(350,280,"215");outtextxy(340,130,"196");
					outtextxy(190,90,"186");
   

					line(140,190,90,338);   line(140,190,310,390);
					line(140,190,390,170);  line(140,190,280,110);
					line(140,190,100,100);  line(100,100,90,338);
					line(100,100,280,110);  line(280,110,390,170);
					line(390,170,310,390);  line(310,390,280,110);
					line(90,338,310,390);   line(90,338,110,400);
					line(110,400,310,390);

					getch();
					closegraph();
						return 0;

}
