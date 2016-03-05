import java.util.Comparator;
import java.util.PriorityQueue;

public class ProcessScheduler implements Comparator<Process>{
    private Process[] processes;
    private String schedulingAlgorithm;
    private PriorityQueue<Process> pq;
    

    public ProcessScheduler (String algo, Process[] processes) {
    	this.schedulingAlgorithm = algo;
        this.processes = processes;
    }

    public void setProcesses (Process[] processes) {
        this.processes = processes;
    }

    @Override
    public int compare (Process p1, Process p2) {
        /*switch (schedulingAlgorithm) {
            case "FCFS":
                comparison = p1.arrival - p2.arrival;
                return comparison;
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
        }*/

        return p1.key-p2.key;
    }

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



}