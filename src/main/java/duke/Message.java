package duke;

import duke.task.Task;

/**
 * Represents messages that are printed out by Duke
 * in response to various user inputs.
 *
 * @author botr99
 */
public class Message {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static String formatDukeResponse(String response) {
        return HORIZONTAL_LINE + "\n" + response + "\n" + HORIZONTAL_LINE + "\n";
    }

    /**
     * Prints the welcome message when Duke is started,
     * just before user inputs his/her first command.
     */
    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = logo + "Hello! I'm Duke.\n" + "What can I do for you?";
        System.out.println(formatDukeResponse(welcomeMessage));
    }

    /**
     * Prints the exit message when user inputs "bye".
     */
    public static void printExitMessage() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(formatDukeResponse(exitMessage));
    }

    /**
     * Prints the task that was added and the updated number of tasks in the task list.
     *
     * @param task The task that was added.
     * @param taskCount The updated number of tasks in the task list.
     */
    public static void printAddTaskMessage(Task task, int taskCount) {
        System.out.println(formatDukeResponse("Got it. I've added this task:\n" + task
                + "\nNow you have " + taskCount
                + (taskCount == 1 ? " task " : " tasks ") + "in the list."));
    }

    /**
     * Prints the user's task list.
     *
     * @param tasks The user's task list.
     */
    public static void printTasksMessage(TaskList tasks) {
        System.out.println(formatDukeResponse("Here are the tasks in your list:\n"
                + tasks.toString()));
    }

    /**
     * Prints the task that was marked as done.
     *
     * @param task The task that was marked as done.
     */
    public static void printMarkTaskDoneMessage(Task task) {
        System.out.println(formatDukeResponse("Nice! I've marked this task as done:\n" + task));
    }

    /**
     * Prints the task that was deleted and the updated number of tasks in the task list.
     *
     * @param task The task that was deleted.
     * @param taskCount The updated number of tasks in the task list.
     */
    public static void printDeleteTaskMessage(Task task, int taskCount) {
        System.out.println(formatDukeResponse("Noted. I've removed this task:\n" + task
                + "\nNow you have " + taskCount
                + (taskCount == 1 ? " task " : " tasks ") + "in the list."));
    }

    /**
     * Prints an invalid command message when the user inputs an invalid command.
     */
    public static void printInvalidCommandMessage() {
        System.out.println(formatDukeResponse("Oops!!! I'm sorry, but I don't know what that means."));
    }

    /**
     * Prints the message of the DukeException.
     *
     * @param e The DukeException thrown when Duke is being run.
     */
    public static void printDukeExceptionMessage(DukeException e) {
        System.out.println(formatDukeResponse(e.getMessage()));
    }

    /**
     * Prints the try again message when there is an IOException thrown.
     */
    public static void printTryAgainMessage() {
        System.out.println(formatDukeResponse("Please try again or restart the application."));
    }

}
