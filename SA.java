import java.util.*;
class Main
{
    String name;
    int at;
    int bt;
    int wt;
    int ta;
    int ft;

}
class Main2
{
    String name;
    int at;
    int bt;
    int wt;
    int ta;
    int ft;
    int p;


}

public class SA
{
    public static void main(String[] args)
    {
        int ch;
        Scanner sc = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("Scheduling Algorithms");

        do
        {
            System.out.println("***************************************\n");
            System.out.println("1.FIFO\t2.SJF\t3.Round Robin\t4.Priority\t5.Exit");
            ch=sc.nextInt();
            switch(ch)
            {
                case 1:fc();
                    break;
                case 2: sjf();
                    break;
                case 3:
                    int n;
                    System.out.println("Enter the number of processes: ");
                    n= sc.nextInt();

                    String name[] = new String[n];


                    int arrivaltime[] = new int[n];


                    int bursttime[] = new int[n];


                    int q;
                    System.out.println("Enter quantum time: ");
                    q= sc.nextInt();
                    for(int i=0; i<n; i++)
                    {
                        System.out.println("Enter process name: ");
                        name[i]= sc.next();
                        System.out.println("Enter the burst time of process " + (name[i]) + ": ");
                        bursttime[i]= sc.nextInt();
                        System.out.println("Enter the arrival time of process " + (name[i]) + ": ");
                        arrivaltime[i]= sc.nextInt();
                    }


                    roundRobin(name, arrivaltime, bursttime, q);

            break;
                case 4:pr();
                    break;
                case 5: System.out.println("Thank You...");
                    break;
                default: System.out.println("Invalid...");
            }
        }while(ch!=5);
    }

    static void fc()
    {   Scanner sc = new Scanner(System.in);
        System.out.println("Enter no.of process:");
        int i = sc.nextInt();
        Main obj[]=new Main[i];
        for(int j=0;j<i;j++)
        {
            obj[j]= new Main();
            System.out.println("Enter for process_"+(j+1)+":");
            System.out.println("Enter name of process:");
            obj[j].name=sc.next();
            System.out.println("Enter arrival time:");
            obj[j].at=sc.nextInt();
            System.out.println("Enter burst time:");
            obj[j].bt=sc.nextInt();
        }
        Main temp= new Main();
        for(int n=0;n<i;n++)
        {
            for(int j=0;j<i-1;j++)
            {
                if(obj[j+1].at<obj[j].at)
                {
                    temp=obj[j+1];
                    obj[j+1]=obj[j];
                    obj[j]=temp;
                }
            }
        }
        int w=obj[0].at,s=0;
        for(int j=0;j<i;j++)
        {
            obj[j].wt=w-obj[j].at;
            w=w+obj[j].bt;
            obj[j].ft=obj[j].bt+s;
            s=obj[j].ft;
            obj[j].ta=obj[j].wt+obj[j].bt;
        }
        System.out.println("************************************************");
        System.out.println("Process\t\tArr Time\t\tBurst Time\t\tFin Time\tWait Time\t\tTurnA");
        System.out.println("************************************************");
        float avwt=0,avta=0;
        for(int j=0;j<i;j++)
        {
            System.out.println(obj[j].name+"\t\t\t"+obj[j].at+"\t\t\t"+obj[j].bt+"\t\t\t"+obj[j].ft+"\t\t\t"+obj[j].wt+"\t\t\t"+obj[j].ta);
            avwt=avwt+obj[j].wt;avta=avta+obj[j].ta;
        }
        avwt=avwt/i;
        avta=avta/i;
        System.out.println("Average Waiting Time: "+avwt);
        System.out.println("Average TurnAround Time: "+avta);


    }
    static void sjf()
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
            System.out.println(pid[i]+"\t\t"+at[pid[i]-1]+"\t\t"+bt[pid[i]-1]+"\t\t"+ct[pid[i]-1]+"\t\t\t"+ta[pid[i]-1]+"\t\t\t"+wt[pid[i]-1]);
        }
        System.out.println("\nAverage turnaround time is: " +(float)(avgta/n));
        System.out.println("\nAverage wait time is: " + (float)(avgwt/n));
    }

    public static void roundRobin(String p[], int a[],
                                  int b[], int n)
    {

        int res = 0;
        int resc = 0;


        String seq = new String();


        int res_b[] = new int[b.length];
        int res_a[] = new int[a.length];

        for (int i = 0; i < res_b.length; i++) {
            res_b[i] = b[i];
            res_a[i] = a[i];
        }


        int t = 0;


        int w[] = new int[p.length];


        int comp[] = new int[p.length];

        while (true) {
            boolean flag = true;
            for (int i = 0; i < p.length; i++) {


                if (res_a[i] <= t) {
                    if (res_a[i] <= n) {
                        if (res_b[i] > 0) {
                            flag = false;
                            if (res_b[i] > n) {


                                t = t + n;
                                res_b[i] = res_b[i] - n;
                                res_a[i] = res_a[i] + n;
                                seq += "->" + p[i];
                            }
                            else {


                                t = t + res_b[i];


                                comp[i] = t - a[i];


                                w[i] = t - b[i] - a[i];
                                res_b[i] = 0;


                                seq += "->" + p[i];
                            }
                        }
                    }
                    else if (res_a[i] > n) {

                        for (int j = 0; j < p.length; j++) {


                            if (res_a[j] < res_a[i]) {
                                if (res_b[j] > 0) {
                                    flag = false;
                                    if (res_b[j] > n) {
                                        t = t + n;
                                        res_b[j] = res_b[j] - n;
                                        res_a[j] = res_a[j] + n;
                                        seq += "->" + p[j];
                                    }
                                    else {
                                        t = t + res_b[j];
                                        comp[j] = t - a[j];
                                        w[j] = t - b[j] - a[j];
                                        res_b[j] = 0;
                                        seq += "->" + p[j];
                                    }
                                }
                            }
                        }


                        if (res_b[i] > 0) {
                            flag = false;


                            if (res_b[i] > n) {
                                t = t + n;
                                res_b[i] = res_b[i] - n;
                                res_a[i] = res_a[i] + n;
                                seq += "->" + p[i];
                            }
                            else {
                                t = t + res_b[i];
                                comp[i] = t - a[i];
                                w[i] = t - b[i] - a[i];
                                res_b[i] = 0;
                                seq += "->" + p[i];
                            }
                        }
                    }
                }


                else if (res_a[i] > t) {
                    t++;
                    i--;
                }
            }

            if (flag) {
                break;
            }
        }

        System.out.println("name  ctime  wtime");
        for (int i = 0; i < p.length; i++) {
            System.out.println(" " + p[i] + "    " + comp[i]
                    + "    " + w[i]);

            res = res + w[i];
            resc = resc + comp[i];
        }

        System.out.println("Average waiting time is "
                + (float)res / p.length);
        System.out.println("Average compilation  time is "
                + (float)resc / p.length);
        System.out.println("Sequence is like that " + seq);
    }
    static void pr()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no.of process:");
        int i = sc.nextInt();
        Main2 obj[]=new Main2[i];
        for(int j=0;j<i;j++)
        {
            obj[j]= new Main2();
            System.out.println("Enter for process_"+(j+1)+":");
            System.out.println("Enter name of process:");
            obj[j].name=sc.next();
            System.out.println("Enter arrival time:");
            obj[j].at=sc.nextInt();
            System.out.println("Enter burst time:");
            obj[j].bt=sc.nextInt();
            System.out.println("Enter priorities:");
            obj[j].p=sc.nextInt();
        }
        Main2 temp= new Main2();
        for(int n=0;n<i;n++)
        {
            for(int j=0;j<i-1;j++)
            {
                if(obj[j+1].at<obj[j].at)
                {
                    temp=obj[j+1];
                    obj[j+1]=obj[j];
                    obj[j]=temp;
                }
                else if(obj[j+1].at==obj[j].at)
                {
                    if(obj[j+1].p<obj[j].p)
                    {
                        temp=obj[j+1];
                        obj[j+1]=obj[j];
                        obj[j]=temp;
                    }

                }
            }
        }
        int b=0; Main2 min= new Main2();

        for(int j=0;j<i;j++)
        {

            for(int k=j+1;k<i;k++)
            {
                if(obj[k].p<obj[j].p && obj[k].at<b )
                {
                    min=obj[j];
                    obj[j]=obj[k];
                    obj[k]=min;
                }

            }
            b=b+obj[j].bt;
        }
        int w=0,s=0;
        for(int j=0;j<i;j++)
        {
            obj[j].wt=w-obj[j].at;
            w=w+obj[j].bt;
            obj[j].ft=obj[j].bt+s;
            s=obj[j].ft;
            obj[j].ta=obj[j].wt+obj[j].bt;
        }
        System.out.println("************************************************");
        System.out.println("Process\t\tArr Time\t\tPrior\tBurst Time\tFin Time\tWait Time\t\tTurnA");
        System.out.println("************************************************");
        float avwt=0,avta=0;
        for(int j=0;j<i;j++)
        {
            System.out.println(obj[j].name+"\t\t\t"+obj[j].at+"\t\t\t\t"+obj[j].p+"\t\t\t\t"+obj[j].bt+"\t\t\t"+obj[j].ft+"\t\t\t"+obj[j].wt+"\t\t\t"+obj[j].ta);
            avwt=avwt+obj[j].wt;avta=avta+obj[j].ta;
        }
        avwt=avwt/i;
        avta=avta/i;
        System.out.println("Average Waiting Time: "+avwt);
        System.out.println("Average TurnAround Time: "+avta);


    }
}
