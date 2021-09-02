package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Class that encapsulates the Ui in Duke.
 */
public class Ui {
    private Scanner sc;
    private String input = "";

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome statement,
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\n" + "What can I do for you?");
    }

    /**
     * Prints closing statement.
     */
    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints Ui for adding task to list.
     *
     * @param task Task to be added.
     * @param size Updated number of items on the TaskList.
     */
    public void addTaskToList(Task task, int size) {
        String taskToString = task.toString();
        System.out.println("Got it. I've added this task: \n" + taskToString
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints Ui for removing task from the list.
     *
     * @param task Task to be removed.
     * @param size Updated number of items on the TaskList.
     */
    public void removeTaskFromList(Task task, int size) {
        String taskToString = task.toString();
        System.out.println("Noted. I've removed this task: \n" +
                taskToString
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints the current TaskList.
     *
     * @param taskList The current TaskList.
     */
    public void printTaskList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("There are currently no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println((i + 1) + "." + taskList.getTask(i).toString());
            }
        }
    }

    /**
     * Reads the user input.
     *
     * @return The next line of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints DukeException errors.
     *
     * @param e Error message.
     */
    public void showError(String e) {
        System.out.println(e);
    }

    /**
     * Prints line for formatting messages.
     */
    public void showLine() {
        System.out.println("___________________");
    }

    /**
     * Returns String representation of Task status.
     *
     * @param status Boolean representation of Task status, true for done and false for undone.
     * @return String representation of Task status, "X" for done and " " for undone.
     */
    public String taskStatusIcon(boolean status) {
        return (status ? "X" : " "); // mark done task with X
    }

}
