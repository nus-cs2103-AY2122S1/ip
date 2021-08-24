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
     * @param taskList The resulting TaskList.
     * @param t Task that has been added.
     */
    public void updateMessage(TaskList taskList, Task t) {
        System.out.println("Lania has added: ");
        System.out.println(t);
        System.out.println("Great! Now you have " + taskList.size() + (taskList.size() == 1 ? " task" : " tasks") + " in your list.");
    }

    public void greetingMessage() {
        System.out.println("Hello I am Lania! How may Lania be of assistance?");
        System.out.println("Enter 'bye' to exit");
    }

    public void goodbyeMessage() {
        System.out.println("Bye. Lania looks forward to seeing you again!");
    }

    /**
     * Message that displays all tasks by the user.
     *
     * @param taskList Contains the user's task list.
     */
    public void listMessage(TaskList taskList) {
        System.out.println("You have the following task(s):");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i));
        }
    }

    /**
     * Message that shows after the user completes a task.
     *
     * @param taskList Contains the user's task list.
     * @param i Index of the task the user has completed.
     */
    public void taskCompleteMessage(TaskList taskList, int i) {
        System.out.println("Good job! Lania has marked this task as done:");
        System.out.println(taskList.get(i));
    }

    /**
     * Message that shows after the user deletes a task.
     *
     * @param taskList taskList Contains the user's task list.
     * @param t Index of the task the user has deleted.
     */
    public void removeTaskMessage(TaskList taskList, Task t) {
        System.out.println("Ok, Lania has removed this task:");
        System.out.println(t);
        System.out.println("Great! Now you have " + taskList.size() + (taskList.size() == 1 ? " task" : " tasks") + " in your list.");
    }

    public void dateTimeExceptionMessage() {
        System.out.println("Lania does not understand this data/time format. Please try again");
    }

    public void laniaExceptionMessage(LaniaException e) {
        System.out.println(e);
    }

    public void loadingErrorMessage() {
        System.out.println("Lania encountered an error while loading the file.");
    }
}
