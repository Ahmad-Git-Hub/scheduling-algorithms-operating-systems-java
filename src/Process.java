import java.util.*;

public class Process {

    static Scanner scan = new Scanner(System.in);
    String process_name;
    int arrival_time;
    int burst_time;

    static List<Process> list = new ArrayList<>();

    public Process() {

    }
    public Process(String process_name, int arrival_time, int burst_time) {
        this.process_name = process_name;
        this.burst_time = burst_time;
        this.arrival_time = arrival_time;
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
        System.out.printf("\033[1;33m%-15s\033[0m   \033[1;36m%-12d\033[0m%n", "Arrival Time", this.arrival_time);
        System.out.printf("\033[1;33m%-15s\033[0m   \033[1;36m%-12d\033[0m%n", "Burst Time", this.burst_time);
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
        int choice = 0;
        do {
            try {
                System.out.println("============================================");
                System.out.println("|                 Main Menu                |");
                System.out.println("============================================");
                System.out.println("| 0. exit                                   |");
                System.out.println("| 1. enter a process                        |");
                System.out.println("| 2. display all processes                  |");
                System.out.println("| 3. FCFS                                   |");
                System.out.println("| 4. SJF                                   |");
                System.out.println("============================================");
                System.out.print("Enter your choice: ");

                choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 0 -> System.out.println("Exiting");
                    case 1 -> enterProcess();
                    case 2 -> displayAllProcesses();
                    case 3 -> FcfsAlgorithm();
                    case 4 -> sjf();

                    default -> System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
                choice = -1;
            }
        } while (choice != 0);
    }

    public static void sjf(){

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

        Process temp = new Process();

//        int min = 0;
//        for(int  i = 0; i < array.length - 1; i++) {
//            for(int j = i+1; j < array.length; j++) {
//                if(min > array[j].arrival_time) {
//                    min = array[j].arrival_time;
//                    temp = array[i];
//                    array[i] = array[j];
//                    array[j] = temp;
//                }
//            }
//        }
        for(int  i = 0; i < array.length - 1; i++) {
            for(int j = i+1; j < array.length; j++) {
                if(array[i].burst_time > array[j].burst_time) {
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

    public static void FcfsAlgorithm() {
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

        Process temp = new Process();
        for(int  i = 0; i < array.length - 1; i++) {
            for(int j = i+1; j < array.length; j++) {
                if(array[i].arrival_time > array[j].arrival_time) {
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
