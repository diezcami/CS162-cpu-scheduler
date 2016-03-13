import java.util.PriorityQueue;
import java.util.Scanner;

public class Runner {
    public static void main (String args[]) {
        //ProcessScheduler ps = new ProcessScheduler();
    	ProcessScheduler ps;
    	PriorityQueue pq;
        Scanner sc = new Scanner(System.in);

        int numCases = Integer.parseInt(sc.nextLine());

        for ( int j = 1; j <= numCases; j++ ) {
            String[] caseData = sc.nextLine().split("\\s+");
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
                int quantum = schedulingAlgorithm.equals("RR") ? Integer.parseInt(processData[3]) : 0;// processData[3]: quantum (only if RR)
                Process temp = new Process (i + 1, arrival, burst , priority, quantum, schedulingAlgorithm);
                processes[i] = temp;
            }
            ps = new ProcessScheduler(schedulingAlgorithm, processes);
            pq = new PriorityQueue<Process>(ps);
            System.out.println(j);
            ps.scheduleProcesses();
            //ps.printGantt(pq);
            //ps.setAlgorithm (caseData[1]);
        }
    }
}