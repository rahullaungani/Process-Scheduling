import java.util.*;

class RR
{
public static void main(String[] args)
{
     Scanner sc= new Scanner(System.in);
     int k,j,q,i,n,ts,temp;
     float aw;                      float awt;
     int bt[]= new int[10];
     int wt[]= new int[10];
     int te[]= new int[10];
     int rt[]= new int[10];
     int at[]= new int[10];
     j=0;
     System.out.println("Enter the number of processes: ");
     n= sc.nextInt();
    for(i=0;i<n;i++)
      {
          System.out.println("Enter the burst time of process " + (i+1) +": ");
          bt[i]= sc.nextInt();
          System.out.println("Enter the arrival time of process " + (i+1) +": ");
          at[i]= sc.nextInt();
          te[i]=0;     wt[i]=0;
      }
    for(i=0;i<n;i++)
      {
        for(j=i+1;j<n;j++)
         {
             if(at[i]>at[j])
               {
                    temp=at[i];                                       
                    at[i]=at[j];
                    at[j]=temp;
                    temp=bt[i];
                    bt[i]=bt[j];
                    bt[j]=temp;
              }
         }
     }
    System.out.println("Enter quantum time: ");
    ts= sc.nextInt(); q=0;  
    /*
    printf("\nprocess      :")  ;
    for(i=0;i<n;i++)
     {
       printf("  %d",i+1);
      }
    printf("\nBrust time   :");
    for(i=0;i<n;i++)
       {
         printf("  %d",bt[i]); rt[i]=bt[i];
        }
    printf("\nArrival time :");
    for(i=0;i<n;i++)
      {
          printf("  %d",at[i]);
      }
    */
    System.out.println("Gantt chart");
    j=0;

    while(j<=n)
       {
          j++;
          for(i=0;i<n;i++)
            {
              if(rt[i]==0)  continue;
                   if(rt[i]>ts)
                     {
			System.out.println(q + "\t" + "P" + (i+1));
                        q=q+ts;
                        rt[i]=rt[i]-ts;
                        te[i]=te[i]+1;
                     }
                  else
                    {
			System.out.println(q + "\t" + "P" + (i+1));
                       wt[i]=q-te[i]*ts;
                       q=q+rt[i]; 
                       rt[i]=rt[i]-rt[i];
                    }
            }
       }                                   
    awt=0;
    System.out.println(" Process   Waiting time");
    for(i=0;i<n;i++)
       {
                       wt[i]=wt[i]-at[i];
                       System.out.println("P" +(i+1)   +  ":   "+wt[i]); awt=awt+wt[i];
        }
    aw=awt;
    System.out.println("Total waiting time: "+aw);
    System.out.println("Average wainting time "+awt/n);
 }
}