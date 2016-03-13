import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Runner {
    public static void main (String args[]) {
        //ProcessScheduler ps = new ProcessScheduler();
    	ProcessScheduler ps;
    	//PriorityQueue pq;
        try {
            Scanner sc = new Scanner(new FileReader("cases.txt"));

            int numCases = Integer.parseInt(sc.nextLine());

            // Solve for results
            for ( int j = 1; j <= numCases; j++ ) {
            	String temsp = sc.nextLine();
                String[] caseData = temsp.split("\\s+");
                //System.out.println(temsp+"FUCK");
                // caseData[0]: number of processes
                // caseData[1]: FCFS, SJF, SRTF, P, RR
                int numProcesses = Integer.parseInt(caseData[0]);
                String schedulingAlgorithm = caseData[1];
                // Parse processes into an array
                Process[] processes = new Process[numProcesses];
                for (int i = 0; i < numProcesses; i++) {
                    String[] processData = sc.nextLine().split("\\s+");
                    int arrival = Integer.parseInt(processData[0]);// processData[0]: arrival
                    int burst = Integer.parseInt(processData[1]);// processData[1]: burst
                    int priority = Integer.parseInt(processData[2]);// processData[2]: priority
                    int quantum = schedulingAlgorithm.equals("RR") ? Integer.parseInt(caseData[2]) : 0;// processData[3]: quantum (only if RR)
                    Process temp = new Process (i + 1, arrival, burst , priority, quantum, schedulingAlgorithm);
                    processes[i] = temp;
                }
                System.out.println(j);
                ps = new ProcessScheduler(schedulingAlgorithm, processes);
                ps.scheduleProcesses();
                
            }
            sc.close();
        } 
        catch (IOException e) {
            System.out.println ("File not found!");
        }
    }
}