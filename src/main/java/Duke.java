import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException{
        Scanner scanner = new Scanner(System.in);
        List<Task> inputs = new ArrayList<Task>();

        System.out.println("Hi, I'm Duke, your personal assistant!\n");
        System.out.println("What should I add to your schedule?");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye from Duke!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Current List:");
                System.out.println("---------------");
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println( (i+1) + ": " + inputs.get(i));
                }
            } else if (input.matches("done\\s[1-9][0-9]?")) {
                int taskToComplete = Integer.valueOf(input.split(" ")[1]);
                if (taskToComplete - 1 >= 0 && taskToComplete - 1 <inputs.size()) {
                    inputs.get(taskToComplete - 1).markAsCompleted();
                    System.out.println("I have marked the task as done!");
                    System.out.println("Current List:");
                    System.out.println("---------------");
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println( (i+1) + ": " + inputs.get(i));
                    }
                } else {
                    throw new InvalidTaskIDException();
                }
            } else if (input.matches("todo(.*?)")) {
                if (input.split(" ").length < 2) {
                    throw new EmptyTaskDescriptionException();
                } else {
                    String taskname = input.split(" ", 2)[1];
                    ToDo todo = new ToDo(taskname);
                    inputs.add(todo);
                    System.out.println("A ToDo has been added\n");
                    System.out.println("Current List:");
                    System.out.println("---------------");
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println( (i+1) + ": " + inputs.get(i));
                    }
                }

            } else if (input.matches("deadline(.*?)")) {
                if (input.split(" ").length < 2) {
                    throw new EmptyTaskDescriptionException();
                } else {
                    String firstCommand = input.split("/by", 2)[0];
                    String taskname = firstCommand.split(" ", 2)[1];
                    String dueDate = input.split("/by", 2)[1];
                    Deadline deadline = new Deadline(taskname.trim(), dueDate);
                    inputs.add(deadline);
                    System.out.println("A Deadline has been added\n");
                    System.out.println("Current List:");
                    System.out.println("---------------");
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println( (i+1) + ": " + inputs.get(i));
                    }
                }

            } else if (input.matches("event(.*?)")) {
                if (input.split(" ").length < 2) {
                    throw new EmptyTaskDescriptionException();
                } else {
                    String firstCommand = input.split("/at", 2)[0];
                    String taskname = firstCommand.split(" ", 2)[1];
                    String duration = input.split("/at", 2)[1];
                    Event event = new Event(taskname.trim(), duration);
                    inputs.add(event);
                    System.out.println("An Event has been added\n");
                    System.out.println("Current List:");
                    System.out.println("---------------");
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println((i + 1) + ": " + inputs.get(i));
                    }
                }
            } else
                throw new InvalidCommandException();
            }


        }
}
