package duke;

import java.util.Scanner;

/**
 * Class handling interactions with the users
 */
public class Ui {
    private Scanner s = new Scanner(System.in);

    /**
     * Retrieve the next command from the user and executes it.
     */
    public String nextLine() {
        return s.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcome() {
        System.out.println("Hello! I'm \nWhat can I do for you?");
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    /**
     * Prints the message for successful load.
     */
    public void printLoadSuccess() {
        System.out.println("Save file successfully loaded");
    }

    /**
     * Prints the list of task.
     *
     * @param tasks TaskList to be printed.
     */
    public void printList(TaskList tasks) {
        System.out.println("---------");
        System.out.println(tasks.toString());
        System.out.println("---------");
    }


    /**
     * Prints the error of a given DukeException.
     *
     * @param err Exception to be printed.
     */
    public void printDukeException(DukeException err) {
        System.out.println("---------");
        System.out.println("Error: " + err.getMessage());
        System.out.println("---------");
    }


    /**
     * Print message when adding a new task.
     * @param tasks TaskList of the program.
     * @throws DukeException When TaskList cannot retreive the last element in the list.
     */
    public void printNewTask(TaskList tasks) throws DukeException {
        System.out.println("---------");
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.count() - 1).toString());
        System.out.println("Now you have " + tasks.count() + " task in the list");
        System.out.println("---------");
    }

    /**
     * Prints message when a task is marked as completed.
     * @param task Task that was marked complete.
     */
    public void printDoneTask(Task task) {
        System.out.println("---------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("---------");
    }

    /**
     * Prints message when a task is deleted.
     * @param size Current size of the TaskList.
     */
    public void printDeleteTask(int size) {
        System.out.println("---------");
        System.out.println("Noted. I've removed this task");
        System.out.println("Now you have " + size + " task in the list");
        System.out.println("---------");
    }

    /**
     * Closes the scanner when the program is done.
     */
    public void closeScanner() {
        s.close();
    }
}
