import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        // Hello message
        System.out.println(
            "Lollipop: Hello! I am your personal chatbot, Lollipop! " +
            "What would you like to do today?");
        System.out.println("");
        System.out.print("You: ");
        String command = scanner.nextLine();

        // Commands
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Lollipop: Here are your tasks");
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    System.out.printf("%d. [%s] %s%n", i + 1, task.getStatusIcon(), task.toString());
                }
            } else if (command.contains("done")) {
                int taskNumber = parseInt(command.split(" ")[1]);
                Task task = taskList.get(taskNumber - 1);
                task.markAsDone();
                System.out.printf("Lollipop: %s has been marked as done.%n", task.toString());
            } else {
                Task task = new Task(command);
                taskList.add(task);
                System.out.printf("Lollipop: %s has been added.%n", task.toString());
            }

            System.out.println("");
            System.out.print("You: ");
            command = scanner.nextLine();
        }

        // Goodbye message
        System.out.println("Lollipop: See you again soon!");
    }
}
