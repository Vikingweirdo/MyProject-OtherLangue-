#include<graphics.h>
#include<conio.h>


int main(){
   initgraph(680,680);
   char s1[]="太原";
   char s2[]="古交";
   char s3[]="高平";
   char s4[]="介休";
   char s5[]="吕梁";
   char s6[]="原平"; 
   char s7[]="临汾";
  
   outtextxy(300,300,s1);circle(300,300,5);
   outtextxy(260,260,s2);circle(260,260,5);
   outtextxy(300,570,s3);circle(300,570,5);
   outtextxy(200,390,s4);circle(200,390,5);
   outtextxy(100,287,s5);circle(100,287,5);
   outtextxy(300,184,s6);circle(300,184,5);
   outtextxy(100,500,s7);circle(100,500,5);

   outtextxy(70,390,"213");outtextxy(170,220,"256");
   outtextxy(260,230,"153");outtextxy(310,244,"116");
   outtextxy(180,260,"138");outtextxy(270,280,"40");
   outtextxy(140,340,"109");outtextxy(210,325,"141");
   outtextxy(240,350,"134");outtextxy(310,430,"270");
   outtextxy(140,445,"145");outtextxy(240,460,"228");
   outtextxy(200,535,"177");
  
   line(300,300,300,184);
   line(300,300,300,570);
   line(300,300,200,390);
   line(300,300,260,260);
   line(260,260,300,184);
   line(260,260,200,390);
   line(200,390,300,570);
   line(300,570,100,500);
   line(200,390,100,500);
   line(200,390,100,287);
   line(260,260,100,287);
   line(100,287,100,500);
   line(260,260,300,184);
   line(100,287,300,184);

   getch();
   closegraph();
   return 0;

}
