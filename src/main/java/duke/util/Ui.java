package duke.util;

import duke.task.Task;

import java.util.Scanner;

/**
 * Task representing the user interface of the program.
 */
public class Ui {
    /** Scanner used to get input from the user. */
    Scanner scanner;

    /**
     * Constructor of Ui class.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows a loading error.
     */
    public void showLoadingError() {
        System.out.println("Sorry. We could not load your save file. We are created a new save file.");
    }

    /**
     * Shows the welcome text.
     */
    public void showWelcome() {
        System.out.println("Hello, I am Duke. What can I do for you?");
    }

    /**
     * Retrieves a command from the user.
     *
     * @return An array containing the command as the first item and all subsequent words are the second item.
     */
    public String[] readCommand() {
        return new String[] {scanner.next(), scanner.nextLine().trim()};
    }

    /**
     * Shows the error given an error message.
     *
     * @param errMessage Error message.
     */
    public void showError(String errMessage) {
        System.out.println("Ooops! " + errMessage);
        System.out.println("Please try again.");
    }

    /**
     * Shows that the user has successfully added a new task.
     *
     * @param task Task that has been added.
     * @param tasks Tasklist which the task has been added to.
     */
    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.printf(
                "Got it. I've added this task: \n   %s \nNow you have %d task in the list.%n",
                task, tasks.size());
    }

    /**
     * Shows that the user has successfully removed a task.
     *
     * @param task Task that has been removed
     * @param tasks Tasklist which the task has been added to.
     */
    public void showTaskRemoved(Task task, TaskList tasks) {
        System.out.printf("Noted. I've removed this task:\n  %s\nNow you have %d task in the list.%n",
                task, tasks.size());
    }

    /**
     * Shows that the user has successfully marked a task as completed.
     *
     * @param task Task that has been completed.
     */
    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Shows a user friendly text version of the tasks in the tasklist.
     */
    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }

    /**
     * Shows a goodbye message.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again!");
    }
}
