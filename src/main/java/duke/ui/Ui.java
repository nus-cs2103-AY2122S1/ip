package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Class that deals with interactions with the user.
 */
public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Default welcome message printed when user starts the program.
     */
    public void welcomeMessage() {
        lineGenerator();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        lineGenerator();
    }

    public void lineGenerator() {
        System.out.println("____________________________________________________________\n");
    }

    public void enterCommand() {
        System.out.print("Enter command: ");
    }

    public String readInput() {
        return sc.nextLine();
    }

    public String printError(Exception e) {
        return e.getMessage();
    }

    public String taskListMessage() {
        return "Here are the tasks in your list: ";
    }

    /**
     * Prints a standard message signalling task is marked as done.
     *
     * @param t The task to be marked as done.
     */
    public String taskDone(Task t) {
        return "Nice! I've marked this task as done: \n  " + t + '\n';
    }

    public String printTaskLength(TaskList tasks) {
        return "Now you have " + tasks.numberOfTasks() + " tasks in the list.\n";
    }

    /**
     * Prints a standard message signalling task is added.
     *
     * @param t The task to be added.
     */
    public String addTaskMessage(Task t) {
        return "Got it. I've added this task: \n  " + t + '\n';
    }

    public String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a standard message signalling task is deleted.
     *
     * @param t The task to be deleted.
     */
    public String deleteTask(Task t) {
        return "Noted! I've removed this task: \n  " + t + '\n';
    }

    public static String showLoadingError() {
        return "No past saved data!\n";
    }

    public String matchTaskMessage() {
        return "Here are the matching tasks in your list: \n";
    }
}
