import java.util.*;

public class SJF
{
	public static void main(String[] args)
	{
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the number of processes: ");
		int n= sc.nextInt();
		int pid[]= new int[n];
		int at[]= new int[n];
		int bt[]= new int[n];
		int ct[]= new int[n];
		int ta[]= new int[n];
		int wt[]= new int[n];
		int f[]= new int[n];
		
		int st=0, tot=0;
		float avgwt=0, avgta=0;

		for(int i=0; i<n; i++)
		{
			System.out.println("Enter arrival time of process " + (i+1) + ": ");
			at[i]= sc.nextInt();
			System.out.println("Enter burst time of process " + (i+1) + ": ");
			bt[i]= sc.nextInt();
			pid[i]= i+1;
			f[i]=0;
		}

		while(true)
		{
			int c=n, min=999;
			if(tot==n)
				break;
			
			for(int i=0; i<n; i++)
			{
				if((at[i]<=st) && (f[i]==0) && (bt[i]<min))
				{
 					min= bt[i];
					c=i;
				}
			}
			if(c==n)
				st++;

			else
			{
				ct[c]= st+bt[c];
				st+=bt[c];
				ta[c]=ct[c]-at[c];
				wt[c]=ta[c]-bt[c];
				f[c]= 1;
				pid[tot]= c+1;
				tot++;
			}
		}

		System.out.println("\nPID\t\tArrival\t\tBurst\t\tComplete\t\tTurn\t\tWaiting");
		for(int i=0; i<n; i++)
		{
			avgwt+= wt[i];
			avgta+= ta[i];
			System.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+ct[i]+"\t\t"+ta[i]+"\t\t"+wt[i]);
		}
		System.out.println("\nAverage turnaround time is: " +(float)(avgta/n));
		System.out.println("\nAverage wait time is: " + (float)(avgwt/n));
 	}
}