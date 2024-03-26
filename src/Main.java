public class Main {
    public static void main(String[] args) {

        // testing round-robin
        Process process = new Process();
        Process p1 = new Process("p1", 0, 7);
        Process p2 = new Process("p2", 1, 4);
        Process p3 = new Process("p3", 2, 15);
        Process p4 = new Process("p4", 3, 11);
        Process p5 = new Process("p5", 4, 20);
        Process p6 = new Process("p6", 4, 9);
        Process.list.add(p1);
        Process.list.add(p2);
        Process.list.add(p3);
        Process.list.add(p4);
        Process.list.add(p5);
        Process.list.add(p6);
        process.displayMenu();

    }
}