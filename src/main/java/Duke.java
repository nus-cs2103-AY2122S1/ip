/**
 * A personal assistant chatbot that helps a person to keep track of various things.
 *
 * @author Jovyn Tan
 * @version CS2103 AY21/22 Sem 1
 */
public class Duke extends Chatbot {
    private static String GREETING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static String FAREWELL_MESSAGE = "See you soon! :)";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.greet();
        duke.echo();
    }

    /**
     * Prints a greeting to the user.
     */
    public void greet() {
        Chatbot.printMessage(GREETING_MESSAGE);
    }

    /**
     * Echoes the user's input, until the user says "bye".
     */
    public void echo() {
        String message = Chatbot.acceptUserInput();
        if (message.equals("bye")) {
            Chatbot.printMessage(FAREWELL_MESSAGE);
        } else {
            Chatbot.printMessage(message);
            echo();
        }
    }
}
