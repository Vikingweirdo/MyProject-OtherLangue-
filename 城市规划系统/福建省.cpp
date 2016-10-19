#include<graphics.h>
#include<conio.h>


int main(){
					initgraph(680,680);


					char s1[]="福州";
					char s2[]="南平";
					char s3[]="三明";
					char s4[]="厦门";
				    char s5[]="莆田";
				    char s6[]="福清"; 
					char s7[]="长乐";
  
					outtextxy(280,170,s1);circle(280,170,5);
					outtextxy(100,100,s2);circle(100,100,5);
					outtextxy(50,150,s3);circle(50,150,5);
					outtextxy(90,430,s4);circle(90,430,5);
					outtextxy(230,310,s5);circle(230,310,5);
					outtextxy(315,240,s6);circle(315,240,5);
					outtextxy(350,180,s7);circle(350,180,5);

					outtextxy(170,115,"171");outtextxy(105,156,"231");
				    outtextxy(170,200,"273");outtextxy(155,300,"250");
					outtextxy(255,235,"106");outtextxy(300,195,"56");
					outtextxy(315,160,"33");outtextxy(55,115,"82");
					outtextxy(45,290,"290");outtextxy(120,230,"286");
					outtextxy(160,370,"174");outtextxy(270,275,"105");
					outtextxy(335,210,"50");
   

					line(280,170,100,100);   line(280,170,50,150);  
					line(280,170,90,430);line(280,170,230,310);
					line(280,170,315,240);line(280,170,350,180);
					line(50,150,100,100);line(50,150,90,430);
					line(50,150,230,310);line(90,430,230,310);
					line(230,310,315,240);line(315,240,350,180);
					line(100,100,230,310);

					getch();
					closegraph();
						return 0;

}
