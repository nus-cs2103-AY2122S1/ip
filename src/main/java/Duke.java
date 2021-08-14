import java.util.Scanner;

/**
 * This Duke class implements the functionalities of a chatbot,
 * helping a person to keep track of various things.
 *
 * @author Yeo Jun Wei
 * @version Level-1
 */
public class Duke {
    /** Horizontal line for formatting */
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /** Greeting message to be displayed when bot starts running */
    private static final String GREETING_MESSAGE = "Hello! I'm JWBot \nWhat can I do for you?";

    /** Goodbye message to be displayed when bot stops running */
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        greetUser();
        listenToCommands();
    }

    /**
     * Displays the formatted greeting message to the user.
     */
    private static void greetUser() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GREETING_MESSAGE);
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    /**
     * Displays the formatted goodbye message to the user.
     */
    private static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the formatted user-entered command to the user.
     *
     * @param command Command entered by the user.
     */
    private static void echoCommand(String command) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(command);
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    /**
     * Listens to the user-entered commands, and acts accordingly.
     */
    private static void listenToCommands() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        if (command.equals("bye")) {
            exit();
        } else {
            echoCommand(command);
            listenToCommands();
        }
    }
}
