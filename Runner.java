import java.util.Scanner;

public class Runner {
    public static void main (String args[]) {
        ProcessScheduler ps = new ProcessScheduler ();
        Scanner sc = new Scanner(System.in);

        int numCases = (int)sc.nextLine();

        while (numCases > 0) {
            String[] caseData = sc.nextLine().split("\\s+");
            // caseData[0]: number of processes
            // caseData[1]: FCFS, SJF, SRTF, P, RR
            
            // Parse processes into an array
            Process[] processes = new Process[(int)caseData[0]];
            for (int i = 0; i < (int)caseData[0]; i++) {
                String processData = sc.nextLine.split("\\s+");
                // processData[0]: arrival
                // processData[1]: burst
                // processData[2]: priority
                // processData[3]: quantum (only if RR)
                Process temp = new Process ((int)processData[0], (int)processData[1], (int)processData[2]);
                temp.quantum = caseData[1].equals("RR") ? processData[3] : 0;
                processes[i] = temp;
            }
            ps.setProcesses (processes);
            ps.setAlgorithm (caseData[1]);
        }
    }
}