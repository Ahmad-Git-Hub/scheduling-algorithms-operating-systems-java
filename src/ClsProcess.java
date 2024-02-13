import java.util.*;

public class ClsProcess {

    static Scanner scan = new Scanner(System.in);
    String process_name;
    int arrival_time;
    int burst_time;

    static List<ClsProcess> list = new ArrayList<>();

    public ClsProcess() {

    }
    public ClsProcess(String process_name, int arrival_time, int burst_time) {
        this.process_name = process_name;
        this.burst_time = burst_time;
        this.arrival_time = arrival_time;
    }


    public static void enterProcess(){
        System.out.println("Enter process name: ");
        String process_name = scan.nextLine();
        System.out.println("Enter process arrival time: ");
        int burst_time = scan.nextInt();
        System.out.println("Enter process burst time: ");
        int arrival_time = scan.nextInt();
        ClsProcess process = new ClsProcess(process_name, arrival_time, burst_time);
        addProcessToList(process);
    }

    public static void addProcessToList(ClsProcess process) {
        list.add(process);
    }
    public void readProcess() {
        System.out.println(this.process_name);
        System.out.println(this.arrival_time);
        System.out.println(this.burst_time);
    }

    public void displayMenu(){
        int choice = 0;
        do {
            System.out.println("enter a choice: ");
            System.out.println("0. exit");
            System.out.println("1. enter a process");
            System.out.println("2. read a process");

            scan.nextLine();
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    enterProcess();
                    break;
                case 2:
                    readProcess();
                    break;
                case 0:
                    System.out.println("exiting");
                    break;
            }
        } while(choice != 3);
    }

    public static void algorithmFCFS() {
        List<ClsProcess> FCFS = list;
        int index = 0;
        while(!FCFS.isEmpty()) {
            FCFS.remove(0);
            index++;
        }
        print
    }



}
