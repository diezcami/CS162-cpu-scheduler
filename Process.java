public class Process {
    public int arrival;
    public int burst;
    public int priority;
    public int quantum;

    public Process (int arrival, int burst, int priority) {
        this.arrival = arrival;
        this.burst = burst;
        this.priority = priority;
        quantum = 0;
    }

    public enum SchedulingAlgorithm {
        // First Line: Non-preemptive
        // Second Line: Preemptive
        FCFS, SJF, 
        SRTF, P, RR
    }

    public static Comparator<Process> getComparator(SchedulingAlgorithm sa) {
        return new ProcessScheduler(sa);
    }
}