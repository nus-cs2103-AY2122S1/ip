package duke.main;

import duke.tasks.Task;

import java.util.Scanner;

/**
 * Represents a class that handles all Ui responsibility
 */
public class Ui {
    private Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Shows users that there are no saved txt file
     */
    public void showLoadingError() {
        System.out.println("Saved task not found, starting as new!");
    }

    /**
     * Shows errors
     * @param error Errors to be shown
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Shows welcome screen
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("───────────────────────────");
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Shows confirmation after task is added
     * @param task Task added
     */
    public void showAddTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
    }

    /**
     * Shows task list
     * @param taskList Task list to be shown
     */
    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList);
    }

    /**
     * Shows confirmation that a task has been deleted
     * @param task Deleted task
     */
    public void showDeleteTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    public void showDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showNumTask(int numTasks) {
        if (numTasks == 1 || numTasks == 0) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }
    }
}
