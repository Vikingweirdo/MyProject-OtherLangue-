#include<graphics.h>
#include<conio.h>


int main(){
					initgraph(680,680);







					char s1[]="广州";
					char s2[]="清远";
					char s3[]="中山";
					char s4[]="香港";
				    char s5[]="河源";
				    char s6[]="韶关"; 
					char s7[]="肇庆";
  
					outtextxy(270,240,s1);circle(270,240,5);
					outtextxy(200,130,s2);circle(200,130,5);
					outtextxy(240,320,s3);circle(240,320,5);
					outtextxy(350,390,s4);circle(350,390,5);
					outtextxy(400,190,s5);circle(400,190,5);
					outtextxy(300,50,s6);circle(300,50,5);
					outtextxy(180,280,s7);circle(180,280,5);

					outtextxy(220,80,"175");outtextxy(165,190,"170");
					outtextxy(235,210,"81");outtextxy(285,145,"235");
					outtextxy(335,215,"194");outtextxy(305,295,"172");
					outtextxy(255,280,"87");outtextxy(190,300,"134");
					outtextxy(280,355,"122");outtextxy(375,290,"201");
					outtextxy(360,120,"220");outtextxy(240,155,"280");
					outtextxy(225,260,"108");
   

					line(270,240,200,130);   line(270,240,240,320);   
					line(270,240,400,190);   line(270,240,300,50);   
					line(270,240,180,280);   line(270,240,350,390);   
					line(200,130,300,50);   line(200,130,180,280);   
					line(180,280,300,50);   line(180,280,240,320);   
					line(240,320,350,390);   line(350,390,400,190);   
					line(300,50,400,190);   

					getch();
					closegraph();
						return 0;

}
