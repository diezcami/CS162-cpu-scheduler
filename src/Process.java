public class Process {
    public int arrival;
    public int burst;
    public int priority;
    public int quantum;
    public int key;

    public Process (int arrival, int burst, int priority, int quantum) {
        this.arrival = arrival;
        this.burst = burst;
        this.priority = priority;
        this.quantum = quantum;
    }

    /*public enum SchedulingAlgorithm {
        // First Line: Non-preemptive
        // Second Line: Preemptive
        FCFS, SJF, 
        SRTF, P, RR
    }*/


}