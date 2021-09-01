package duke.ui;

import duke.commands.Task;
import duke.data.TaskList;

/**
 * Encapsulates the Ui component of Duke.
 * This class deals with interactions with the user.
 *
 * @author: Jason Ng
 * @version: Duke Level-10
 */
public class Ui {
    /** The border to be printed for messages */
    private static final String BORDER = "____________________________________________________________";
    /** The introductory message to be printed for the start of the program */
    private static final String INTRO = "Hello from\n"
                                        +  " ____        _        \n"
                                        + "|  _ \\ _   _| | _____ \n"
                                        + "| | | | | | | |/ / _ \\\n"
                                        + "| |_| | |_| |   <  __/\n"
                                        + "|____/ \\__,_|_|\\_\\___|\n"
                                        + "Im Duke\nWhat can I do for you?";

    /**
     * Returns the introductory message for Duke
     *
     * @return The formatted introduction message.
     */
    public static String intro() {
        return INTRO;
    }

    /**
     * Returns the formatted input message for Duke.
     * Message is lined with the borders stored in the attribute of the class.
     *
     * @param message The message to be printed.
     * @return The formatted message.
     */
    public String formatMessage(String message) {
        return BORDER + '\n' + message + '\n' + BORDER;
    }

    /**
     * Returns the entire formatted taskList.
     *
     * @param taskList The taskList to be printed.
     * @return The formatted message after a delete command.
     */
    public String formatList(TaskList taskList) {
        String formattedList = "Here are the tasks in your list:" + '\n';
        formattedList += BORDER + '\n';
        for (int i = 1; i <= taskList.size(); i++) {
            Task thisTask = taskList.get(i - 1);
            String task = String.format("%d. %s", i, thisTask);
            formattedList += task + '\n';
        }
        formattedList += BORDER;
        return formattedList;
    }

    /**
     * Returns the formatted message after special tasks are added to the taskList.
     *
     * @param taskList The taskList which the tasks are added to.
     * @return The formatted message after a special command.
     */
    public String formatSpecialTasks(TaskList taskList) {
        String message = taskList.get(taskList.size() - 1).toString();
        int total = taskList.size();
        String newMsg = String
                .format("Got it, I've added this task:\n  %s\nNow you have a total of %d tasks in the list.",
                message, total);
        return formatMessage(newMsg);
    }

    /**
     * Returns the message after a deletion of a task.
     * The deleted task is also printed,
     * together with the number of remaining tasks in the taskList.
     *
     * @param deleted The deleted task.
     * @param taskList The taskList the task was deleted from.
     * @return The formatted message after a delete command.
     */
    public String formatDelete(Task deleted, TaskList taskList) {
        String message = deleted.toString();
        int total = taskList.size();
        String newMsg = String
                .format("Noted. I've removed this task:\n  %s\nNow you have a total of %d tasks in the list.",
                        message, total);
        return formatMessage(newMsg);
    }

    /**
     * Returns the message after a find of tasks.
     *
     * @param matchedTasks The list of matched tasks.
     * @return The formatted message after a find command.
     */
    public String formatFind(TaskList matchedTasks) {
        String toReturn = BORDER + '\n';
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 1; i <= matchedTasks.size(); i++) {
            Task thisTask = matchedTasks.get(i - 1);
            String task = String.format("%d. %s", i, thisTask);
            toReturn += task + '\n';
        }
        toReturn += BORDER;
        return toReturn;
    }
}
