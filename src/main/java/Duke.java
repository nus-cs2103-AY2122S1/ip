import utils.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Command Keywords.
     */
    private static final String listString = "list";
    private static final String exitString = "bye";
    private static final String doneString = "done";
    private static final String todoString = "todo";
    private static final String deadlineString = "deadline";
    private static final String eventString = "event";
    private static final String deadlineDelimiter = " /by ";
    private static final String eventDelimiter = " /at ";

    /**
     * Templated Messages.
     */
    private static final String exitMessage = "Bye. Hope to see you again soon!";
    private static final String introMessage = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String doneMessage = "Nice! I've marked this task as done:\n";
    private static final String addedMessage = "Got it. I've added this task:\n";
    private static final String tasksStatusMessage = "Now you have %d task(s) in the list";

    /**
     * Error Messages.
     */
    private static final String unrecognizedCommand = "Oops! I'm sorry but I don't know what that means.";
    private static final String doneOutsideOfList = "Oops! You do not have such a task!";
    private static final String todoNoDescError = "Oops! The description of a todo cannot be empty";
    private static final String deadlineNoDescError = "Oops! The description of a deadline cannot be empty";
    private static final String eventNoDescError = "Oops! The description of an event cannot be empty";
    private static final String deadlineNoDateError = "Oops! The date of a deadline cannot be empty";
    private static final String eventNoDateError = "Oops! The date of an event cannot be empty";

    /**
     * Declares the ArrayList to hold the list of tasks.
     */
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        // Introduces the program.
        System.out.println(formatMessage(introMessage));

        // Creates a new Scanner object.
        Scanner scanner = new Scanner(System.in);

        // Continuously grabbing the user input and reacting to it.
        // Loop only ends when userInput is the exitString.
        while (true) {

            // Grabs the user input.
            String userInput = scanner.nextLine();

            // Checks if userInput is equal to the exitString,
            // if so then print the exitMessage and exit the program, else react accordingly.
            if (userInput.equals(exitString)) {
                System.out.println(formatMessage(exitMessage));
                break;
            }

            // Reacts to userInput.
            System.out.println(formatMessage(react(userInput)));
        }
    }

    /**
     * Takes in the user input and reacts accordingly to specification.
     * @param userInput the user input string.
     * @return string of reaction.
     */
    private static String react(String userInput) {

        // Checks if input is "<listString>"
        if (userInput.equals(listString)) {
            return getTaskListString();
        }

        // Splits the userInput by a space.
        String[] split = userInput.split(" ", 2);

        // Checks if the format of the userInput is of "<doneString> <int>".
        if (split.length == 2 && split[0].equals(doneString) && isInteger(split[1])) {
            int index = Integer.parseInt(split[1]) - 1;
            if (index < tasks.size()) {
                Task task = tasks.get(index);
                task.markAsDone();
                return doneMessage + task.toString();
            }
            return doneOutsideOfList;
        }

        // Ensure format of string "<command> <string>"
        // React according to user inputs.
        switch (split[0]) {
            case (todoString):
                if (split.length == 1 || split[1].equals("")) {
                    return todoNoDescError;
                }
                Todo todo = new Todo(split[1]);
                tasks.add(todo);
                return formatAddTaskMessage(todo);
            case (deadlineString):
                if (split.length == 1 || split[1].equals("")) {
                    return deadlineNoDescError;
                }
                String[] deadlineSplit = split[1].split(deadlineDelimiter, 2);
                if (deadlineSplit.length == 1 || deadlineSplit[1].equals("")) {
                    return deadlineNoDateError;
                }
                Deadline deadline = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                tasks.add(deadline);
                return formatAddTaskMessage(deadline);
            case (eventString):
                if (split.length == 1 || split[1].equals("")) {
                    return eventNoDescError;
                }
                String[] eventSplit = split[1].split(eventDelimiter, 2);
                if (eventSplit.length == 1 || eventSplit[1].equals("")) {
                    return eventNoDateError;
                }
                Event event = new Event(eventSplit[0], eventSplit[1]);
                tasks.add(event);
                return formatAddTaskMessage(event);
            default:
                return unrecognizedCommand;
        }
    }

    /**
     * Utility function to format the add task message.
     * @return the formatted add task message.
     */
    private static String formatAddTaskMessage(Task task) {
        return addedMessage + task.toString() + "\n" + String.format(tasksStatusMessage, tasks.size());
    }

    /**
     * Utility function to test whether input string is an integer.
     * @param input input String.
     * @return returns true if input string can be parsed as an integer, else false.
     */
    private static Boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method to get the string of the tasks list.
     * @return string with the list of tasks.
     */
    private static String getTaskListString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i+1).append(". ").append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Formats string before printing.
     * @param message string to be printed.
     */
    private static String formatMessage(String message) {
        return  "_____________________________________\n" +
                message +                            "\n" +
                "_____________________________________\n";
    }
}
