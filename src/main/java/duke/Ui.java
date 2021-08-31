package duke;

import java.util.ArrayList;
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
     * Displays UI when a task has been added.
     *
     * @param taskList The TaskList.
     * @param task The Task that has been added.
     */
    public void displayAddUi(TaskList taskList, TaskList.Task task) {
        System.out.println("Got it. I've added this task:\n" + "  " + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Displays UI when a task has been marked as done.
     *
     * @param task The Task that has been marked as done.
     */
    public void displayDoneUi(TaskList.Task task) {
        if (task != null) {
            System.out.println("Nice! I've marked this task as done:\n  " + task);
        }
    }

    /**
     * Displays UI when a task has been deleted.
     *
     * @param taskList The TaskList.
     * @param task The Task that has been deleted.
     */
    public void displayDeleteUi(TaskList taskList, TaskList.Task task) {
        System.out.println("Noted. I've removed this task:\n  " + task + "\nNow you have "
                + taskList.size() + " tasks in the list.");
    }

    /**
     * Displays UI when a command to display TaskList has been given.
     *
     * @param taskList The TaskList.
     */
    public void displayListUi(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("You do not have any outstanding task. Yay!");
        } else {
            System.out.println("Here are the tasks in your list:\n" + taskList.toString());
        }
    }

    /**
     * Displays UI when a command to exit Duke has been given.
     */
    public void displayExitUi() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays UI when an unknown command has been given.
     */
    public void displayUnknownUi() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Displays UI when a command to find a keyword has been given.
     *
     * @param relatedList The list of related tasks found in TaskList.
     */
    public void displayFindUi(ArrayList<TaskList.Task> relatedList) {
        if (relatedList.isEmpty()) {
            System.out.println("Unfortunately, there's no task that matches your keyword :-(");
        } else {
            System.out.println("Here are the matching tasks in your list:");

            String str = "";
            int size = relatedList.size();

            for (int i = 0; i < size - 1; i++) {
                str += String.format("%d.%s\n", i + 1, relatedList.get(i));
            }
            str += String.format("%d.%s", size, relatedList.get(size - 1));

            System.out.println(str);
        }

    }

    public void greet1() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void greet2() {
        System.out.println("Welcome back! :-)");
    }

    /**
     * Displays the UI between every input and output.
     */
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
