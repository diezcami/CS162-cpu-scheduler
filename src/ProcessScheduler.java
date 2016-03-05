import java.util.Comparator;
import java.util.PriorityQueue;

public class ProcessScheduler implements Comparator<Process>{
    private Process[] processes;
    private String schedulingAlgorithm;

    public ProcessScheduler (String algo, Process[] processes) {
    	this.schedulingAlgorithm = algo;
        this.processes = processes;
    }

    public void setProcesses (Process[] processes) {
        this.processes = processes;
        PriorityQueue pq = new PriorityQueue<>();
    }

    public void setAlgorithm (String algorithm) {

    }

    public void scheduleProcesses (String algorithm) {

    }

    @Override
    public int compare (Process p1, Process p2) {
        int comparison = 0;
        switch (schedulingAlgorithm) {
            case "FCFS":
                comparison = p1.arrival - p2.arrival;
                if (comparison != 0) return comparison;
                break;
            case "SJF":
                comparison = p1.burst - p2.burst;
                if (comparison != 0) return comparison;
                break;
            case "SRTF":
                if (comparison != 0) return comparison;
                break;
            case "P":
                comparison = p1.priority - p2.priority;
                if (comparison != 0) return comparison;
                break;
        }

        return 0;
    }



}