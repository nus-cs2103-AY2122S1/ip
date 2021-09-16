package katheryne;

import java.util.Scanner;

/**
 * Deals with user interactions.
 */
public class Ui {
    public boolean isRunning;
    private final Scanner scanner = new Scanner(System.in);

    public Ui() {
        this.isRunning = true;
    }

    /**
     * Displays a greeting message when Katheryne is initialised.
     *
     * @param lst
     */
    void greet(TaskList lst) {
        System.out.println("Ad astra abyssosque! " 
                + "I am Katheryne, the receptionist here at the Adventurers' Guild.");
        System.out.println("How may I assist?");
        if (!lst.isEmpty()) {
            System.out.println("I still have your list of tasks from last time. " +
                            "The number of tasks you have is " + lst.getSize() + ".");
        }
    }

    /**
     * Read command typed by the user and return it as a string
     */
    String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints out the given task list, item by item.
     *
     * @param lst
     */
    public void listTasks(TaskList lst) {
        this.say("Here's the list I've stored for you:");
        for (int i = 1; i <= lst.getSize(); i++) {
            this.say(i + ") " + lst.getTask(i - 1));
        }
    }

    /**
     * Prints out a statement which states the number of tasks in the list
     * 
     * @param lst
     */
    public void countTasksInList(TaskList lst) {
        String statement = lst.getSize() == 1
                ? "There is currently 1 item in your list."
                : "There are currently " + lst.getSize() + " items in your list.";
        this.say(statement);
    }

    /**
     * Marks the Ui as not running, and displays an exit message.
     */
    public void stopRunning() {
        this.say("Ad Astra Abyssoque, Traveller!");
        this.isRunning = false;
    }

    public void say(String dialogue) {
        System.out.println(dialogue);
    }

    /**
     * Shows the error message of the exception to the user
     *
     * @param e
     */
    void showErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
