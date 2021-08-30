package duke;
import java.util.Scanner;

/**
 * Class that handles the interaction with the user
 * by displaying the information and receiving user inputs.
 */
public class Ui {
    private static final Scanner sc = new Scanner(System.in);
    private static final String GREETING_MESSAGE = 
    "Hello! I'm Duke" + "\n" + "What can I do for you?";

    /**
     * Instantiats a new UI class
     */
    public Ui() {}

    /**
     * Prints out the welcome message when user first uses the bot
     */
    protected void showWelcome() {
        System.out.println(GREETING_MESSAGE);
    }

    /**
     * Prints out that reading from storage of past stored tasks have failed.
     */
    protected void showLoadingError() {
        System.out.println("Error in loading task in local disk!");
    }

    /**
     * Prints segmentation line 
     */
    protected void showLine() {
        System.out.println("________________________________");
    }

    /**
     * Returns user input as string to be 
     * used in the duke bot for processing
     * 
     * @return String of the user input
     */
    protected String readCommand() {
        String userInput = sc.nextLine();
        return userInput;
    }

    /**
     * Prints the exit message when user exits the bot
     * and closes the scanner classes.
     */
    public void displayByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Prints the task that is marked completed by user.
     * 
     * @param completedMessage String of task that is completed
     */
    public void displayCompletedMessage(String completedMessage) {
        System.out.println("Nice! I've marked this task as done:" + 
        "\n" + completedMessage);
    }

    /**
     * Prints the task that is going to be deleted by user.
     * 
     * @param deleteMessage String of task to be deleted
     * @param taskLength Integer of length of taskList ArrayList
     */
    public void displayDeleteMessage(String deleteMessage, int taskLength) {
        System.out.println("Noted. I've removed this task" +
        "\n" + "Now you have " + taskLength + " tasks in the list.");
    }

    /**
     * Prints the task that user has just specified to be stored in TaskList
     * 
     * @param displayMessage String of task to be added to TaskList
     * @param taskLength Integer of length of taskList ArrayList
     */
    public void displayTaskInstructions(String displayMessage, int taskLength) {
        System.out.println("Got it. I've added this task:" + 
        "\n" + displayMessage + "\n" + "Now you have " + taskLength + 
        " tasks in the list.");
    }

    /**
     * Prints out all the tasks stored by user in chronological order.
     * 
     * @param listMessage String of the all the tasks stored in taskList
     */
    public void displayTaskList(String listMessage) {
        System.out.println("Here are the tasks in your list:" + 
        "\n" + listMessage);
    }
}
