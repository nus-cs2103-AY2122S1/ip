import components.Task;
import components.TaskList;
import utils.InputParser;

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
            InputParser inputParser = new InputParser(userInput);
            String command = inputParser.getCommand();
            String otherArguments = inputParser.getOtherArguments();
            switch (command) {
                case "bye":
                    userExit = true;
                    break;
                case "list":
                    System.out.println(taskList.toString());
                    break;
                case "done":
                    if (otherArguments.equals("")) {
                        System.out.println("Please re-enter the command, with the index of the task to be marked as done.");
                    } else {
                        int index = Integer.parseInt(otherArguments.replaceAll(" ", ""));
                        if (1 <= index && index <= taskList.size()) {
                            taskList.markTaskDone(index - 1);
                            System.out.println(String.format("Nice! I've marked this task as done: \n \t %s", taskList.showTask(index - 1)));
                        } else {
                            System.out.println("Index is out of bounds.");
                        }
                    }
                    break;
                default:
                    Task task = new Task(userInput);
                    taskList.addTask(task);
                    System.out.println(String.format("added: %s", userInput));
            }
        }
        if (userExit) {
            String goodbyeMessage = "Bye. Hope to see you again soon!";
            System.out.println(goodbyeMessage);
        }
    }
}
