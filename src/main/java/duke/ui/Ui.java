package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

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

    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void taskList() {
        System.out.println("Here are the tasks in your list: ");
    }

    public void taskDone(Task t) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + t);
    }

    public void printTaskLength(TaskList tasks) {
        System.out.println("Now you have " + tasks.numberOfTasks() + " tasks in the list.");
    }

    public void addTaskMessage(Task t) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + t);
    }

    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void deleteTask(Task t) {
        System.out.println("Noted! I've removed this task: ");
        System.out.println("  " + t);
    }

    public void showLoadingError() {
        System.out.println("No past saved data!");
    }
}
