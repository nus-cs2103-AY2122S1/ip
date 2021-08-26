package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    /** Line divider for beautification of command line interface */
    private static final String H_LINE = "------------------------------\n";

    /** Scanner to be used for taking in the user's commands */
    private Scanner commandInput;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        
    }

    /**
     * Prints a welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String helloMsg = H_LINE
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + H_LINE;
        System.out.print(logo + helloMsg);
    }
    
    public void beginListen() {
        commandInput = new Scanner(System.in);
    }

    /**
     * Returns the next line of user input from the command line interface.
     *
     * @return Next command.
     */
    public String readCommand() {
        return commandInput.nextLine();
    }

    /**
     * Prints out the string representation of the list of tasks.
     *
     * @param tasks ArrayList containing all the tasks.
     */
    public void showList(ArrayList<Task> tasks) {
        System.out.print(H_LINE);
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + tasks.get(i));
            }
        } else {
            System.out.println("There are no tasks in your list.");
        }
        System.out.print(H_LINE);
    }

    public void showFindList(ArrayList<Task> tasks) {
        System.out.print(H_LINE);
        if (tasks.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + tasks.get(i));
            }
        } else {
            System.out.println("There are no matching tasks in your list.");
        }
        System.out.print(H_LINE);
    }
    
    /**
     * Prints a message indicating that a user command has failed to be understood by the bot.
     */
    public void showCommandFail() {
        System.out.print(H_LINE + "I didn't get that. Please try again.\n" + H_LINE);
    }
    
    public void showError(String message) {
        System.out.print(H_LINE + "Oops... Something's wrong.\n" + message + "\n" + H_LINE);
    }
    
    public void showMessage(String message) {
        System.out.print(H_LINE + message + "\n" + H_LINE);
    }
    
    public void showStorageError() {
        showError("File");
    }
    
    public void stopListen() {
        commandInput.close();
    }
    
    public void showGoodbye() {
        System.out.print(H_LINE + "Bye. Hope to see you again soon!\n" + H_LINE);
    }
}
