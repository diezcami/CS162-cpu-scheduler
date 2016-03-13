import java.util.PriorityQueue;

public class ProcessScheduler{
	
	public static final int PREEMPTIVE = 0;
	public static final int NONPREEMPTIVE = 1;
	public static final int RR = 2;
    private Process[] processes;
    private PriorityQueue<Process> pq;
    private int algoType;
    
    public ProcessScheduler (String schedulingAlgorithm, Process[] processes) {
        this.processes = processes;
        if (schedulingAlgorithm.equals ("FCFS") || schedulingAlgorithm.equals ("SJF"))
            algoType = PREEMPTIVE;
        else algoType =  NONPREEMPTIVE;
        pq = new PriorityQueue<Process>();
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
                if (p.arrival <= currentTime && p.burst != 0)
                    pq.offer(p);
                if (firstIterationEver) {
                    previousProcess = p;
                    firstIterationEver = false;
                }
            }
            // Actually schedule processes
            if (algoType == PREEMPTIVE) { // SRTF, P, RR
                Process p = pq.poll();
                if (previousProcess != p) {
                    // Handle CPU Update
                    p.burst--;
                    cpuTime++;
                    if (p.burst == 0) 
                    	System.out.println(currentTime + " " + p.index + " " + cpuTime + "X");
                        // *** Print currentTime, p.index, cpuTime, "X"
                } else {
                    // Handle previous process
                	System.out.println(currentTime + " " + previousProcess.index + " " + cpuTime);
                    // *** Print currentTime, previousProcess.index, cpuTime
                    // Handle current process
                    p.burst--;
                    cpuTime = 1; // Reset CPU time
                    previousProcess = p;
                    if (p.burst == 0)
                    	System.out.println(currentTime + " " + p.index + " " + cpuTime + "X");
                        // *** Print currentTime, p.index, cpuTime, "X"
                }

            } else if (algoType == NONPREEMPTIVE){ // Non-Preemptive: FCFS, SJF
                Process p = pq.poll();
                System.out.println(currentTime + " " + p.index + " " + cpuTime + "X");
                // *** Print currentTime, p.index, p.burst, "X"
                p.burst = 0;
                currentTime = currentTime + p.burst;
                processesScheduled++;
            }else{
            	Process p = pq.poll();
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