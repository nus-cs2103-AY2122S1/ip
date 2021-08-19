import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static void handleCommands(String commandInput, List<Task> taskList) throws DukeException {
        if (commandInput.equals("list")) {
            System.out.println("Current List:");
            System.out.println("---------------");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println( (i+1) + ": " + taskList.get(i));
            }
        } else if (commandInput.matches("done\\s[0-9][0-9]?")) {
            int taskToComplete = Integer.valueOf(commandInput.split(" ")[1]);
            if (taskToComplete - 1 >= 0 && taskToComplete - 1 < taskList.size()) {
                taskList.get(taskToComplete - 1).markAsCompleted();
                System.out.println("I have marked the task as done!");
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println( (i+1) + ": " + taskList.get(i));
                }
            } else {
                throw new InvalidTaskIDException();
            }
        } else if (commandInput.matches("delete\\s[0-9][0-9]?")) {
            int taskToComplete = Integer.valueOf(commandInput.split(" ")[1]);
            if (taskToComplete - 1 >= 0 && taskToComplete - 1 <taskList.size()) {
                taskList.remove(taskToComplete - 1);
                System.out.println("I have deleted the task!");
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println( (i+1) + ": " + taskList.get(i));
                }

            } else {
                throw new InvalidTaskIDException();
            }

        } else if (commandInput.matches("todo(.*?)")) {
            if (commandInput.split(" ").length < 2) {
                throw new EmptyTaskDescriptionException();
            } else {
                String taskname = commandInput.split(" ", 2)[1];
                ToDo todo = new ToDo(taskname);
                taskList.add(todo);
                System.out.println("A ToDo has been added\n");
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println( (i+1) + ": " + taskList.get(i));
                }
            }

        } else if (commandInput.matches("deadline(.*?)")) {
            if (commandInput.split(" ").length < 2) {
                throw new EmptyTaskDescriptionException();
            } else {
                String firstCommand = commandInput.split("/by", 2)[0];
                String taskname = firstCommand.split(" ", 2)[1];
                String dueDate = commandInput.split("/by", 2)[1];
                Deadline deadline = new Deadline(taskname.trim(), dueDate);
                taskList.add(deadline);
                System.out.println("A Deadline has been added\n");
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println( (i+1) + ": " + taskList.get(i));
                }
            }

        } else if (commandInput.matches("event(.*?)")) {
            if (commandInput.split(" ").length < 2) {
                throw new EmptyTaskDescriptionException();
            } else {
                String firstCommand = commandInput.split("/at", 2)[0];
                String taskname = firstCommand.split(" ", 2)[1];
                String duration = commandInput.split("/at", 2)[1];
                Event event = new Event(taskname.trim(), duration);
                taskList.add(event);
                System.out.println("An Event has been added\n");
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ": " + taskList.get(i));
                }
            }
        } else
            throw new InvalidCommandException();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> taskList = new ArrayList<Task>();
        System.out.println("Hi, I'm Duke, your personal assistant!\n");
        System.out.println("What should I add to your schedule?");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye from Duke!");
                break;
            } else {
                try {
                    handleCommands(input, taskList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            }


        }
    }
}
