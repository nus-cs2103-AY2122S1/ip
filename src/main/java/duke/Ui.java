package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

/**
 * Manages the user interface and contains all user outputs to print during user interaction.
 */
public class Ui {
    private Scanner s;

    public Ui() {
        this.s = new Scanner(System.in);
        printIntro();
    }

    /**
     * Prints logo of DUKE and short introduction.
     */
    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello FROM\n" + logo);
    }

    /**
     * Reads commands from text file line by line.
     *
     * @return Command from text file.
     */
    public String readCommand() {
        return s.nextLine().trim();
    }

    /**
     * Prints cue for user to start user input.
     */
    public void start() {
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________________");
    }

    /**
     * Prints simple farewell message to user.
     */
    public void end() {
        System.out.println("    ______________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ______________________________________");
        s.close();
    }

    /**
     * Prints error message accordingly based on what error is thrown.
     *
     * @param err DukeException that is being thrown.
     */
    public void printError(DukeException err) {
        System.out.println("    ______________________________________");
        System.out.printf("     %s\n", err);
        System.out.println("    ______________________________________");
    }

    /**
     * Prints simple message to indicate the task that has been added and how many items user has in the list.
     *
     * @param task Task that is currently being added into the list.
     * @param size Size of current list including current Task being added.
     */
    public void printTaskAdded(Task task, int size) {
        System.out.println("    ______________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n",task);
        System.out.printf("     Now you have %d tasks in the list\n", size);
        System.out.println("    ______________________________________");
    }

    /**
     * Prints simple message to indicate that a certain task has been marked as completed.
     *
     * @param task Task that user has indicated as completed.
     */
    public void printTaskCompleted(Task task) {
        System.out.println("    ______________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.printf("       %s\n", task);
        System.out.println("    ______________________________________");
    }

    /**
     * Prints simple message to indicate that a certain task has been deleted and how many tasks remain.
     *
     * @param task Task that the user wants deleted.
     * @param size Size of list with remaining tasks.
     */
    public void printDeleteTask(Task task, int size) {
        System.out.println("    ______________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.printf("       %s\n", task);
        System.out.printf("     Now you have %d tasks in the list.\n", size);
        System.out.println("    ______________________________________");
    }

}
