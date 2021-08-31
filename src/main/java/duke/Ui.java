package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user interface that interacts with the user for text.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Ui {

    /**
     * The logo of Duke chatBot.
     */
    private static final String DUKE_LOGO = "\t\t____        _        \n" +
            "\t\t|  _ \\ _   _| | _____ \n" +
            "\t\t| | | | | | | |/ / _ \\\n" +
            "\t\t| |_| | |_| |   <  __/\n" +
            "\t\t|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Formats the reply in a bubble.
     * @param reply reply message that is to be formatted.
     * @return formatted reply that is sent to the user.
     */
    private static String formatDukeMessage(String reply) {
        return "\t____________________________________________________________\n" +
                reply +
                "\t____________________________________________________________\n";
    }

    /**
     * Prints the opening message when Duke chatBot first boots up.
     */
    public static void printOpeningMessage() {
        System.out.println(Ui.formatDukeMessage(Ui.DUKE_LOGO + "\n" +
                        "\tHELLO! I'm Duke\n" +
                        "\tTo ease your experience, here are some commands you can type: \n" +
                        "\t\t 'list': view all tasks in your task list\n" +
                        "\t\t 'todo': add a todo task in your task list\n" +
                        "\t\t 'deadline': add a deadline task in your task list\n" +
                        "\t\t 'event': add an event task in your task list\n" +
                        "\t\t 'delete': delete a task from your task list\n" +
                        "\t\t 'bye': exit chat\n" +
                        "\tWhat can I do for you?\n"

                )
        );
    }

    /**
     * Prints the closing message when Duke chatBot is closed.
     */
    public static void printClosingMessage() {
        System.out.println(Ui.formatDukeMessage("\tBye. Hope to see you again soon!\n"));
    }

    /**
     * Prints the error messages when Duke chatBot catches an exception.
     * @param e exception that is thrown.
     */
    public static void printErrorMessage(Exception e) {
        System.err.println(Ui.formatDukeMessage(e.getMessage() + "\n"));
    }

    /**
     * Prints the reply from Duke chatBot to the user.
     * @param message message that is formatted and sent to the user.
     */
    public static void printReply(String message) {
        System.out.println(Ui.formatDukeMessage(message));
    }

    /**
     * Prints the success message once task is added.
     * @param task task to be added.
     * @param total total number of tasks in the task list.
     */
    public static void printAddedTaskMessage(Task task, String total) {
        System.out.println(Ui.formatDukeMessage("\tGot it. I've added this task:\n" +
                "\t\t" + task + "\n" +
                "\tNow you have " + total + " in your list.\n"));
    }

    /**
     * Prints the success message when task is deleted.
     * @param task task to be deleted.
     * @param total total number of tasks in the task list.
     */
    public static void printDeleteTaskMessage(Task task, String total) {
        System.out.println(Ui.formatDukeMessage("\tNoted. I've removed this task:\n" +
                "\t\t" + task + "\n" +
                "\tNow you have " + total + " in your list.\n"));
    }

    /**
     * Prints the success message when task is marked as done.
     * @param task task to be marked as done.
     */
    public static void printDoneMessage(Task task) {
        System.out.println(Ui.formatDukeMessage("\tNice! I've marked this task as done:\n" +
                "\t\t" + task + "\n"));
    }

    public static void printFindMessage(TaskList tasks) {
        System.out.println(Ui.formatDukeMessage("\tHere are the matching tasks in your list:\n" +
                tasks.toString()));
    }

}
