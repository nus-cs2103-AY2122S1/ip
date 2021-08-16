import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }

    public static void run(Scanner scanner) {
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
            try {
                if (command.equals("list")) {
                    if (taskList.size() == 0) {
                        System.out.println("Lollipop: You have no tasks available.");
                    } else {
                        System.out.println("Lollipop: Here are your tasks");
                        for (int i = 0; i < taskList.size(); i++) {
                            Task task = taskList.get(i);
                            System.out.printf("%d. %s%n", i + 1, task.toString());
                        }
                    }

                } else if (command.startsWith("done")) {
                    int taskNumber = parseInt(command.split(" ")[1]);
                    Task task = taskList.get(taskNumber - 1);
                    task.markAsDone();
                    System.out.printf("Lollipop: %s has been marked as done.%n", task.toString());

                } else if (command.startsWith("delete")) {
                    int taskNumber = parseInt(command.split(" ")[1]);
                    Task task = taskList.get(taskNumber - 1);
                    taskList.remove(taskNumber - 1);
                    System.out.printf("Lollipop: %s has been deleted.%n", task.toString());

                } else if (command.startsWith("todo")) {
                    String[] splitCommand = command.split(" ", 2);
                    if (splitCommand.length == 1) {
                        throw new DukeException("Please fill in a description for todo.");
                    }
                    String description = splitCommand[1];
                    Task task = new Todo(description);
                    taskList.add(task);
                    System.out.printf("Lollipop: %s has been added.%n", task.toString());

                } else if (command.startsWith("deadline")) {
                    String[] splitCommand = command.split(" ", 2);
                    if (splitCommand.length == 1) {
                        throw new DukeException("Please fill in a description for deadline.");
                    }

                    String description = splitCommand[1];
                    String[] splitDescription = description.split(" /by ");
                    if (splitDescription.length == 1) {
                        throw new DukeException("Please add in /by, following by a dateline.");
                    }

                    description = splitDescription[0];
                    String deadline = splitDescription[1];
                    Task task = new Deadline(description, deadline);
                    taskList.add(task);
                    System.out.printf("Lollipop: %s has been added.%n", task.toString());

                } else if (command.startsWith(("event"))) {
                    String[] splitCommand = command.split(" ", 2);
                    if (splitCommand.length == 1) {
                        throw new DukeException("Please fill in a description for event.");
                    }

                    String description = splitCommand[1];
                    String[] splitDescription = description.split(" /at ");
                    if (splitDescription.length == 1) {
                        throw new DukeException("Please add in /at, followed by the event's time.");
                    }

                    description = splitDescription[0];
                    String time = splitDescription[1];
                    Task task = new Event(description, time);
                    taskList.add(task);
                    System.out.printf("Lollipop: %s has been added.%n", task.toString());

                } else {
                    throw new DukeException("I do not understand what that means :(");
                }
            } catch (DukeException e) {
                System.out.printf("Lollipop: %s%n", e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Lollipop: No such task number found.");
            } catch (NumberFormatException e) {
                System.out.println("Lollipop: Please input a number.");
            }

            System.out.println("");
            System.out.print("You: ");
            command = scanner.nextLine();
        }

        // Goodbye message
        System.out.println("Lollipop: See you again soon!");
    }
}
