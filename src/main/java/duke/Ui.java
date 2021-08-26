package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Handles all User Interface of the program.
 */
public class Ui {

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {

    }

    /**
     * Display the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Display a prompt asking user for an action.
     */
    public void showLoopWelcome() {
        System.out.println("What else can I do for you?");
    }

    /**
     * Reads input by user and returns what user have typed.
     *
     * @return A String written by user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        try {
            input = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return input.trim();
    }

    /**
     * Prints out an error message.
     *
     * @param msg The error message that is to be printed.
     */
    public void printErrorMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Display loading error message.
     */
    public void showLoadingError() {
        System.out.println("Initialisation Error! We will override with a fresh state.");
    }

    /**
     * Display goodbye message.
     */
    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Display task removed message.
     *
     * @param taskToBeDeleted The Task that is to be deleted.
     * @param totalTask The remaining number of task.
     */
    public void taskRemovedMessage(Task taskToBeDeleted, int totalTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + taskToBeDeleted.toString());
        System.out.println("Now you have " + totalTask + " task in the list.");
    }

    /**
     * Display task added message.
     *
     * @param task The Task that is to be Added.
     * @param totalTask The new total number of task.
     */
    public void taskAddedMessage(Task task, int totalTask) {
        System.out.println("Got it. I've added this task.");
        System.out.println(task);
        System.out.println("Now you have " + totalTask + " tasks in the list.");
    }
}
