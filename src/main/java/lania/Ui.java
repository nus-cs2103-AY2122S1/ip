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
     * @param tasks The resulting TaskList.
     * @param task The task that has been added.
     * @return The message displayed to user concatenated as a string.
     */
    public String showUpdateMessage(TaskList tasks, Task task) {
        String message = "Lania has added: ";
        message = message + "\n" + task.toString();
        message = message + "\n" + "Great! Now you have "
                + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in your list.";
        System.out.println("Lania has added: ");
        System.out.println(task);
        System.out.println("Great! Now you have "
                + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in your list.");
        return message;
    }

    /**
     * Displays a greeting message.
     *
     * @return The message displayed to user concatenated as a string.
     */
    public String showGreetingMessage() {
        String message = "Hello I am Lania! How may Lania be of assistance?" + "\n" + "Enter 'bye' to exit";
        System.out.println("Hello I am Lania! How may Lania be of assistance?");
        System.out.println("Enter 'bye' to exit");
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
     * Message that displays all tasks by the user.
     *
     * @param tasks The user's list of tasks.
     * @return The message displayed to user concatenated as a string.
     */
    public String showListMessage(TaskList tasks) {
        String message = "You have the following task(s):";
        System.out.println("You have the following task(s):");
        for (int i = 0; i < tasks.size(); i++) {
            message = message + "\n" + (i + 1) + "." + tasks.get(i).toString();
            System.out.println(i + 1 + "." + tasks.get(i));
        }
        return message;
    }

    /**
     * Message that shows after the user completes a task.
     *
     * @param tasks The user's list of tasks.
     * @param i The index of the task the user has completed.
     * @return The message displayed to user concatenated as a string.
     */
    public String showCompleteMessage(TaskList tasks, int i) {
        String message = "Good job! Lania has marked this task as done:";
        message = message + "\n" + tasks.get(i).toString();
        System.out.println("Good job! Lania has marked this task as done:");
        System.out.println(tasks.get(i));
        return message;
    }

    /**
     * Message that shows after the user deletes a task.
     *
     * @param tasks taskList Contains the user's task list.
     * @param task Index of the task the user has deleted.
     * @return The message displayed to user concatenated as a string.
     */
    public String showRemoveMessage(TaskList tasks, Task task) {
        String message = "Ok, Lania has removed this task:";
        message = message + "\n" + task.toString();
        message = message + "\n" + "Great! Now you have "
                + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in your list.";

        System.out.println("Ok, Lania has removed this task:");
        System.out.println(task);
        System.out.println("Great! Now you have "
                + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in your list.");
        return message;
    }

    /**
     * Message that indicates wrong date/time format.
     *
     * @return The message displayed to user as a string.
     */
    public String showDateTimeException() {
        String message = "Lania does not understand this date/time format. Please try again";
        System.out.println(message);
        return message;
    }

    /**
     * Message that indicates invalid command.
     *
     * @return The message displayed to user as a string.
     */
    public String showLaniaException(LaniaException e) {
        System.out.println(e);
        return e.getMessage();
    }

    /**
     * Message that indicates error loading the file.
     *
     * @return The message displayed to user as a string.
     */
    public String showError() {
        String message = "Lania encountered an error while loading the file.";
        System.out.println(message);
        return message;
    }
}
