package duke;

import java.util.Scanner;

/**
 * Encapsulates the user interface (reading user input and printing strings to the console).
 *
 * @author Quan Teng Foong
 */
public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints out the error message.
     *
     * @param errorMessage the message to print
     */
    public void showError(String errorMessage) {
        reply(errorMessage + "\n");
    }


    /**
     * Prints a basic greeting message.
     */
    public void greet() {
        System.out.println("Hello from Duke! What can I do for you?\n");
    }


    /**
     * Reads user input.
     *
     * @return the string input by the user
     */
    public String readCommand() {
        System.out.print("You: ");
        return sc.nextLine();
    }

    private void reply(String reply) {
        System.out.println("Duke: " + reply);
    }

    /**
     * Prints the goodbye message.
     */
    public void goodbye() {
        reply("Nice talking to you, goodbye!");
    }

    /**
     * Prints the user's current todo list.
     *
     * @param taskList the current TaskList object
     */
    public void showList(TaskList taskList) {
        if (taskList.toString().equals("")) {
            reply("Your todo list is empty!\n");
        } else {
            reply("Checking your todo list...");
            System.out.println(taskList);
        }
    }

    /**
     * Prints all Tasks that match the user's search term.
     *
     * @param taskList a TaskList of Tasks that match the user's search term
     */
    public void showSearchResults(TaskList taskList) {
        reply("Here are your search results:");
        System.out.println(taskList);
    }

    /**
     * Shows success message for adding of a task.
     *
     * @param taskList the current TaskList object
     */
    public void showAddedTask(TaskList taskList) {
        reply("Alright, I've added the following task:");
        System.out.println("      " + taskList.get(taskList.size() - 1) + "\n      Now you have " + taskList.size()
                + " tasks in the list.\n");
    }

    /**
     * Print a message to tell the user that the task has been deleted.
     *
     * @param taskList the current TaskList object
     */
    public void showDeleteTask(TaskList taskList) {
        reply("Task removed. You now have " + taskList.size() + " tasks remaining.");
    }
}
