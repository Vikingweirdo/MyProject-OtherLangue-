#include<graphics.h>
#include<conio.h>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#define M 20
#define F 5000
typedef struct Graph{			//����ͼ�Ľṹ��
	char b[M][M];
	int n ,  e; 
	int edges[M][M];
}Graph;

typedef struct EdgePrim{		//prim�㷨�ṹ��
  int beg,en;					//������С����������ʼ����ֹ�ڵ�
  int length;					//��С�������ĳ���
}edge;
typedef struct EdgeKruskal{		//kruskal�㷨�ṹ��
  int u,v;						//��¼�бߵ���ʼ�ڵ����ֹ�ڵ�
  int power;					//�ߵ�Ȩֵ
}Edge;
	
edge tree[M];					//������С����������

Graph G;						//����ȫ��ͼ����

void Creat(char *name){
	FILE *fp;					//ָ���ļ���ָ��
	int m = 0;
	char a[10];					//����ص�����
	fp = fopen(name,"r");		//������Ϊname���ļ�		
					
	int i,  begin , end , pow  , count=0;

	printf("Input the information of the City\n");

	fscanf(fp,"%s",a);			//������е�����

	while (strcmp(a,"end")!= 0){		//����end��ֹͣ�������������Ϣ
	

		strcpy(G.b[count],a);		//ʹ���ַ������ư�a����ĳ������ָ��Ƶ�b������
				
		printf("Input the information of the City\n");

		fscanf(fp,"%s",a);			//�ٴ������������

		count++;					//ͳ�Ƴ�������ĸ���
	}

	G.n = count;					//ͳ�Ƴ��еĸ���
	
	fflush(stdin);					//���������
	
	for (int k = 0;k<G.n ;k++ ){	//ͨ��forѭ����ʼ���ڽӾ���
	
		for (int l = 0;l < G.n ;l++ ){
		
			if (k == l){			//ʹ�Խ��ߵ�ֵΪ0
			
				G.edges[k][l] = 0;
			}
			else{					//����ĵط�Ϊ�����
				G.edges[k][l] = F;
			}
		}
	}
	
	printf("The number of the City is %d:\n",G.n);
	
	printf("Input the number of the road:\n");
	
	fscanf(fp,"%d",&G.e);			//�ļ������
	
	printf("%d\n",G.e);
	
	if (G.e == 0){					//�������Ϊ0����ô����
		
		printf("Error!!\n");
		
		return ;
	}
	
	fflush(stdin);
	
	for(i = 0 ; i < G.e ; i++){		//ͼ�ı�ѭ������ڽӾ��������
		
		printf("Input the information of the road ; begining and ending :\n");	//����������������·
		
		fscanf(fp,"%d",&begin);
		
		fscanf(fp,"%d",&end);
		
		printf("Input the pow of the road:\n");		//����·�Ĵ��ۣ�Ȩֵ��
		
		fscanf(fp,"%d",&pow);
		
		G.edges[begin][end] = pow;					//����ͼ�ڽӾ�������
		
		G.edges[end][begin] = pow;
	}

	fclose(fp);
}
void print(){					//�����ڽӾ���ͼ
	
	int i , j , k , m = 0 ;
	
	while(m<G.n){				//������е�����
		
		printf("%s\n",G.b[m]);
		
		m++;
	}
	for (i = 0 ; i < G.n ; i++){		//����ڽӾ���
		
		k = 1 ; 
		
		for (j = 0 ; j < G.n ; j++){
			
			printf("%5d",G.edges[i][j]);	//%5d�ǿ�������������֮���5���ո�
			
			if (k == G.n)printf("\n");		//����ÿ�������������ʱ����
			
			k++;
		}
	
	}


}
int Prim(){
	
	edge temp;				//��������tree[]����
	
	int i , j ,d , min , k ,s , v;
	
	int built;				//������д�����С������
	
	printf("Input the number you want to bulit:\n");
	
	scanf("%d",&built);
	
	built--;				//��Ϊ�����0�����б��ǵ�һ������
	
	fflush(stdin);
	
	for (i = 0; i<G.n ; i++){	//��ʼ��tree����
	
		tree[i].beg = built;	
		
		tree[i].en = i;
		
		tree[i].length=G.edges[built][i];
	}
	
	for (k = 0 ;k<G.n ;k++ ){		
		
		min = tree[k].length;
		
		s = k;
		
		for (j = k+1;j< G.n;j++){		//�ҵ�ÿ��ѭ����С�ı�
		
			if (min > tree[j].length){
					
					min = tree[j].length;
					s = j;			
				}
		}

		v  = tree[s].en;				//��¼����С�ߵ���ֹ�ڵ�
		
		temp = tree[s];					//ͨ������ʹ��С�߿�ǰ��ͬʱ��ֹ����v0---v0���������
		
		tree[s] = tree[k];
		
		tree[k] = temp;					
		
		for (j = k+1;j<G.n ;j++){		//ͨ��ѭ��������tree�����Ȩֵ
		
			d = G.edges[v][tree[j].en];	
			
			if (d<tree[j].length){		//��forѭ�������Ƚϴ˽ڵ��ڽӾ����еľ������һ���ڵ��ڽӾ���ľ���治�������
			
				tree[j].length = d;		//��������µ�tree�����Ȩֵ�ͱ߳�ʼ�ڵ�
				tree[j].beg = v;
			}
		}
	}
	
	printf("********\n");
	
	for (j = 1;j< G.n ;j++ ){		//j = 0�洢��ʼ�ڵ㵽��ʼ�ڵ�ľ��룬���Դ�j = 1��ʼ
	
		printf("%s---%s %d\n",G.b[tree[j].beg],G.b[tree[j].en],tree[j].length);
	}
	
	int sum = 0;

	for (j = 0;j< G.n ; j++){		//�������������д��ۺ�
	
		sum+=tree[j].length;
	}
	
	printf("The Cicy of start is: %s\n",G.b[built]);

	return sum;

}
int Kruskal(){
	
	Edge E[M*M];				//��������ͼ�����бߣ���M���ڵ������M*M����
	
	int i , j , k ,s1 , s2 , mul = 0;
	
	int vset[M];				//����ÿ���ڵ����ͨ����
	
	k = 0 ;
	
	for (i = 0;i<G.n ;i++ ){	//forѭ����Ĭ��ʹÿ���ڵ����ͨ�����������Ķ��������v0 = 0��
	
		vset[i] = i;			
	}
	for (i = 0;i<G.n ; i++){	//forѭ����¼�����б߲�����E������
	
		for (j = 0 ;j < G.n ;j++ ){
		
			if (G.edges[i][j] != 0 && G.edges[i][j]< F){
			
				E[k].u = i;
				E[k].v = j;
				E[k].power = G.edges[i][j];
				k++;
			}

		}
	}
	
	Edge temp;				//ð�����򷨵Ľ�������
	
	for (i = 0;i<k ; i++){	//ð������ѱ�Ȩֵ������������
	
		for (j = 0 ;j < k ;j++ ){
		
			if (E[i].power<E[j].power){
			
				temp = E[i];
				E[i] = E[j];
				E[j] = temp;
			}
		}
	}

	k = 1;			//����ɨ��Ķ�������Ϊ0----0û�бߣ����Դӵ�һ�����㿪ʼ
	
	j = 0;			//����ɨ��E����
	
	while (k<G.n){	//ɨ��ȫ������
	
		s1 = vset[E[j].u];		//��¼�˳�ʼ�������ͨ����
		
		s2 = vset[E[j].v];		//��¼����ֹ�������ͨ����
		
		if (s1 != s2){			//�����ͨ�����������Ϊ��������һ����
		
			printf("%s---->%s %d\n",G.b[E[j].u],G.b[E[j].v],E[j].power);//�����ʱ�Ķ������С��
			
			k++;				
			mul+=E[j].power;	//��Ȩֵ��
			for (i = 0;i<G.n ;i++ )	{	//�Ѹ����ɵı�����Ȩֵˢ��һ��
			
				if (vset[i] == s2)
				{
					vset[i] = s1;
				}
			}
		}
		j++;
	}
	printf("The Cicy of start is:%s\n",G.b[0]);		//�����ʼ����

	return mul;
}

int Find(){				//������С���۵���������
	
	int i , j , k , min ;
	
	k = 0;
	
	Edge E[M*M];		//��������ߵ���Ϣ
	
	Edge temp;			//������м����
	
	for (i = 0;i<G.n ; i++)	//��ʼ��E����
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

	for (i = 0;i<k ; i++)//ð������
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
	
	min = E[0].power;		//�������E[0]������̵ı�
	printf("%s------%s \n",G.b[E[0].u],G.b[E[0].v]);
	return min;

}

void Menu(){
	printf("*********************************************************\n");
	printf("*~~~~~~~Welcome to city planning system~~~~~~~~~~~~~~~~~*\n");
	printf("* 0��Input the picture                                  *\n");
	printf("* 1��List the plans of the provinces                    *\n");//�����嵥
	printf("* 2��Capital query                                      *\n");//Ͷ���ʽ��ѯ
	printf("* 3��Financial Review                                   *\n");//�ʽ𸴲�
	printf("* 4��Two city minimum funding query                     *\n");//���ٳ���Ͷ��
	printf("* 5��City rules matrix					*\n");				  //���й滮����ͼ
	printf("* 6��Exit                                               *\n");
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
				
				if (strcmp(name,"ɽ��ʡ")== 0){
						initgraph(680,680);
						char s1[]="̫ԭ";
						char s2[]="�Ž�";
						char s3[]="��ƽ";
						char s4[]="����";
						char s5[]="����";
						char s6[]="ԭƽ"; 
						char s7[]="�ٷ�";
  
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
				else if (strcmp(name,"����ʡ")== 0){
					
					initgraph(680,750);
					char s1[]="����";
					char s2[]="����";
					char s3[]="�Ӱ�";
					char s4[]="μ��";
					char s5[]="����";
					 char s6[]="����"; 
					char s7[]="����";
  
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
					
				}else if (strcmp(name,"�㶫ʡ")== 0){
					initgraph(680,680);
					char s1[]="����";
					char s2[]="��Զ";
					char s3[]="��ɽ";
					char s4[]="���";
				    char s5[]="��Դ";
				    char s6[]="�ع�"; 
					char s7[]="����";
  
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
						
				}else if (strcmp(name,"����ʡ")== 0){
					initgraph(680,680);
					char s1[]="����";
					char s2[]="��ƽ";
					char s3[]="����";
					char s4[]="����";
				    char s5[]="����";
				    char s6[]="����"; 
					char s7[]="����";
  
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
					
				}else if (strcmp(name,"�Ĵ�ʡ")== 0){
					initgraph(680,680);

					char s1[]="�ɶ�";
					char s2[]="�Ű�";
					char s3[]="��ɽ";
					char s4[]="�ڽ�";
				    char s5[]="�ϳ�";
				    char s6[]="����"; 
					char s7[]="�봨";
  
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
				
				if (strcmp(name,"ɽ��ʡ")== 0){
						Creat("Shanxi_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						sum = Prim();
						printf("Minimum capital investment is : %d\n",sum);
						if (sum <= Finance){
						
							printf("Capital surplus\n");	//�ʽ�ӯ��
						}else{
							printf("Capital loss\n");	//�ʽ����
						}
				}else if (strcmp(name,"����ʡ")== 0){
					
						Creat("Sanxi_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						sum = Prim();
						printf("Minimum capital investment is : %d\n",sum);
						if (sum <= Finance){
						
							printf("Capital surplus\n");	//�ʽ�ӯ��
						}else{
							printf("Capital loss\n");	//�ʽ����
						}
				}else if (strcmp(name,"�㶫ʡ")== 0){
					
						Creat("Guangdong_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						sum = Prim();
						printf("Minimum capital investment is : %d\n",sum);
						if (sum <= Finance){
						
							printf("Capital surplus\n");	//�ʽ�ӯ��
						}else{
							printf("Capital loss\n");	//�ʽ����
						}
				}else if (strcmp(name,"����ʡ")== 0){
					
						Creat("Fujian_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						sum = Prim();
						printf("Minimum capital investment is : %d\n",sum);
						if (sum <= Finance){
						
							printf("Capital surplus\n");	//�ʽ�ӯ��
						}else{
							printf("Capital loss\n");	//�ʽ����
						}
				}else if (strcmp(name,"�Ĵ�ʡ")== 0){
					
						Creat("Sichuan_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						sum = Prim();
						printf("Minimum capital investment is : %d\n",sum);
						if (sum <= Finance){
						
							printf("Capital surplus\n");	//�ʽ�ӯ��
						}else{
							printf("Capital loss\n");	//�ʽ����
						}
				}
			
				printf("*************\n");break;
		
		case 3:printf("Input The name of the Province:\n");
				gets(name);
				if (strcmp(name,"ɽ��ʡ")== 0){
						Creat("Shanxi_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						mul = Kruskal();
						printf("Minimum capital investment : %d\n",mul);
						if (mul <= Finance){
						
							printf("Capital surplus\n");	//�ʽ�ӯ��
						}else{
							printf("Capital loss\n");	//�ʽ����
						}
				}else if (strcmp(name,"����ʡ")== 0){
					
						Creat("Sanxi_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						mul = Kruskal();
						printf("Minimum capital investment : %d\n",mul);
						if (mul <= Finance){
						
							printf("Capital surplus\n");	//�ʽ�ӯ��
						}else{
							printf("Capital loss\n");	//�ʽ����
						}
				}else if (strcmp(name,"�㶫ʡ")== 0){
					
						Creat("Guangdong_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						mul = Kruskal();
						printf("Minimum capital investment : %d\n",mul);
						if (mul <= Finance){
						
							printf("Capital surplus\n");	//�ʽ�ӯ��
						}else{
							printf("Capital loss\n");	//�ʽ����
						}
				}else if (strcmp(name,"����ʡ")== 0){
					
						Creat("Fujian_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						mul = Kruskal();
						printf("Minimum capital investment : %d\n",mul);
						if (mul <= Finance){
						
							printf("Capital surplus\n");	//�ʽ�ӯ��
						}else{
							printf("Capital loss\n");	//�ʽ����
						}
				}else if (strcmp(name,"�Ĵ�ʡ")== 0){
					
						Creat("Sichuan_Province.txt");
						printf("Input your Finance:\n");
						scanf("%d",&Finance);
						mul = Kruskal();
						printf("Minimum capital investment : %d\n",mul);
						if (mul <= Finance){
						
							printf("Capital surplus\n");	//�ʽ�ӯ��
						}else{
							printf("Capital loss\n");	//�ʽ����
						}

				}
				
				printf("*************\n");break;
		
		case 4:	printf("Input The name of the Province:\n");
				gets(name);
				
				if (strcmp(name,"ɽ��ʡ")== 0){
						Creat("Shanxi_Province.txt");
						printf("This are Two City:\n");
						min = Find();
						printf("The minmun of the power :\n");
						printf("%d\n",min);
						
				}else if (strcmp(name,"����ʡ")== 0){
						Creat("Sanxi_Province.txt");
						printf("This are Two City:\n");
						min = Find();
						printf("The minmun of the power :\n");
						printf("%d\n",min);
						
				}else if (strcmp(name,"�㶫ʡ")== 0){
						Creat("Guangdong_Province.txt");
						printf("This are Two City:\n");
						min = Find();
						printf("The minmun of the power :\n");
						printf("%d\n",min);
						
				}else if (strcmp(name,"����ʡ")== 0){
						Creat("Fujian_Province.txt");
						printf("This are Two City:\n");
						min = Find();
						printf("The minmun of the power :\n");
						printf("%d\n",min);
						
				}else if (strcmp(name,"�Ĵ�ʡ")== 0){
						Creat("sichuan_Province.txt");
						printf("This are Two City:\n");
						min = Find();
						printf("The minmun of the power :\n");
						printf("%d\n",min);
						
				}
			printf("*************\n");break;
		case 5:printf("Input The name of the Province:\n");
				gets(name);
				
				if (strcmp(name,"ɽ��ʡ")== 0){
						Creat("Shanxi_Province.txt");
						printf("City rules matrix is :\n");
						print();
				}else if (strcmp(name,"����ʡ")== 0){
					
						Creat("Sanxi_Province.txt");
						printf("City rules matrix is :\n");
						print();
				}else if (strcmp(name,"�㶫ʡ")== 0){
					
						Creat("Guangdong_Province.txt");
						printf("City rules matrix is :\n");
						print();
				}else if (strcmp(name,"����ʡ")== 0){
					
						Creat("Fujian_Province.txt");
						printf("City rules matrix is :\n");
						print();
				}else if (strcmp(name,"�Ĵ�ʡ")== 0){
					
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