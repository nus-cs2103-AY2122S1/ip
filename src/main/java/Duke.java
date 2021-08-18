import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! This is Jarvis.");
        System.out.println("What can I do for you sir?");
        String [] tasks = new String[100];
        int taskIndex = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            String task = sc.nextLine();
            if (task.equals("bye")) {
                System.out.println("Goodbye Sir. Hope you have a pleasant day sir.");
                break;
            } else if (task.equals("list")) {
                for (int i = 0; i < taskIndex; i++) {
                    System.out.printf("%d. %s%n", i + 1, tasks[i]);
                }
            } else {
                tasks[taskIndex] = task;
                taskIndex++;
                System.out.println("added: " + task);
            }
        }
        sc.close();
    }
}
