import java.util.*;
class roundrobin { 
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
  
    
    public static void main(String args[]) 
    { 
	Scanner sc= new Scanner(System.in);
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
    } 
} 