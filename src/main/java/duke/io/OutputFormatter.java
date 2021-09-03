package duke.io;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.data.TaskList;

/**
 * UI class that handles all output to the console for the program (No input).
 */
public class OutputFormatter {
    private OutputFormatter() {}

    public static String getWelcomeMessage() {
        return "Hello! I'm duke.\nWhat can I do for you?";
    }

    /**
     * Sends a message to the user in the specified format.
     *
     * @param message The message to be sent to the user
     */
    public static String formatMessage(String message) {
        return "    ____________________________________________________________\n    "
                + message.replace("\n", "\n    ")
                + "\n    ____________________________________________________________";
    }

    /**
     * Sends a specialised message for in the case of an error in loading.
     */
    public static String getLoadingErrorMessage() {
        return "Error! Cannot load tasks from save file!";
    }

    /**
     * Prints a given list of tasks.
     *
     * @param taskList An object containing a list of tasks to be formatted and displayed to the user
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
     * Prints a given list of tasks that are due on the given date.
     *
     * @param taskList An object containing a list of tasks to be formatted and displayed to the user.
     * @param date The date and time of the tasks we want to retrieve
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
