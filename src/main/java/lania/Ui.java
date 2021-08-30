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
     */
    public void showUpdateMessage(TaskList tasks, Task task) {
        System.out.println("Lania has added: ");
        System.out.println(task);
        System.out.println("Great! Now you have "
                + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in your list.");
    }

    /**
     * Displays a greeting message.
     */
    public void showGreetingMessage() {
        System.out.println("Hello I am Lania! How may Lania be of assistance?");
        System.out.println("Enter 'bye' to exit");
    }

    /**
     * Displays an exit message.
     */
    public void showExitMessage() {
        System.out.println("Bye. Lania looks forward to seeing you again!");
    }

    /**
     * Message that displays all tasks by the user.
     *
     * @param tasks The user's list of tasks.
     */
    public void showListMessage(TaskList tasks) {
        System.out.println("You have the following task(s):");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
    }

    /**
     * Message that shows after the user completes a task.
     *
     * @param tasks The user's list of tasks.
     * @param i The index of the task the user has completed.
     */
    public void showCompleteMessage(TaskList tasks, int i) {
        System.out.println("Good job! Lania has marked this task as done:");
        System.out.println(tasks.get(i));
    }

    /**
     * Message that shows after the user deletes a task.
     *
     * @param tasks taskList Contains the user's task list.
     * @param task Index of the task the user has deleted.
     */
    public void showRemoveMessage(TaskList tasks, Task task) {
        System.out.println("Ok, Lania has removed this task:");
        System.out.println(task);
        System.out.println("Great! Now you have "
                + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in your list.");
    }

    public void showDateTimeException() {
        System.out.println("Lania does not understand this date/time format. Please try again");
    }

    public void showLaniaException(LaniaException e) {
        System.out.println(e);
    }

    public void showError() {
        System.out.println("Lania encountered an error while loading the file.");
    }
}
