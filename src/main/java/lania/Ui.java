package lania;

import lania.exception.LaniaException;
import lania.task.Task;
import lania.task.TaskList;

/**
 * This class deals with interactions with the user.
 */
public class Ui {

    /**
     * Displays a message when the user adds a task.
     *
     * @param tasks The user's TaskList.
     * @param task The task that has been added.
     * @return The message displayed to user concatenated as a string.
     */
    public String showUpdateMessage(TaskList tasks, Task task) {
        // display to user
        System.out.println("Lania has added: ");
        System.out.println(task);
        System.out.println("Great! Now you have "
                + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in your list.");

        // concatenate the messages displayed
        String message = "Lania has added: ";
        message += "\n" + task.toString();
        message += "\n" + "Great! Now you have "
                + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in your list.";

        return message;
    }

    /**
     * Displays a greeting message.
     *
     * @return The message displayed to user concatenated as a string.
     */
    public String showGreetingMessage() {
        // display to user
        System.out.println("Hello I am Lania! How may Lania be of assistance?");
        System.out.println("Enter 'bye' to exit");

        // concatenate the messages displayed
        String message = "Hello I am Lania! How may Lania be of assistance?" + "\n" + "Enter 'bye' to exit";

        return message;
    }

    /**
     * Displays an exit message.
     *
     * @return The message displayed to user concatenated as a string.
     */
    public String showExitMessage() {
        String message = "Bye. Lania looks forward to seeing you again!";
        System.out.println(message);
        return message;
    }

    /**
     * Shows a message that displays all tasks by the user.
     *
     * @param tasks The user's list of tasks.
     * @return The message displayed to user concatenated as a string.
     */
    public String showListMessage(TaskList tasks) {
        System.out.println("You have the following task(s):");
        String message = "You have the following task(s):";

        // print and accumulate all messages
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
            message += "\n" + (i + 1) + "." + tasks.get(i).toString();
        }

        return message;
    }

    /**
     * Shows a message after the user completes a task.
     *
     * @param tasks The user's list of tasks.
     * @param i The index of the task the user has completed.
     * @return The message displayed to user concatenated as a string.
     */
    public String showCompleteMessage(TaskList tasks, int i) {
        // display to user
        System.out.println("Good job! Lania has marked this task as done:");
        System.out.println(tasks.get(i));

        // concatenate the messages displayed
        String message = "Good job! Lania has marked this task as done:";
        message += "\n" + tasks.get(i).toString();

        return message;
    }

    /**
     * Shows a message after the user undo-es a complete command.
     *
     * @param tasks The user's list of tasks.
     * @param i The index of the task the user has completed.
     * @return The message displayed to user concatenated as a string.
     */
    public String showIncompleteMessage(TaskList tasks, int i) {
        // display to user
        System.out.println("Lania has marked this task as undone:");
        System.out.println(tasks.get(i));

        // concatenate the messages displayed
        String message = "Lania has marked this task as undone:";
        message += "\n" + tasks.get(i).toString();

        return message;
    }

    /**
     * Shows a message after the user deletes a task.
     *
     * @param tasks taskList Contains the user's task list.
     * @param task The task the user has deleted.
     * @return The message displayed to user concatenated as a string.
     */
    public String showRemoveMessage(TaskList tasks, Task task) {
        // display to user
        System.out.println("Ok, Lania has removed this task:");
        System.out.println(task);
        System.out.println("Great! Now you have "
                + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in your list.");

        // concatenate the messages displayed
        String message = "Ok, Lania has removed this task:";
        message += "\n" + task.toString();
        message += "\n" + "Great! Now you have "
                + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in your list.";

        return message;
    }

    /**
     * Shows a message that indicates wrong date/time format.
     *
     * @return The message displayed to user as a string.
     */
    public String showDateTimeException() {
        String message = "Lania does not understand this date/time format. Please try again";
        System.out.println(message);
        return message;
    }

    /**
     * Shows a message that indicates invalid command.
     *
     * @param e The LaniaException to display.
     * @return The message displayed to user as a string.
     */
    public String showLaniaException(LaniaException e) {
        System.out.println(e.toString());
        return e.toString();
    }

    /**
     * Shows a message when user tries to access an index
     * ouf of bounds of the list of tasks
     *
     * @param index The index of the task to access.
     * @return The Message displayed to user as a string.
     */
    public String showUnavailableTaskMessage(int index) {
        return "There is no task available at: " + index;
    }

    /**
     * Shows a message that indicates error loading the file.
     *
     * @return The message displayed to user as a string.
     */
    public String showError() {
        String message = "Lania encountered an error while loading the file.";
        System.out.println(message);
        return message;
    }
}
