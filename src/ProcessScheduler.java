import java.util.Arrays;
import java.util.PriorityQueue;

public class ProcessScheduler{
	
	public static final int PREEMPTIVE = 0;
	public static final int NONPREEMPTIVE = 1;
	public static final int RR = 2;
    private Process[] processes;
    private PriorityQueue<Process> pq;
    private int algoType;
    private int quantum;
	private int rrpriority;
    
    public ProcessScheduler (String schedulingAlgorithm, Process[] processes) {
    	this.quantum = processes[0].quantum;
        this.processes = processes;
        if (schedulingAlgorithm.equals ("FCFS") || schedulingAlgorithm.equals ("SJF"))
            algoType = NONPREEMPTIVE;
        else if (schedulingAlgorithm.equals ("RR")){
        	algoType = RR;
        	sortProcesses(this.processes);
        }
        else
            algoType = PREEMPTIVE;
        pq = new PriorityQueue<Process>();
    }
    private void sortProcesses(Process[] sortArr) {
    	rrpriority = 0;
		for( Process p : sortArr ){
			p.schedulingAlgorithm = "FCFS";
		}
    	Arrays.sort(sortArr);
    	for( int i = 0; i < sortArr.length; i++ ){
    		sortArr[i].priority = i;
    		rrpriority++;
    	}
    	for( Process p : sortArr ){
			p.schedulingAlgorithm = "RR";
		}
	}

	// Input: None, be sure the process array isn't empty
    // Output: Schedules processes and subsequently prints Gantt Charts
    public void scheduleProcesses() {
        int currentTime = 0; // Time elapsed since scheduler started
        int processesFinished = 0;
        // Preemptive Variables
        Process previousProcess = null; // To know if the Gantt Chart needs to be updated
        boolean firstIterationEver = true; // To set the previousProcess as the first process for the first iteration
        int cpuTime = 0; // CPU time of the current process
        int startingTime = 0;

        // While not all processes have been scheduled
        while (processesFinished < processes.length) {
            // Add processes to priority queue
    		for (Process p : processes) {
                if (p.arrival <= currentTime && p.burst != 0)
                	//if(algoType == RR && p)
                    pq.add(p);         
        	}

            if (pq.isEmpty()) {
                currentTime++;
                continue;
            }
   
            Process p = pq.poll();
            if (firstIterationEver) {
                previousProcess = p;
                firstIterationEver = false;
            }       
            // ===================================================
            // PREEMPTIVE : SRTF, P
            // ===================================================
            if (algoType == PREEMPTIVE) { 
                if (previousProcess == p) {
                    // Handle CPU Update
                    p.burst--;
                    cpuTime++;
                    if (p.burst == 0) {
                    	System.out.println(startingTime + " " + p.index + " " + cpuTime + "X");
                        startingTime = currentTime;
                        processesFinished++;
                        cpuTime = 0;
                    }
                } else {
                    // Handle previous process
                    if (previousProcess.burst != 0)
                	   System.out.println(startingTime + " " + previousProcess.index + " " + cpuTime);
                    // Handle current process
                    p.burst--;
                    cpuTime = 1; // Reset CPU time
                    previousProcess = p;
                    if (p.burst == 0) {
                    	System.out.println(startingTime + " " + p.index + " " + cpuTime + "X");
                        processesFinished++;
                        cpuTime = 0;
                    }
                    startingTime = currentTime;
                }

            // ===================================================
            // NON-PREEMPTIVE : FCFS, SJF
            // ===================================================
            } else if (algoType == NONPREEMPTIVE){ // Non-Preemptive: FCFS, SJF
                System.out.println(currentTime + " " + p.index + " " + p.burst + "X");
                currentTime = currentTime + p.burst -1;
                p.burst = 0;
                processesFinished++;


            // ===================================================
            // ROUND ROBIN
            // ===================================================
            } else { 
            	if (previousProcess == p) {
                    // Handle CPU Update
                    p.burst--;
                    p.quantum--;
                    p.priority = -1;
                    if( p.quantum == 0 ){
                    	p.priority = rrpriority++;
                    	p.quantum = quantum;
                    	p.arrival = currentTime;
                    }
                    cpuTime++;
                    if (p.burst == 0) {
                    	System.out.println(startingTime + " " + p.index + " " + cpuTime + "X");
                        startingTime = currentTime;
                        processesFinished++;
                        cpuTime = 0;
                    }
                } else {
                    // Handle previous process
                    if (previousProcess.burst != 0){
                    	System.out.println(startingTime + " " + previousProcess.index + " " + cpuTime);
                    	startingTime = currentTime;
                    }
                	   
                    // Handle current process
                    p.burst--;
                    p.quantum--;
                    if( p.quantum == 0 ){
                    	p.priority = rrpriority++;
                    	p.quantum = quantum;
                    	p.arrival = currentTime;
                    }
                    cpuTime = 1; // Reset CPU time
                    previousProcess = p;
                    if (p.burst == 0) {
                    	System.out.println(startingTime + " " + p.index + " " + cpuTime + "X");
                        processesFinished++;
                        cpuTime = 0;
                    }
                    startingTime = currentTime;
                }
            }
        
            previousProcess = p;
            currentTime++;
            pq.clear();    
        }
    }
}