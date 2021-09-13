package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the GUI that interacts with the user.
 */
public class GUI {

    /**
     * Formats the reply in a bubble.
     * @param reply reply message that is to be formatted.
     * @return formatted reply that is sent to the user.
     */
    private static String formatDukeMessage(String reply) {
        return reply;
    }

    /**
     * Sends the opening message when Duke chatBot first boots up.
     */
    public static String sendOpeningMessage() {
        return GUI.formatDukeMessage(
                "HELLO! I'm Duke\n"
                        + "To ease your experience, here are some commands you can type: \n"
                        + "\t 'list': view all tasks in your task list\n"
                        + "\t 'todo': add a todo task in your task "
                        + "list\n"
                        + "\t 'deadline': add a deadline task in your task list\n"
                        + "\t 'event': add an event task in your task list\n"
                        + "\t 'delete': delete a task from your task list\n"
                        + "\t 'find': find a task based on the given keyword\n"
                        + "\t 'sort': sort the list of tasks by their date\n"
                        + "\t 'bye': exit chat\n"
                        + "What can I do for you?\n"

        );
    }

    /**
     * Sends the closing message when Duke chatBot is closed.
     */
    public static String sendClosingMessage() {
        return GUI.formatDukeMessage("Bye!\n");
    }

    /**
     * Sends the error messages when Duke chatBot catches an exception.
     * @param e exception that is thrown.
     */
    public static String sendErrorMessage(Exception e) {
        return GUI.formatDukeMessage(e.getMessage() + "\n");

    }

    /**
     * Sends the reply from Duke chatBot to the user.
     * @param message message that is formatted and sent to the user.
     */
    public static String sendReply(String message) {
        return GUI.formatDukeMessage(message);
    }

    /**
     * Sends the success message once task is added.
     * @param task task to be added.
     * @param total total number of tasks in the task list.
     */
    public static String sendAddedTaskMessage(Task task, String total) {
        return GUI.formatDukeMessage("Got it. I've added this task:\n" +
                "\t" + task + "\n" +
                "Now you have " + total + " in your list.\n");
    }

    /**
     * Sends the success message when task is deleted.
     * @param task task to be deleted.
     * @param total total number of tasks in the task list.
     */
    public static String sendDeleteTaskMessage(Task task, String total) {
        return GUI.formatDukeMessage("Noted. I've removed this task:\n" +
                "\t" + task + "\n" +
                "Now you have " + total + " in your list.\n");
    }

    /**
     * Sends the success message when task is marked as done.
     * @param task task to be marked as done.
     */
    public static String sendDoneMessage(Task task) {
        return GUI.formatDukeMessage("Nice! I've marked this task as done:\n" +
                "\t" + task + "\n");
    }

    /**
     * Sends the success message when task is found.
     * @param tasks list of tasks saved by the Duke chatbot.
     * @return tasks that match the user keyword.
     */
    public static String sendFindMessage(TaskList tasks) {
        return GUI.formatDukeMessage("Here are the matching tasks in your list:\n" +
                tasks.toString());
    }

    /**
     * Sends the success message when task list is sorted.
     * @param tasks list of tasks saved by the Duke chatbot.
     * @return tasks that are sorted.
     */
    public static String sendSortedMessage(TaskList tasks) {
        return GUI.formatDukeMessage("Sorted! Here are the tasks:\n" +
                tasks.toString());
    }

}