package duke;

/**
 * Class that handles the interaction with the user
 * by displaying the information and receiving user inputs.
 */
public class Ui {
    private static final String GREETING_MESSAGE = "Hello! I'm Duke"
            + "\n"
            + "What can I do for you?";

    /**
     * Instantiates a new UI class.
     */
    public Ui() { }

    /**
     * Prints out the welcome message when user first uses the bot.
     */
    protected String showWelcomeMsg() {
        return GREETING_MESSAGE;
    }

    /**
     * Prints out that reading from storage of past stored tasks have failed.
     */
    protected void showLoadingError() {
        System.out.println("Error in loading task in local disk!");
    }

    /**
     * Prints the exit message when user exits the bot
     * and closes the scanner classes.
     */
    public String displayByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the task that is marked completed by user.
     *
     * @param completedMessage String of task that is completed
     */
    public String displayCompletedMessage(String completedMessage) {
        return "Nice! I've marked this task as done:"
                + "\n"
                + completedMessage;
    }

    /**
     * Prints the task that is going to be deleted by user.
     *
     * @param deleteMessage String of task to be deleted
     * @param taskLength Integer of length of taskList ArrayList
     */
    public String displayDeleteMessage(String deleteMessage, int taskLength) {
        return "Noted. I've removed this task"
                + "\n"
                + "Now you have "
                + taskLength
                + " tasks in the list.";
    }

    /**
     * Prints the task that user has just specified to be stored in TaskList
     *
     * @param displayMessage String of task to be added to TaskList
     * @param taskLength Integer of length of taskList ArrayList
     */
    public String displayTaskInstructions(
            String displayMessage,
            int taskLength) {
        return "Got it. I've added this task:"
                + "\n"
                + displayMessage
                + "\n"
                + "Now you have "
                + taskLength
                + " tasks in the list.";
    }

    /**
     * Prints out all the tasks stored by user in chronological order.
     * 
     * @param listMessage String of the all the tasks stored in taskList
     */
    public String displayTaskList(String listMessage) {
        return "Here are the tasks in your list:"
                + "\n"
                + listMessage;
    }

    public String displayFoundTasks(String tasksFound) {
        return "Here are the matching tasks in your list:"
                + "\n"
                + tasksFound;
    }

    public String displayErrorMessage(String errorMessage) {
        return errorMessage;
    }
}
