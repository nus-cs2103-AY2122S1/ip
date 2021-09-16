package duke.tools;

/**
 * Deals with interactions with the user.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Ui {

    private static final String DIVIDER = "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Method to get a message divider.
     */
    public void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Method to print the bot's welcome message.
     */
    public void showWelcomeMessage() {
        String welcome = "Hello! I'm Duke. A friendly chatbot!! :)\n"
                + "What can I do for you?\n";
        System.out.println(DIVIDER + "\n" + LOGO + "\n" + welcome + DIVIDER);
    }

    public static String getWelcomeMessage() {
        String welcome = "Hello! I'm Duke. A friendly chatbot!! :)\n"
                + "What can I do for you?\n";
        return (welcome);
    }

    public void showGoodbyeMessage() {
        String end_message = "\n Bye. I hope to talk to you again soon! :)";
        System.out.println(end_message + "\n" + DIVIDER);
    }

    /**
     * Method to read user input.
     */
    public void readCommand() {
        //String prompt_message = "Add to-do list ({input})/ View list (list) / Complete task (done {input}) / End (bye) :";
        String prompt_message = "Please input your command: ";
        System.out.println(prompt_message);
    }

    public String testReadCommand() {
        return "Please input your command: ";
    }

    public static void printMessage(String message) {
        System.out.println(DIVIDER + "\t" + message);
    }
}