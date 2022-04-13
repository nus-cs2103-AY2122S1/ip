package duke.io;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.data.TaskList;

/**
 * OutputFormatter class that formats strings for output to the user
 */
public class OutputFormatter {
    private OutputFormatter() {}

    /**
     * Returns a pre-defined welcome message
     *
     * @return A fixed pre-defined welcome message
     */
    public static String getWelcomeMessage() {
        return "Hello! I'm duke.\nWhat can I do for you?";
    }

    /**
     * Returns a message to the user in a specified format (Deprecated).
     *
     * @param message The message to be sent to the user
     * @return A formatted string containing the message to be sent to the user
     */
    public static String formatMessage(String message) {
        return "    ____________________________________________________________\n    "
                + message.replace("\n", "\n    ")
                + "\n    ____________________________________________________________";
    }

    /**
     * Returns a string containing a message for in the case of an error in loading.
     *
     * @return A string containing an error message for unable to load tasks from save file
     */
    public static String getLoadingErrorMessage() {
        return "Error! Cannot load tasks from save file!";
    }

    /**
     * Returns a string containing the given list of tasks.
     *
     * @param taskList An object containing a list of tasks to be formatted and displayed to the user
     * @return A string containing the list of given tasks
     */
    public static String formatTaskList(TaskList taskList) {
        // Construct the string containing the list of items that have been stored in
        // preparation to send to user
        StringBuilder listMessage = new StringBuilder("Here are the tasks in your list:");

        // Add all elements in the list
        for (int i = 0; i < taskList.size(); ++i) {
            listMessage.append("\n").append(i + 1).append(". ").append(taskList.get(i));
        }

        return listMessage.toString();
    }

    /**
     * Returns a string containing the given list of tasks.
     *
     * @param taskList An object containing a list of tasks to be formatted and displayed to the user
     * @param date The date of the tasks we want to display to the user
     * @return A string containing the list of given tasks
     */
    public static String formatTaskList(TaskList taskList, LocalDate date) {
        // Construct the string containing the list of items that have been stored in
        // preparation to send to user
        StringBuilder listMessage = new StringBuilder("Here are the tasks in your list that are due on ")
                .append(date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))).append(":");

        // Add all elements in the list
        for (int i = 0; i < taskList.size(); ++i) {
            listMessage.append("\n").append(i + 1).append(". ").append(taskList.get(i));
        }

        return listMessage.toString();
    }
}
