#include<graphics.h>
#include<conio.h>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#define M 20
#define F 5000
typedef struct Graph{			//定义图的结构体
	char b[M][M];
	int n ,  e; 
	int edges[M][M];
}Graph;

typedef struct EdgePrim{		//prim算法结构体
  int beg,en;					//储存最小生成树的起始和终止节点
  int length;					//最小生成树的长度
}edge;
typedef struct EdgeKruskal{		//kruskal算法结构体
  int u,v;						//记录有边的起始节点和终止节点
  int power;					//边的权值
}Edge;
	
edge tree[M];					//定义最小生成树数组

Graph G;						//定义全局图变量

void Creat(char *name){
	FILE *fp;					//指向文件的指针
	int m = 0;
	char a[10];					//储存地点名字
	fp = fopen(name,"r");		//打开名字为name的文件		
					
	int i,  begin , end , pow  , count=0;

	printf("Input the information of the City\n");

	fscanf(fp,"%s",a);			//输入城市的名字

	while (strcmp(a,"end")!= 0){		//输入end就停止输入城市名字信息
	

		strcpy(G.b[count],a);		//使用字符串复制把a数组的城市名字复制到b数组中
				
		printf("Input the information of the City\n");

		fscanf(fp,"%s",a);			//再次输入城市名字

		count++;					//统计城市输入的个数
	}

	G.n = count;					//统计城市的个数
	
	fflush(stdin);					//清除缓存区
	
	for (int k = 0;k<G.n ;k++ ){	//通过for循环初始化邻接矩阵
	
		for (int l = 0;l < G.n ;l++ ){
		
			if (k == l){			//使对角线的值为0
			
				G.edges[k][l] = 0;
			}
			else{					//其余的地方为无穷大
				G.edges[k][l] = F;
			}
		}
	}
	
	printf("The number of the City is %d:\n",G.n);
	
	printf("Input the number of the road:\n");
	
	fscanf(fp,"%d",&G.e);			//文件读入边
	
	printf("%d\n",G.e);
	
	if (G.e == 0){					//如果边数为0，那么出错
		
		printf("Error!!\n");
		
		return ;
	}
	
	fflush(stdin);
	
	for(i = 0 ; i < G.e ; i++){		//图的边循环完成邻接矩阵的输入
		
		printf("Input the information of the road ; begining and ending :\n");	//输入哪两个城市有路
		
		fscanf(fp,"%d",&begin);
		
		fscanf(fp,"%d",&end);
		
		printf("Input the pow of the road:\n");		//输入路的代价（权值）
		
		fscanf(fp,"%d",&pow);
		
		G.edges[begin][end] = pow;					//无向图邻接矩阵生成
		
		G.edges[end][begin] = pow;
	}

	fclose(fp);
}
void print(){					//输入邻接矩阵图
	
	int i , j , k , m = 0 ;
	
	while(m<G.n){				//输出城市的名字
		
		printf("%s\n",G.b[m]);
		
		m++;
	}
	for (i = 0 ; i < G.n ; i++){		//输出邻接矩阵
		
		k = 1 ; 
		
		for (j = 0 ; j < G.n ; j++){
			
			printf("%5d",G.edges[i][j]);	//%5d是控制两个输出结果之间空5个空格
			
			if (k == G.n)printf("\n");		//控制每输出顶点数个数时候换行
			
			k++;
		}
	
	}


}
int Prim(){
	
	edge temp;				//用来排序tree[]数组
	
	int i , j ,d , min , k ,s , v;
	
	int built;				//任意城市创建最小生成树
	
	printf("Input the number you want to bulit:\n");
	
	scanf("%d",&built);
	
	built--;				//人为人物第0个城市便是第一个城市
	
	fflush(stdin);
	
	for (i = 0; i<G.n ; i++){	//初始化tree数组
	
		tree[i].beg = built;	
		
		tree[i].en = i;
		
		tree[i].length=G.edges[built][i];
	}
	
	for (k = 0 ;k<G.n ;k++ ){		
		
		min = tree[k].length;
		
		s = k;
		
		for (j = k+1;j< G.n;j++){		//找到每趟循环最小的边
		
			if (min > tree[j].length){
					
					min = tree[j].length;
					s = j;			
				}
		}

		v  = tree[s].en;				//记录下最小边的终止节点
		
		temp = tree[s];					//通过交换使最小边靠前，同时防止出现v0---v0的情况发生
		
		tree[s] = tree[k];
		
		tree[k] = temp;					
		
		for (j = k+1;j<G.n ;j++){		//通过循环来更新tree数组的权值
		
			d = G.edges[v][tree[j].en];	
			
			if (d<tree[j].length){		//此for循环用来比较此节点邻接矩阵中的距离和上一个节点邻接矩阵的距离存不存在最短
			
				tree[j].length = d;		//存在则更新掉tree数组的权值和边初始节点
				tree[j].beg = v;
			}
		}
	}
	
	printf("********\n");
	
	for (j = 1;j< G.n ;j++ ){		//j = 0存储初始节点到初始节点的距离，所以从j = 1开始
	
		printf("%s---%s %d\n",G.b[tree[j].beg],G.b[tree[j].en],tree[j].length);
	}
	
	int sum = 0;

	for (j = 0;j< G.n ; j++){		//求生成树的所有代价和
	
		sum+=tree[j].length;
	}
	
	printf("The Cicy of start is: %s\n",G.b[built]);

	return sum;

}
int Kruskal(){
	
	Edge E[M*M];				//用来储存图的所有边，有M个节点最大有M*M条边
	
	int i , j , k ,s1 , s2 , mul = 0;
	
	int vset[M];				//储存每个节点的连通分量
	
	k = 0 ;
	
	for (i = 0;i<G.n ;i++ ){	//for循环来默认使每个节点的连通分量等于他的顶点序号如v0 = 0；
	
		vset[i] = i;			
	}
	for (i = 0;i<G.n ; i++){	//for循环记录下所有边并存在E数组中
	
		for (j = 0 ;j < G.n ;j++ ){
		
			if (G.edges[i][j] != 0 && G.edges[i][j]< F){
			
				E[k].u = i;
				E[k].v = j;
				E[k].power = G.edges[i][j];
				k++;
			}

		}
	}
	
	Edge temp;				//冒泡排序法的交换变量
	
	for (i = 0;i<k ; i++){	//冒泡排序把边权值进行升序排序
	
		for (j = 0 ;j < k ;j++ ){
		
			if (E[i].power<E[j].power){
			
				temp = E[i];
				E[i] = E[j];
				E[j] = temp;
			}
		}
	}

	k = 1;			//控制扫描的顶点数因为0----0没有边，所以从第一个顶点开始
	
	j = 0;			//控制扫描E数组
	
	while (k<G.n){	//扫描全部顶点
	
		s1 = vset[E[j].u];		//记录此初始顶点的连通分量
		
		s2 = vset[E[j].v];		//记录此终止顶点的连通分量
		
		if (s1 != s2){			//如果连通分量不相等则为生成树的一条边
		
			printf("%s---->%s %d\n",G.b[E[j].u],G.b[E[j].v],E[j].power);//输出此时的顶点和最小边
			
			k++;				
			mul+=E[j].power;	//求权值和
			for (i = 0;i<G.n ;i++ )	{	//把刚生成的边两点权值刷新一样
			
				if (vset[i] == s2)
				{
					vset[i] = s1;
				}
			}
		}
		j++;
	}
	printf("The Cicy of start is:%s\n",G.b[0]);		//输出开始城市

	return mul;
}

int Find(){				//查找最小代价的两个城市
	
	int i , j , k , min ;
	
	k = 0;
	
	Edge E[M*M];		//用来储存边的信息
	
	Edge temp;			//排序的中间变量
	
	for (i = 0;i<G.n ; i++)	//初始化E数组
	{
		for (j = 0 ;j < G.n ;j++ )
		{
			if (G.edges[i][j] != 0 && G.edges[i][j]< F)
			{
				E[k].u = i;
				E[k].v = j;
				E[k].power =G.edges[i][j];
	
				k++;
			}

		}
	}

	for (i = 0;i<k ; i++)//冒泡排序
	{
		for (j = 0 ;j < k ;j++ )
		{
			if (E[i].power<E[j].power)
			{
				temp = E[i];
				E[i] = E[j];
				E[j] = temp;
			}
		}
	}
	
	min = E[0].power;		//排序完后E[0]储存最短的边
	printf("%s------%s \n",G.b[E[0].u],G.b[E[0].v]);
	return min;

}

void Menu(){
	printf("*********************************************************\n");
	printf("*~~~~~~~Welcome to city planning system~~~~~~~~~~~~~~~~~*\n");
	printf("* 0、Input the picture                                  *\n");
	printf("* 1、List the plans of the provinces                    *\n");//城市清单
	printf("* 2、Capital query                                      *\n");//投入资金查询
	printf("* 3、Financial Review                                   *\n");//资金复查
	printf("* 4、Two city minimum funding query                     *\n");//最少城市投入
	printf("* 5、City rules matrix					*\n");				  //城市规划矩阵图
	printf("* 6、Exit                                               *\n");
	printf("*                                                       *\n");
	printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	printf("*********************************************************\n");
}

int main(){
	Menu();
	char Provinces_name[5][10];
	char name[10];
	int Finance , sum , mul , min;
	FILE *rf;
	rf = fopen("Provinces.txt","r");
	int i ;
	for (i = 0;i<5 ;i++ )
	{
		fscanf(rf,"%s",Provinces_name[i]);
	}
	int num;
	while (1)
	{	
		scanf("%d",&num);
	
		switch (num)
		{
		
		case 0:
				gets(name);
				
				if (strcmp(name,"山西省")== 0){
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
				
				}else if (strcmp(name,"end")==0){
					printf("Input the number\n");
					break;
				}
				else if (strcmp(name,"陕西省")== 0){
					
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
					
				}else if (strcmp(name,"广东省")== 0){
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
						
				}else if (strcmp(name,"福建省")== 0){
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
					
				}else if (strcmp(name,"四川省")== 0){
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
					
				}
			printf("*************\n");break;
		
		case 1:for (i = 0;i<5 ;i++ ){
				
					printf("%s\n",Provinces_name[i]);
				}
			
			printf("*************\n");break;
	
		case 2:printf("Input The name of the Province:\n");
				gets(name);
				
				if (strcmp(name,"山西省")== 0){
						Creat("Shanxi_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						sum = Prim();
						printf("Minimum capital investment is : %d\n",sum);
						if (sum <= Finance){
						
							printf("Capital surplus\n");	//资金盈余
						}else{
							printf("Capital loss\n");	//资金亏损
						}
				}else if (strcmp(name,"陕西省")== 0){
					
						Creat("Sanxi_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						sum = Prim();
						printf("Minimum capital investment is : %d\n",sum);
						if (sum <= Finance){
						
							printf("Capital surplus\n");	//资金盈余
						}else{
							printf("Capital loss\n");	//资金亏损
						}
				}else if (strcmp(name,"广东省")== 0){
					
						Creat("Guangdong_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						sum = Prim();
						printf("Minimum capital investment is : %d\n",sum);
						if (sum <= Finance){
						
							printf("Capital surplus\n");	//资金盈余
						}else{
							printf("Capital loss\n");	//资金亏损
						}
				}else if (strcmp(name,"福建省")== 0){
					
						Creat("Fujian_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						sum = Prim();
						printf("Minimum capital investment is : %d\n",sum);
						if (sum <= Finance){
						
							printf("Capital surplus\n");	//资金盈余
						}else{
							printf("Capital loss\n");	//资金亏损
						}
				}else if (strcmp(name,"四川省")== 0){
					
						Creat("Sichuan_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						sum = Prim();
						printf("Minimum capital investment is : %d\n",sum);
						if (sum <= Finance){
						
							printf("Capital surplus\n");	//资金盈余
						}else{
							printf("Capital loss\n");	//资金亏损
						}
				}
			
				printf("*************\n");break;
		
		case 3:printf("Input The name of the Province:\n");
				gets(name);
				if (strcmp(name,"山西省")== 0){
						Creat("Shanxi_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						mul = Kruskal();
						printf("Minimum capital investment : %d\n",mul);
						if (mul <= Finance){
						
							printf("Capital surplus\n");	//资金盈余
						}else{
							printf("Capital loss\n");	//资金亏损
						}
				}else if (strcmp(name,"陕西省")== 0){
					
						Creat("Sanxi_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						mul = Kruskal();
						printf("Minimum capital investment : %d\n",mul);
						if (mul <= Finance){
						
							printf("Capital surplus\n");	//资金盈余
						}else{
							printf("Capital loss\n");	//资金亏损
						}
				}else if (strcmp(name,"广东省")== 0){
					
						Creat("Guangdong_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						mul = Kruskal();
						printf("Minimum capital investment : %d\n",mul);
						if (mul <= Finance){
						
							printf("Capital surplus\n");	//资金盈余
						}else{
							printf("Capital loss\n");	//资金亏损
						}
				}else if (strcmp(name,"福建省")== 0){
					
						Creat("Fujian_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						mul = Kruskal();
						printf("Minimum capital investment : %d\n",mul);
						if (mul <= Finance){
						
							printf("Capital surplus\n");	//资金盈余
						}else{
							printf("Capital loss\n");	//资金亏损
						}
				}else if (strcmp(name,"四川省")== 0){
					
						Creat("Sichuan_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						mul = Kruskal();
						printf("Minimum capital investment : %d\n",mul);
						if (mul <= Finance){
						
							printf("Capital surplus\n");	//资金盈余
						}else{
							printf("Capital loss\n");	//资金亏损
						}

				}
				
				printf("*************\n");break;
		
		case 4:	printf("Input The name of the Province:\n");
				gets(name);
				
				if (strcmp(name,"山西省")== 0){
						Creat("Shanxi_Province.txt");
						printf("This are Two City:\n");
						min = Find();
						printf("The minmun of the power :\n");
						printf("%d\n",min);
						
				}else if (strcmp(name,"陕西省")== 0){
						Creat("Sanxi_Province.txt");
						printf("This are Two City:\n");
						min = Find();
						printf("The minmun of the power :\n");
						printf("%d\n",min);
						
				}else if (strcmp(name,"广东省")== 0){
						Creat("Guangdong_Province.txt");
						printf("This are Two City:\n");
						min = Find();
						printf("The minmun of the power :\n");
						printf("%d\n",min);
						
				}else if (strcmp(name,"福建省")== 0){
						Creat("Fujian_Province.txt");
						printf("This are Two City:\n");
						min = Find();
						printf("The minmun of the power :\n");
						printf("%d\n",min);
						
				}else if (strcmp(name,"四川省")== 0){
						Creat("sichuan_Province.txt");
						printf("This are Two City:\n");
						min = Find();
						printf("The minmun of the power :\n");
						printf("%d\n",min);
						
				}
			printf("*************\n");break;
		case 5:printf("Input The name of the Province:\n");
				gets(name);
				
				if (strcmp(name,"山西省")== 0){
						Creat("Shanxi_Province.txt");
						printf("City rules matrix is :\n");
						print();
				}else if (strcmp(name,"陕西省")== 0){
					
						Creat("Sanxi_Province.txt");
						printf("City rules matrix is :\n");
						print();
				}else if (strcmp(name,"广东省")== 0){
					
						Creat("Guangdong_Province.txt");
						printf("City rules matrix is :\n");
						print();
				}else if (strcmp(name,"福建省")== 0){
					
						Creat("Fujian_Province.txt");
						printf("City rules matrix is :\n");
						print();
				}else if (strcmp(name,"四川省")== 0){
					
						Creat("Sichuan_Province.txt");
						printf("City rules matrix is :\n");
						print();
				}
			printf("*************\n");break;
		case 6:exit(-1);
			printf("Welcome to ues!!Thank you!\n");
			printf("*************\n");break;
	}

	
	}
	return 0 ;
}