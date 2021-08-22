package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is an Ui class that deals with interaction with Users.
 */
public class Ui {

    /**
     * These are class fields and constants of Ui.
     */
    private static final String LINE_SEPARATOR = "    _______________________________";
    private Scanner s = new Scanner(System.in);

    /**
     * Prints the standard welcome message user sees when initiating duke.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello I'm FullOfBugs. What can I do for you?");
    }


    /**
     * Prints the standard line separator for each message.
     */
    public void printLine() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints the error message when a file cannot be loaded.
     */
    public void showLoadingError() {
        printLine();
        System.out.println("    Error loading file!!");
        printLine();
    }

    /**
     * Prints the error message from DukeExceptions.
     *
     * @param message A String representing the message to be printed.
     */
    public void showError(String message) {
        printLine();
        System.out.printf("    %s\n", message);
        printLine();
    }


    /**
     * Prints the message when a task is added to the list.
     *
     * @param t  A Task instance that contains details of the newly added task and to be printed.
     * @param newSize An int representing the new size of the Task list.
     */
    public void printAddTask(Task t, int newSize) {
        printLine();
        System.out.println("    Got it. I've added this task:");
        System.out.printf("      %s\n", t);
        System.out.printf("    Now you have %d ", newSize);
        System.out.println((newSize <= 1 ? "task" : "tasks") + " in the list.");
        printLine();
    }

    /**
     * Prints the message when a task is removed from the list.
     *
     * @param t  A Task instance that contains the details of the removed task.
     * @param remainingSize An int representing the newly updated size of task list.
     */
    public void printRemoveTask(Task t, int remainingSize) {
        printLine();
        System.out.println("    Noted. I've removed this task:");
        System.out.printf("      %s\n", t);
        System.out.printf("    Now you have %d ", remainingSize);
        System.out.println((remainingSize <= 1 ? "task" : "tasks") + " in the list.");
        printLine();
    }

    /**
     * Prints the message when a task is marked as done.
     *
     * @param t A Task instance that contains details of the task marked as done.
     */
    public void printMarkTaskDone(Task t) {
        printLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.printf("    %s\n", t);
        printLine();
    }

    /**
     * Prints a list of all the task currently in the list.
     *
     * @param tasks A TaskList instance containing a list of task.
     */
    public void printList(ArrayList<Task> tasks) {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("    %d. %s\n", i + 1, tasks.get(i));
        }
        printLine();
    }

    /**
     * Prints the farewell message when the user says bye to Duke.
     */
    public void bidFarewell() {
        printLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printLine();
        s.close();
    }

    /**
     * Returns a string representing the command keyed in by the user.
     *
     * @return A String representing a single command.
     */
    public String readCommand() {
        String command = s.nextLine();
        return command;
    }

}
