/**
 * A personal assistant chatbot that helps a person to keep track of various things.
 *
 * @author Jovyn Tan
 * @version CS2103 AY21/22 Sem 1
 */
public class Duke extends Chatbot {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
    }

    /**
     * Prints a greeting to the user.
     */
    public static void greet() {
        Chatbot.printMessage("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Echoes the user's input, until the user says "bye".
     */
    public static void echo() {
        String message = Chatbot.acceptUserInput();
        if (message.equals("bye")) {
            Chatbot.printMessage("See you soon! :)");
        } else {
            Chatbot.printMessage(message);
            echo();
        }
    }
}
