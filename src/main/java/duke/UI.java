package duke;

/**
 * A class that prints messages to users
 *
 */
public class UI {
    /**
     * Constructor of class. Prints welcome message when the program is first run.
     */
    public UI() {
        printWelcomeMessage();
    }

    /**
     * A method which displays welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello!! This is Duke <3" + "\n" + "What can I do for you?");
    }

    /**
     * A method which display an error message when the program throws an error.
     *
     * @param message Error message to be sent to users.
     */
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * A method which displays goodbye message.
     */
    public void printByeMessage() {
        System.out.println("Babai! See you again soon! XD");
    }

}
