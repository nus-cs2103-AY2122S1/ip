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
            String command = scanner.nextLine();
            String[] commandSplit = command.split(" ", 2);
            String commandType = "";
            if (commandSplit.length>= 1) {
                commandType = commandSplit[0];
            }
            boolean aborted = false;
            try {
                switch (commandType) {
                    case "bye":
                        aborted = true;
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    case "list":
                        ArrayList<Task> tasks = taskList.getTasks();
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                        }
                        break;
                    case "done":
                        int i = Integer.parseInt(commandSplit[1].trim());
                        Task task = taskList.markTaskAsDone(i - 1);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.printf("%s\n", task);
                        break;
                    case "todo": {
                        if (commandSplit.length < 2) {
                            throw new DukeException(String.format("The description of a %s cannot be empty.", commandType));
                        }
                        String taskName = commandSplit[1].trim();
                        Task newTask = new Todo(taskName);
                        taskList.addTask((newTask));
                        System.out.println("Got it. I've added this task: ");
                        System.out.printf("%s\n", newTask);
                        System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
                        break;
                    }
                    case "deadline": {
                        if (commandSplit.length < 2) {
                            throw new DukeException(String.format("The description of a %s cannot be empty.", commandType));
                        }
                        String[] nameAndTime = commandSplit[1].split("/by",2);
                        if (nameAndTime.length < 2) {
                            throw new DukeException(String.format("The dateTime of a %s cannot be empty.", commandType));
                        }
                        String taskName = nameAndTime[0].trim();
                        String dateTime = nameAndTime[1].trim();
                        Task newTask = new Deadline(taskName, dateTime);
                        taskList.addTask((newTask));
                        System.out.println("Got it. I've added this task: ");
                        System.out.printf("%s\n", newTask);
                        System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
                        break;
                    }
                    case "event": {
                        if (commandSplit.length < 2) {
                            throw new DukeException(String.format("The description of a %s cannot be empty.", commandType));
                        }
                        String[] nameAndTime = commandSplit[1].split("/at",2);
                        if (nameAndTime.length < 2) {
                            throw new DukeException(String.format("The dateTime of a %s cannot be empty.", commandType));
                        }
                        String taskName = nameAndTime[0].trim();
                        String dateTime = nameAndTime[1].trim();
                        Task newTask = new Event(taskName, dateTime);
                        taskList.addTask((newTask));
                        System.out.println("Got it. I've added this task: ");
                        System.out.printf("%s\n", newTask);
                        System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
                        break;
                    }
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DukeException e) {
                System.out.printf("HuAI Liddat!!! %s\n",e);
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            if (aborted) {
                break;
            }
        }
    }
}
