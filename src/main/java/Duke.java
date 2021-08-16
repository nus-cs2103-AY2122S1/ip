import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();


    public static void main(String[] args) {
        System.out.println("Hello, I am Duke. What can I do for you?");
        waitResponse();
    }

    private static void waitResponse() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()){
            String command = scanner.next();
            String action = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                return;
            } else if (command.equals("list")){
                System.out.println("List of Tasks:");
                for (int i = 0; i<tasks.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
                }
                waitResponse();
            } else if (command.equals("done")){
                int taskNumber = Integer.parseInt(action.trim());
                if (taskNumber <= tasks.size()) {
                    Task taskToComplete = tasks.get(taskNumber - 1);
                    taskToComplete.complete();
                    System.out.println("Nice! I've marked this task as done:\n");
                    System.out.println(taskToComplete);
                } else {
                    System.out.println("This task does not exist! Use the list command to see your tasks.");
                }
                waitResponse();
            } else {
                String next = command + action;
                tasks.add(new Task(next));
                System.out.println(String.format("Added: %s", next));
                waitResponse();
            }
        }
    }
}
