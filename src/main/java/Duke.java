import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String FORMAT = "\t%s\n";
    private static final String LINE = "______________________________________________________";
    
    public static void main(String[] args) {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        List<Task> tasks = new ArrayList<>();

        System.out.print(logo);
        System.out.printf(FORMAT, LINE);
        System.out.printf(FORMAT, "Hello there, I'm Duke!");
        System.out.printf(FORMAT, "What can I do for you today?");
        System.out.printf(FORMAT, LINE);

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.nextLine().trim();
                String command = input.split(" ")[0];
                if (input.equals("bye")) {
                    // Exit chat bot
                    System.out.printf(FORMAT, LINE);
                    System.out.printf(FORMAT, "Goodbye. Have a nice day!");
                    System.out.printf(FORMAT, LINE);
                    break;
                } else if (input.equals("list")) {
                    // List all added tasks
                    runListCommand(tasks);
                } else if (command.equals("done")) {
                    // Set a task as done
                    runDoneCommand(input, tasks);
                } else if (command.equals("todo")
                        || command.equals("deadline")
                        || command.equals("event")) {
                    // Add a Todo, Deadline or Event task
                    runAddTaskCommand(input, command, tasks);
                } else if (command.equals("delete")) {
                    runDeleteCommand(input, tasks);
                } else {
                    // Invalid command
                    throw new DukeException("You have entered an invalid command.");
                }
            } catch (Exception e) {
                System.out.printf(FORMAT, LINE);
                System.out.printf("\tUh-oh! %s\n", e.getMessage());
                System.out.printf(FORMAT, LINE);
            }
        }
    }

    // Helper function to separate a string into taskName and date
    private static String[] splitWith(String input, int startIndex, String regex) throws DukeException {
        if (startIndex >= input.length() || !input.contains(regex)) {
            throw new DukeException("Command must be in the format: [taskName]" + regex + "[date]");
        }
        return input.substring(startIndex).split(regex);
    }

    // Abstraction to make main function neater
    private static void runDoneCommand(String input, List<Task> tasks) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("Please type in a task number to mark as done.");
        }
        String taskNumberString = input.substring(5);
        System.out.printf(FORMAT, LINE);
        if (taskNumberString.matches("\\d+")
                && (Integer.parseInt(taskNumberString) - 1 < tasks.size()
                && Integer.parseInt(taskNumberString) - 1 >= 0)) {
            int taskIndex = Integer.parseInt(taskNumberString) - 1;
            Task doneTask = tasks.get(taskIndex);
            doneTask.markAsDone();
            System.out.printf(FORMAT, "Good work! This task is now marked as done:");
            System.out.printf("\t\t%s\n", doneTask.toString());
        } else {
            // Invalid input (not a number or invalid number)
            throw new DukeException("Please type in a valid task number to mark as done.");
        }
        System.out.printf(FORMAT, LINE);
    }

    // Abstraction to make main function neater
    private static void runListCommand(List<Task> tasks) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("You do not have any tasks currently.");
        }
        System.out.printf(FORMAT, LINE);
        System.out.printf(FORMAT, "Here is your task list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d.%s\n", i + 1, tasks.get(i));
        }
        System.out.printf(FORMAT, LINE);
    }

    // Abstraction to make main function neater
    private static void runAddTaskCommand(String input, String command, List<Task> tasks) throws DukeException {
        Task task;
        if (command.equals("todo")) {
            // Add Todo task
            if (input.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            task = new Todo(input.substring(5));
        } else if (command.equals("deadline")) {
            // Add Deadline task
            String[] splitInput = splitWith(input, 9, " /by ");
            String taskName = splitInput[0];
            String date = splitInput[1];
            task = new Deadline(taskName, date);
        } else {
            // Add Event task
            String[] splitInput = splitWith(input, 6, " /at ");
            String taskName = splitInput[0];
            String date = splitInput[1];
            task = new Event(taskName, date);
        }

        // Common functionality: add task to list, print task and list size
        tasks.add(task);
        System.out.printf(FORMAT, LINE);
        System.out.printf(FORMAT, "Got it. The following task has been added: ");
        System.out.printf("\t\t%s\n", task.toString());
        System.out.printf("\tNow you have %d task%s in the list.\n",
                tasks.size(), tasks.size() == 1 ? "" : "s");
        System.out.printf(FORMAT, LINE);
    }

    // Abstraction to make main function neater
    private static void runDeleteCommand(String input, List<Task> tasks) throws DukeException {
        if (input.length() <= 7) {
            throw new DukeException("Please type in a task number to delete.");
        }
        String taskNumberString = input.substring(7);
        System.out.printf(FORMAT, LINE);
        if (taskNumberString.matches("\\d+")
                && (Integer.parseInt(taskNumberString) - 1 < tasks.size()
                && Integer.parseInt(taskNumberString) - 1 >= 0)) {
            int taskIndex = Integer.parseInt(taskNumberString) - 1;
            Task removedTask = tasks.remove(taskIndex);
            System.out.printf(FORMAT, "Got it. The following task has been removed:");
            System.out.printf("\t\t%s\n", removedTask.toString());
            System.out.printf("\tNow you have %d task%s in the list.\n",
                    tasks.size(), tasks.size() == 1 ? "" : "s");
        } else {
            // Invalid input (not a number or invalid number)
            throw new DukeException("Please type in a valid task number to delete.");
        }
        System.out.printf(FORMAT, LINE);
    }
}
