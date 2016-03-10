import java.util.Comparator;
import java.util.PriorityQueue;

public class ProcessScheduler implements Comparator<Process>{
    private Process[] processes;
    private String schedulingAlgorithm;
    private PriorityQueue<Process> pq;
    private boolean preemptive;
    
    public ProcessScheduler (String schedulingAlorithm, Process[] processes) {
        this.schedulingAlgorithm = schedulingAlgorithm;
        this.processes = processes;
        if (schedulingAlgorithm.equals ("FCFS") || schedulingAlgorithm.equals ("SJF"))
            preemptive = false;
        else preemptive =  true;
    }

    // Input: Processes
    // Updates processes stored in the ProcessScheduler
    public void setProcesses (Process[] processes) {
        this.processes = processes;
    }

    // Input: None, be sure the process array isn't empty
    // Output: Schedules processes and subsequently prints Gantt Charts
    public void scheduleProcesses() {
        int currentTime = 0;
        int processesScheduled = 0;
        // Preemptive Variables
        Process previousProcess = null; // To know if the Gantt Chart needs to be updated
        boolean firstIterationEver = true; // To set the previousProcess as the first process for the first iteration
        int cpuTime = 0; // CPU time of the current process

        // While not all processes have been scheduled
        while (processesScheduled < processes.length) {
            // Add processes to priority queue
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime && p.burst != 0)
                    pq.offer(p);
                if (firstIterationEver) {
                    previousProcess = p;
                    firstIterationEver = false;
                }
            }
            // Actually schedule processes
            if (preemptive) { // SRTF, P, RR
                Process p = pq.poll();
                if (previousProcess != p) {
                    // Handle CPU Update
                    p.burst--;
                    cpuTime++;
                    if (p.burst == 0) 
                        // *** Print currentTime, p.index, cpuTime, "X"
                } else {
                    // Handle previous process
                    // *** Print currentTime, previousProcess.index, cpuTime
                    // Handle current process
                    p.burst--;
                    cpuTime = 1; // Reset CPU time
                    previousProcess = p;
                    if (p.burst == 0)
                        // *** Print currentTime, p.index, cpuTime, "X"
                }

            } else { // Non-Preemptive: FCFS, SJF
                Process p = pq.poll();
                // *** Print currentTime, p.index, p.burst, "X"
                p.burst = 0;
                currentTime = currentTime + p.burst;
                processesScheduled++;
            }

        }
    }
/*
    @Override
    public int compare (Process p1, Process p2) {
        return p1.key-p2.key;
    }

    // Input: Priority Queue
    // Prints out Gantt Chart. Method gets called in every iteration of scheduleProcesses();
    public void printGantt(PriorityQueue<Process> pq) {
        // TODO Auto-generated method stub
        this.pq = pq;
        switch (schedulingAlgorithm) {
            case "FCFS":
                fcfs();
                break;
            case "SJF":
                break;
            case "SRTF":
                break;
            case "P":
                break;
            case "RR":
                break;
        }
    }

    private void fcfs() {
        int currTime = 0;
        while( !pq.isEmpty() ){
            System.out.println(currTime + pq.poll().arrival);
        }
        
    }
    */
}