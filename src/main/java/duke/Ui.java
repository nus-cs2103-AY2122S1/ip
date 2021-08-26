package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Ui class that encapsulates the interface which the user interacts with.
 */
public class Ui {

    // initialize Scanner object
    private Scanner scan = new Scanner(System.in);

    /**
     * Prints a standard formatted message.
     *
     * @param message Message to be printed in console.
     */
    public void display(String message) {
        System.out.println("    * * * * * * * * * * * * * * * * * * * *");
        System.out.println("    " + message);
        System.out.println("    * * * * * * * * * * * * * * * * * * * *\n");
    }

    /**
     * Prints greeting message.
     */
    public void greet() {
        display("Hi, I'm Sync-Me Sebby.\n    " +
                "I'm here to assist you with tracking and synchronizing of your personal tasks.\n    " +
                "Let me know how I can help?");
    }

    /**
     * Reads command given by user in the console.
     *
     * @return A string of the command inputted.
     */
    public String readCommand() {
        // read user input
        return this.scan.nextLine();
    }

    /**
     * Prints exit message.
     */
    public void exit() {
        this.display("Goodbye. See you again soon!");
        this.scan.close();
    }

    /**
     * Prints success message for adding task.
     *
     * @param task The individual task which can be Todo, Deadline or Event.
     * @param numTasks The number of tasks in the list.
     */
    public void displaySuccessMessage(Task task, int numTasks) {
        this.display("Got it. I've added this task: \n" + "      " + task + "\n    Now you have "
                + numTasks + (numTasks <= 1 ? " task" : " tasks") + " in the list.");
    }


}
