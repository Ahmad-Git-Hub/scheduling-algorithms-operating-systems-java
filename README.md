# Process Scheduling Simulator

This Java program simulates various process scheduling algorithms, including First-Come, First-Served (FCFS), Shortest Job First (SJF), and Round Robin.

## Features

* **Process Management:**
    * Create and add processes with their names, arrival times, and burst times.
    * Display all entered processes.
* **Scheduling Algorithms:**
    * **FCFS:** Implements the First-Come, First-Served scheduling algorithm.
    * **SJF:** Implements the Shortest Job First scheduling algorithm.
    * **Round Robin:** Implements the Round Robin scheduling algorithm with a user-defined time quantum.
* **User Interface:**
    * A menu-driven interface for selecting scheduling algorithms and entering process data.
    * Clear output of process information (arrival time, burst time, completion time, waiting time, and turnaround time) after each algorithm execution.


## Example

* | Main Menu |
* | 0. exit |
* | 1. enter a process |
* | 2. display all processes |
* | 3. FCFS |
* | 4. SJF |
* | 5. Round Robin |
* Enter your choice: 1
* Enter process name: P1
* Enter process arrival time: 0
* Enter process burst time: 5
* adding process: P1
* ...

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.