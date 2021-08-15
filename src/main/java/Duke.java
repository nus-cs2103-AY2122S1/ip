import java.util.Scanner;

/**
 * The project aims to build a product named Duke,
 * a Personal Assistant Chatbot that helps a person
 * to keep track of various things.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

/**
 * This is the Main class that will contain the main method
 * to be executed at run-time.
 */

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    private static final String BYE = "bye";

    private static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Hello! I'm Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void echo(String command) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + command);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void bye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * This is Main method.
     *
     * @param args an array of command-line arguments for the application
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        greet();
        String command = scanner.nextLine();
        while (true) {
            if (command.equals(BYE)) {
                bye();
                break;
            } else {
                echo(command);
            }
            command = scanner.nextLine();
        }
    }
}
