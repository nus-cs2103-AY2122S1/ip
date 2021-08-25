import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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
                // Convert string command to enum value
                Command command = Command.valueOf(input.split(" ")[0].toUpperCase());
                switch (command) {
                case BYE:
                    // Exit chat bot
                    System.out.printf(FORMAT, LINE);
                    System.out.printf(FORMAT, "Goodbye. Have a nice day!");
                    System.out.printf(FORMAT, LINE);
                    return; // To terminate function
                case LIST:
                    runListCommand(tasks);
                    break;
                case DONE:
                    runDoneCommand(input, tasks);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    runAddTaskCommand(input, command, tasks);
                    break;
                case DELETE:
                    runDeleteCommand(input, tasks);
                    break;
                default:
                    throw new DukeException("You have entered an invalid command.");
                }
            } catch (IllegalArgumentException e) {
                // When invalid command is given, it is unable to be parsed into the enum
                System.out.printf(FORMAT, LINE);
                System.out.printf("\tUh-oh! %s\n", "You have entered an invalid command.");
                System.out.printf(FORMAT, LINE);
            } catch (Exception e) {
                System.out.printf(FORMAT, LINE);
                System.out.printf("\tUh-oh! %s\n", e.getMessage());
                System.out.printf(FORMAT, LINE);
            }
        }
    }

    // Helper function to separate a string into pieces (e.g. input into taskName and dateTime)
    private static String[] splitWith(String input, int startIndex, String regex, String errorMessage)
            throws DukeException {
        if (startIndex >= input.length() || !input.contains(regex)) {
            throw new DukeException(errorMessage);
        }
        return input.substring(startIndex).split(regex);
    }

    // Helper function to parse a date from an input
    private static LocalDate parseDateFromInput(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date must be of the form YYYY-MM-DD, and must be a real/valid date.");
        }
    }

    // Helper function to parse a time from and input
    private static LocalTime parseTimeFromInput(String timeString) throws DukeException {
        try {
            return LocalTime.parse(timeString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Time must be of the form HH:MM. (HH from 00-23, MM from 00-59)");
        }
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
    private static void runAddTaskCommand(String input, Command command, List<Task> tasks) throws DukeException {
        Task task;
        switch (command) {
        case TODO:
            // Add Todo task
            if (input.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            task = new Todo(input.substring(5));
            break;
        case DEADLINE:
            String errorMessage = "Command must be in the format: [taskName] /by [date(YYYY-MM-DD)] [time(HH:MM)]";
            String[] splitInput = splitWith(input, 9, " /by ", errorMessage);
            String taskName = splitInput[0];
            errorMessage = "Date and time must be in the format: YYYY-MM-DD HH:MM";
            String[] dateTime = splitWith(splitInput[1], 0, " ", errorMessage);
            String date = dateTime[0];
            String time = dateTime[1];
            task = new Deadline(taskName, parseDateFromInput(date), parseTimeFromInput(time));
            break;
        default: // default is guaranteed to be event task due to use of enum + outer control flow
            // Add Event task
            errorMessage = "Command must be in the format: [taskName] /at [date]";
            splitInput = splitWith(input, 6, " /at ", errorMessage);
            taskName = splitInput[0];
            date = splitInput[1];
            task = new Event(taskName, date);
            break;
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
