package duke.gui;

/**
 * A class to handle the input and output of user.
 */
public class Ui {

    private static final String GREETING = "Hello! I am "
            + "Duke, the awesome bot helper! \n"
            + "How can I help you today?";

    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    /**
     * Creates a new Ui instance with a new scanner instance
     **/
    public Ui() {
    }

    /**
     * Returns the String from the parameter.
     * This class is maintained for future use if a new reply format is needed.
     *
     * @param output The message which should be displayed in the output.
     */
    public String reply(String output) {
        return (output);
    }

    /**
     * Returns the string for greetings and goodbyes.
     *
     * @param isGreeting Boolean to indicate if it is a greeting or goodbye.
     * @return String of the greeting or goodbye.
     **/
    public String greet(Boolean isGreeting) {
        String printMessage = isGreeting
                ? GREETING
                : GOODBYE;
        return reply(printMessage);
    }

    /**
     * Return error message of an exception.
     *
     * @param e The exception which holds the message to be returned.
     **/
    public String printException(Exception e) {
        return reply(e.getMessage());
    }

}
