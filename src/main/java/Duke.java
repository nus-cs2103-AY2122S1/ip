import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Initial values
        String sepLine = "____________________________________________________________";
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);

        String start = "Hello! I'm Duke. \n"
                + "What can I do for you? \n"
                + sepLine;

        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println(start);

        // Main loop for commands
        while (isRunning) {
            String next = sc.nextLine();
            if (next.equals("bye")) {
                System.out.println(sepLine + "\n" + "Bye. Hope to see you again soon!" + "\n" + sepLine);
                isRunning = false;
            } else if (next.equals("list")) {
                System.out.println(sepLine);
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print(i + 1 + ". " + taskList.get(i) + "\n");
                }
                System.out.println(sepLine);
            } else {
                // Add a task to the task list
                taskList.add(new Task(next));
                System.out.println(sepLine + "\n added: " + next + "\n" + sepLine);
            }
        }
    }
}
