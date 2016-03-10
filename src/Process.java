public class Process implements Comparable<Process>{
    public int arrival;
    public int burst;
    public int priority;
    public int quantum;
    public int key;
    public String schedulingAlgorithm;

    public Process (int arrival, int burst, int priority, int quantum, String schedulingAlgorithm) {
        this.arrival = arrival;
        this.burst = burst;
        this.priority = priority;
        this.quantum = quantum;
        this.schedulingAlgorithm = schedulingAlgorithm;
    }

    public int compareTo (Process p) {
        switch (schedulingAlgorithm) {
            case "FCFS":
                return Integer.compare(this.arrival, p.arrival);
                break;
            case "SJF":
                if (this.burst != p.burst)
                    return Integer.compare(this.burst, p.burst);
                return Integer.compare(this.arrival, p.arrival);
                break;
            case "SRTF":
                if (this.burst != p.burst)
                    return Integer.compare(this.burst, p.burst);
                return Integer.compare(this.arrival, p.arrival);
                break;
            case "P":
                if (this.priority != p.priority)
                    return Integer.compare(this.priority, p.priority);
                return Integer.compare(this.arrival, p.arrival);
                break;
            case "RR":
                return Integer.compare(this.arrival, p.arrival);
                break;
        }
    }
}
// FCFS (NP): Sort by arrival time
// SJF (NP): Sort by burst, else arrival time
// SRTF (P): Sort by burst, burst-- after every iteration (handled in ProcessScheduler), else arrival time
// P (P): Sort by priority, else arrival time
// RR (P): Sort by arrival, do time quantum stuff in ProcessScheduler

