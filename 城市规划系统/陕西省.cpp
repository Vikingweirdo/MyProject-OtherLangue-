#include<graphics.h>
#include<conio.h>


int main(){
   initgraph(680,750);
   char s1[]="西安";
   char s2[]="咸阳";
   char s3[]="延安";
   char s4[]="渭南";
   char s5[]="汉中";
   char s6[]="宝鸡"; 
   char s7[]="安康";
  
   outtextxy(270,380,s1);circle(270,380,5);
   outtextxy(250,360,s2);circle(250,360,5);
   outtextxy(320,50,s3);circle(320,50,5);
   outtextxy(330,360,s4);circle(330,360,5);
   outtextxy(50,660,s5);circle(50,660,5);
   outtextxy(60,440,s6);circle(60,440,5);
   outtextxy(300,680,s7);circle(300,680,5);

   outtextxy(180,200,"465");outtextxy(255,230,"337");
   outtextxy(290,245,"330");outtextxy(325,200,"308");
   outtextxy(150,380,"178");outtextxy(250,375,"26");
   outtextxy(300,370,"64");outtextxy(160,520,"338");
   outtextxy(315,510,"328");outtextxy(130,490,"240");
   outtextxy(55,550,"219");outtextxy(260,520,"303");
   outtextxy(175,670,"257");
   
   line(270,380,320,50);line(270,380,330,360);
   line(270,380,250,360);line(270,380,300,680);
   line(320,50,60,440);line(270,380,50,660);
   line(250,360,50,660);line(60,440,50,660);
   line(250,360,60,440);line(320,50,330,360);
   line(330,360,300,680);line(320,50,250,360);
   line(50,660,300,680);
   

   getch();
   closegraph();
   return 0;

}
