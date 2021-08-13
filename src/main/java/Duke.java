import components.Task;
import components.TaskList;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";
        TaskList taskList = new TaskList();
        System.out.println(greetingMessage);

        boolean userExit = false;
        while (!userExit) {
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "bye":
                    userExit = true;
                    break;
                case "list":
                    System.out.println(taskList.toString());
                    break;
                default:
                    Task task = new Task(userInput);
                    boolean addSuccess = taskList.addTask(task);
                    if (addSuccess) {
                        System.out.println(String.format("added: %s", userInput));
                    } else {
                        System.out.println("Whoops! Something went wrong.");
                    }
            }
        }
        if (userExit) {
            String goodbyeMessage = "Bye. Hope to see you again soon!";
            System.out.println(goodbyeMessage);
        }
    }
}
