import java.util.*;

public class Process {

    static Scanner scan = new Scanner(System.in);
    String process_name;
    int arrivalTime;
    int burstTime;
    int currentBurstTime;
    int completionTime = 0;
    int waitingTime = 0;
    int turnAroundTime = 0;

    static List<Process> list = new ArrayList<>();

    public Process() {

    }
    public Process(String process_name, int arrivalTime, int burst_time) {
        this.process_name = process_name;
        this.burstTime = burst_time;
        this.arrivalTime = arrivalTime;
        this.currentBurstTime=burstTime;
    }


    public static void enterProcess(){
        System.out.print("Enter process name: ");
        String process_name = scan.nextLine();
        System.out.print("Enter process arrival time: ");
        int arrival_time = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter process burst time: ");
        int burst_time = scan.nextInt();
        scan.nextLine();
        Process process = new Process(process_name, arrival_time, burst_time);
        addProcessToList(process);
    }

    public static void addProcessToList(Process process) {
        System.out.println("adding process: "+ process.process_name);
        list.add(process);
    }
    public void displayProcess() {
        System.out.println("──────────────────────────────────────");
        System.out.printf("\033[1;33m%-15s\033[0m   \033[1;36m%-12s\033[0m%n", "Process Name", this.process_name);
        System.out.printf("\033[1;33m%-15s\033[0m   \033[1;36m%-12d\033[0m%n", "Arrival Time", this.arrivalTime);
        System.out.printf("\033[1;33m%-15s\033[0m   \033[1;36m%-12d\033[0m%n", "Burst Time", this.burstTime);
        System.out.println("──────────────────────────────────────");
    }


    public void displayAllProcesses(){
        if(list.isEmpty()) {
            System.out.println("There are no processes ");
        }
        for(Process p : list) {
            p.displayProcess();
        }
    }

    public void displayMenu() {
        int choice;
        boolean roundRobinUsed = false;
        do {
            try {
                System.out.println("============================================");
                System.out.println("|                 Main Menu                 |");
                System.out.println("============================================");
                System.out.println("| 0. exit                                   |");
                System.out.println("| 1. enter a process                        |");
                System.out.println("| 2. display all processes                  |");
                System.out.println("| 3. FCFS                                   |");
                System.out.println("| 4. SJF                                    |");
                System.out.println("| 5. Round Robin                            |");
                System.out.println("============================================");
                System.out.print("Enter your choice: ");

                choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 0 -> System.out.println("Exiting");
                    case 1 -> enterProcess();
                    case 2 -> displayAllProcesses();
                    case 3 -> FCFS();
                    case 4 -> SJF();
                    case 5 -> {
                        if(!roundRobinUsed) {
                            roundRobinUsed=true;
                            roundRobin();
                        } else {
                            System.out.println("Choose another algorithm");
                        }
                    }


                    default -> System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
                choice = -1;
            }
        } while (choice != 0);
    }


    public static void roundRobin() {
        int time = 0;
        int quantum = 5;
        if(list.isEmpty()) {
            System.out.println("There are no processes ");
            return;
        }
        Process[] array = new Process[list.size()];
        int array_index = 0;
        for(Process currentProcess : list) {
            array[array_index] = currentProcess;
            array_index++;
        }
        Process temp;
        for(int  i = 0; i < array.length - 1; i++) {
            for(int j = i+1; j < array.length; j++) {
                if(array[i].arrivalTime > array[j].arrivalTime) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        int completedProcesses=0;
        int tempBurstTime;
        for(int i = 0; completedProcesses != array.length; i++) {
            i = i % (array.length);
            if (array[i].currentBurstTime != 0) {
                tempBurstTime = array[i].currentBurstTime - quantum;
                if (tempBurstTime < 0) {
                    time += quantum + tempBurstTime;
                    array[i].currentBurstTime = 0;
                    array[i].completionTime = time;
                    array[i].turnAroundTime = array[i].completionTime - array[i].arrivalTime;
                    array[i].waitingTime = array[i].turnAroundTime - array[i].burstTime;
                    completedProcesses++;
                } else if (tempBurstTime == 0) {
                    time += quantum;
                    array[i].currentBurstTime = 0;
                    array[i].completionTime = time;
                    array[i].turnAroundTime = array[i].completionTime - array[i].arrivalTime;
                    array[i].waitingTime = array[i].turnAroundTime - array[i].burstTime;
                    completedProcesses++;
                } else {
                    array[i].currentBurstTime = tempBurstTime;
                    time += quantum;
                }

                System.out.println("process: " + array[i].process_name + " burst time: " + array[i].currentBurstTime);
            }

        }

        System.out.println("──────────────────────────────────────");
        System.out.printf("\033[1;33m%-1s\033[0m   \033[1;36m%-5s\033[0m", "completed processes: ", completedProcesses);
         double avgWT = 0;
         double avgTAT = 0;
         double avgCT = 0;
        for(Process p : array) {
            System.out.println();
            System.out.printf("\033[1;33m%-1s\033[0m   \033[1;36m%-5s\033[0m", "Process Name", p.process_name);
            System.out.printf("\033[1;33m%-1s\033[0m   \033[1;36m%-5d\033[0m", "AT", p.arrivalTime);
            System.out.printf("\033[1;33m%-1s\033[0m   \033[1;36m%-5d\033[0m", "BT", p.burstTime);
            System.out.printf("\033[1;33m%-1s\033[0m   \033[1;36m%-5d\033[0m", "CT", p.completionTime);
            System.out.printf("\033[1;33m%-1s\033[0m   \033[1;36m%-5d\033[0m", "TAT", p.turnAroundTime);
            System.out.printf("\033[1;33m%-1s\033[0m   \033[1;36m%-5d\033[0m", "WT", p.waitingTime);
            avgWT+=p.waitingTime;
            avgTAT += p.turnAroundTime;
            avgCT += p.completionTime;
        }
        avgWT /= array.length;
        avgTAT /= array.length;
        avgCT /= array.length;

        System.out.printf("\033[1;33m%-1s\033[0m   \033[1;36m%-5f\033[0m", "\nAverage waiting time:", avgWT);
        System.out.printf("\033[1;33m%-1s\033[0m   \033[1;36m%-5f\033[0m", "\nAverage Completion time:", avgCT);
        System.out.printf("\033[1;33m%-1s\033[0m   \033[1;36m%-5f\033[0m", "\nAverage Turn Around Time time:", avgTAT);
        System.out.println("\n──────────────────────────────────────");

    }
    public static void SJF(){

        if(list.isEmpty()) {
            System.out.println("There are no processes ");
            return;
        }
        Process[] array = new Process[list.size()];
        int array_index = 0;
        for(Process currentProcess : list) {
            array[array_index] = currentProcess;
            array_index++;
        }

        Process temp;


        for(int  i = 0; i < array.length - 1; i++) {
            for(int j = i+1; j < array.length; j++) {
                if(array[i].burstTime > array[j].burstTime) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        for (Process clsProcess : array) {
            clsProcess.displayProcess();
        }

    }

    public static void FCFS() {
        if(list.isEmpty()) {
            System.out.println("There are no processes ");
            return;
        }
        Process[] array = new Process[list.size()];
        int array_index = 0;
        for(Process currentProcess : list) {
            array[array_index] = currentProcess;
            array_index++;
        }

        Process temp;
        for(int  i = 0; i < array.length - 1; i++) {
            for(int j = i+1; j < array.length; j++) {
                if(array[i].arrivalTime > array[j].arrivalTime) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        for (Process clsProcess : array) {
            clsProcess.displayProcess();
        }

    }





}
