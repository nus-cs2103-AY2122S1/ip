package duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    /** Scanner to scan in user input */
    private Scanner sc;

    /**
     * Constructor for a UI.
     */
    public Ui() {
        sc = new Scanner(System.in);
    };

    /**
     * UI when a task has been added.
     *
     * @param taskList The TaskList.
     * @param task The Task that has been added.
     */
    public void addUi(TaskList taskList, TaskList.Task task) {
        System.out.println("Got it. I've added this task:\n" + "  " + task + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * UI when a task has been marked as done.
     *
     * @param task The Task that has been marked as done.
     */
    public void doneUi(TaskList.Task task) {
        if (task != null) {
            System.out.println("Nice! I've marked this task as done:\n  " + task);
        }
    }

    /**
     * UI when a task has been deleted.
     *
     * @param taskList The TaskList.
     * @param task The Task that has been deleted.
     */
    public void deleteUi(TaskList taskList, TaskList.Task task) {
        System.out.println("Noted. I've removed this task:\n  " + task + "\nNow you have "
                + taskList.size() + " tasks in the list.");
    }

    /**
     * UI when a command to display TaskList has been given.
     *
     * @param taskList The TaskList.
     */
    public void listUi(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("You do not have any outstanding task. Yay!");
        } else {
            System.out.println("Here are the tasks in your list:\n" + taskList.toString());
        }
    }

    /**
     * UI when a command to exit Duke has been given.
     */
    public void exitUi() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * UI when an unknown command has been given.
     */
    public void unknownUi() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void greet1() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void greet2() {
        System.out.println("Welcome back! :-)");
    }

    public void showLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Scans in the input given by user.
     *
     * @return String representation of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
