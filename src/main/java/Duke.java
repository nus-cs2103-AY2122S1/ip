import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        String format = "\t%s\n";
        String horizontalLine = "______________________________________________________";
        List<Task> tasks = new ArrayList<>();

        System.out.print(logo);
        System.out.printf(format, horizontalLine);
        System.out.printf(format, "Hello there, I'm Duke!");
        System.out.printf(format, "What can I do for you today?");
        System.out.printf(format, horizontalLine);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String command = input.split(" ")[0];
            if (input.equals("bye")) {
                // Exit chat bot
                System.out.printf(format, horizontalLine);
                System.out.printf(format, "Goodbye. Have a nice day!");
                System.out.printf(format, horizontalLine);
                break;
            } else if (input.equals("list")) {
                // List all added tasks
                System.out.printf(format, horizontalLine);
                System.out.printf(format, "Here is your task list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("\t%d.%s\n", i + 1, tasks.get(i));
                }
                System.out.printf(format, horizontalLine);
            } else if (command.equals("done")) {
                // Set a task as done
                String taskNumberString = input.substring(5);
                System.out.printf(format, horizontalLine);
                if (taskNumberString.matches("\\d+")
                        && (Integer.parseInt(taskNumberString) - 1 < tasks.size()
                        && Integer.parseInt(taskNumberString) - 1 >= 0)) {
                    int taskIndex = Integer.parseInt(taskNumberString) - 1;
                    Task doneTask = tasks.get(taskIndex);
                    doneTask.markAsDone();
                    System.out.printf(format, "Good work! This task is now marked as done:");
                    System.out.printf("\t\t%s\n", doneTask.toString());
                } else {
                    // Invalid input (not a number or invalid number)
                    System.out.printf(format, "Please type in a valid task number.");
                }
                System.out.printf(format, horizontalLine);
            } else if (command.equals("todo")
                    || command.equals("deadline")
                    || command.equals("event")) {
                // Add a Todo, Deadline or Event task
                Task task = new Task(input); // placeholder item, this be overridden below
                if (command.equals("todo")) {
                    // Add Todo task
                    task = new Todo(input.substring(5));
                } else if (command.equals("deadline")) {
                    // Add Deadline task
                    String[] splitInput = input.substring(9).split(" /by ");
                    String taskName = splitInput[0];
                    String date = splitInput[1];
                    task = new Deadline(taskName, date);
                } else {
                    // Add Event task
                    String[] splitInput = input.substring(6).split(" /at ");
                    String taskName = splitInput[0];
                    String date = splitInput[1];
                    task = new Event(taskName, date);
                }

                // Common functionality: add task to list, print task and list size
                tasks.add(task);
                System.out.printf(format, horizontalLine);
                System.out.printf(format, "Got it. The following task has been added: ");
                System.out.printf("\t\t%s\n", task.toString());
                System.out.printf("\tNow you have %d task%s in the list.\n",
                        tasks.size(), tasks.size() == 1 ? "" : "s");
                System.out.printf(format, horizontalLine);
            } else {
                // Invalid command
                System.out.printf(format, horizontalLine);
                System.out.printf(format, "Please enter a valid command.");
                System.out.printf(format, horizontalLine);
            }
        }
    }
}
