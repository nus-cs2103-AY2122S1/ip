import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner clas

public class Duke {
    public static void main(String[] args) {
        String botName = "HuAI";
        System.out.printf("Hello! I'm %s\n", botName);
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        TaskList taskList = new TaskList();
        while (true) {
            String command = scanner.next();
            boolean aborted = false;
            switch (command) {
                case "bye":
                    aborted = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    scanner.nextLine();
                    break;
                case "list":
                    ArrayList<Task> tasks = taskList.getTasks();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0 ; i < tasks.size(); i++){
                        System.out.printf("%d. %s\n", i+1, tasks.get(i));
                    }
                    scanner.nextLine();
                    break;
                case "done":
                    int i = scanner.nextInt();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.printf("%s\n",taskList.markTaskAsDone(i-1));
                    scanner.nextLine();
                    break;
                case "todo":
                    String taskName = scanner.nextLine().trim();
                    taskList.addTask(new Task(taskName));
                    System.out.printf("added: %s\n", taskName);
                    break;
            }
            if (aborted) {
                break;
            }
        }
    }
}
