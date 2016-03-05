import java.util.Scanner;

public class Runner {
    public static void main (String args[]) {
        //ProcessScheduler ps = new ProcessScheduler();
    	ProcessScheduler ps;
        Scanner sc = new Scanner(System.in);

        int numCases = Integer.parseInt(sc.nextLine());

        while (numCases > 0) {
            String[] caseData = sc.nextLine().split("\\s+");
            // caseData[0]: number of processes
            // caseData[1]: FCFS, SJF, SRTF, P, RR
            int numProcesses = Integer.parseInt(caseData[0]);
            String algorithm = caseData[1];
            // Parse processes into an array
            Process[] processes = new Process[numProcesses];
            for (int i = 0; i < numProcesses; i++) {
                String[] processData = sc.nextLine().split("\\s+");
                int arrival = Integer.parseInt(processData[0]);// processData[0]: arrival
                int burst = Integer.parseInt(processData[1]);// processData[1]: burst
                int priority = Integer.parseInt(processData[2]);// processData[2]: priority
                int quantum = caseData[1].equals("RR") ? Integer.parseInt(processData[3]) : 0;// processData[3]: quantum (only if RR)
                Process temp = new Process (arrival, burst , priority, quantum );
                //temp.quantum = caseData[1].equals("RR") ? processData[3] : 0;
                processes[i] = temp;
            }
            ps = new ProcessScheduler(algorithm, processes);
            //ps.setAlgorithm (caseData[1]);
        }
    }
}