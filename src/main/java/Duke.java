import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner clas

public class Duke {
    public static void main(String[] args) {
        String botName = "HuAI";
        System.out.printf("Hello! I'm %s\n", botName);
        System.out.printf("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        TaskList taskList = new TaskList();
        while (true) {
            String command = scanner.nextLine();
            boolean aborted = false;
            switch (command) {
                case "bye":
                    aborted = true;
                    System.out.printf("Bye. Hope to see you again soon!\n");
                    break;
                case "list":
                    ArrayList<Task> tasks = taskList.getTasks();
                    for (int i = 0 ; i < tasks.size(); i++){
                        System.out.printf("%d. %s\n", i+1, tasks.get(i));
                    }
                    break;
                default:
                    taskList.addTask(new Task(command));
                    System.out.printf("added: %s\n", command);
                    break;
            }
            if (aborted) {
                break;
            }
        }
    }
}
