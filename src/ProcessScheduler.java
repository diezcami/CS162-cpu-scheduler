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

        // While not all processes have been scheduled
        while (processesScheduled < processes.length) {
            // Add processes to priority queue
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime)
                    pq.offer(p);
            }
            // Actually schedule processes
            if (preemptive) { // SRTF, P, RR
                Process p = pq.poll();

            } else { // Non-Preemptive: FCFS, SJF
                Process p = pq.poll();
                // Print i, p.index, p.burst, "X"
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