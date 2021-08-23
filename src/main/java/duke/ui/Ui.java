package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;
/**
 * Contains the logic to display the UI for the user to see.
 */

public class Ui {
    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Displays the welcome text for Duke.
     * @param taskList to output the list of tasks Duke currently has at start
     */
    public void showWelcome(TaskList taskList) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        System.out.println("These are the current tasks I have:");
        listView(taskList.getAllTasks());

    }

    /**
     * Reads a command from the user.
     * @return a string command of the user input
     */
    public String readCommand() {
        String inputCommand = in.nextLine();
        return inputCommand;
    }

    /**
     * Displays the bye text for Duke.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error text based on the error message.
     * @param errorMsg error message to be displayed
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Displays a text when the user adds a task.
     */
    public void addMessage() {
        System.out.println("Got it. I've added this task:\n");
    }

    /**
     * Displays the task's toString() method with a new line at the end.
     * @param task task to be displayed
     */
    public void showTask(Task task) {
        System.out.println(task.toString() + "\n");
    }

    /**
     * Displays the number of tasks that Duke currently has.
     * @param lst the list of tasks needed to output the number of items in it
     */
    public void showListLength(TaskList lst) {
        System.out.println("Now you have " + lst.size() + " tasks in the list\n");
    }

    /**
     * Displays a message when a task has been removed.
     */
    public void deletedMsg() {
        System.out.println("Noted. I've removed this task:\n");
    }

    /**
     * Displays a list view of the tasks that Duke has.
     * @param lst the list of tasks to be displayed
     */
    public void listView(ArrayList<Task> lst) {
        String res = "";
        int counter = 1;
        for (Task tsk : lst) {
            res += counter + ". " + tsk.toString() + System.lineSeparator();
            counter++;
        }
        System.out.println("Here are the tasks in your list:\n" + res);
    }

    /**
     * Displays a message when a task is marked done.
     * @param tsk the task that was done
     */
    public void doneTask(Task tsk) {
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(tsk.toString());
    }

    /**
     * Displays a message if the file had error loading.
     */
    public void showLoadingError() {
        System.out.println("error loading!");
    }
}
