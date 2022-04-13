package duke.tools;

/**
 * A class that encapsulates the interactions with the user.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Ui {

    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * A method to show the message divider.
     */
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * A method to show Duke's welcome message.
     */
    public void showWelcomeMessage() {
        String welcome = "Hello! I'm Duke. A friendly chatbot!! :)\n"
                + "What can I do for you?\n";
        System.out.println(DIVIDER + "\n" + LOGO + "\n" + welcome + DIVIDER);
    }

    /**
     * Returns Duke's Welcome Message for the GUI.
     * @return Duke's Welcome Message.
     */
    public static String getWelcomeMessage() {
        return "Hello! I'm Duke. A friendly chatbot!! :)\n"
                + "What can I do for you?\n";
    }

    /**
     * A method to show Duke's goodbye Message.
     */
    public void showGoodbyeMessage() {
        String end_message = "\n Bye. I hope to talk to you again soon! :)";
        System.out.println(end_message + "\n" + DIVIDER);
    }

    /**
     *  A method to show a prompt message to the user.
     */
    public void readCommand() {
        String prompt_message = "Please input your command: ";
        System.out.println(prompt_message);
    }

    /**
     * Returns a string representation of prompt message to the user.
     * @return A prompt message.
     */
    public String testReadCommand() {
        return "Please input your command: ";
    }

    /**
     * Returns a formatted message to the user.
     * @param message A fromatted message.
     */
    public static void printMessage(String message) {
        System.out.println(DIVIDER + message + "\n");
    }
}