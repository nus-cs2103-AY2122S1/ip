import java.util.Scanner;
import java.lang.String;
/**
 * The Duke class encapsulates the Duke project's chat-bot for CS2103T individual project 1.
 *
 * @author Chesterwongz
 */
public class Duke {
    /**
     * False if Duke is running. True if user has exited Duke.
     */
    private static boolean isExited = false;

    /**
     * Checks if user wants to exit Duke.
     *
     * @param input The user's input
     * @return Returns true if user wants to exit the Duke, and false otherwise.
     */
    private static boolean checkExit(String input) {
        if (input.compareToIgnoreCase("bye") == 0) {
            isExited = true;
        }
        return isExited;
    }
    /**
     * Frames the message with underscore lines.
     *
     * @param msg The String we want to frame.
     * @return The framed String.
     */
    private static void print(String msg) {
        String line = "________________________________";
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
        System.out.println();
    }

    /**
     * Prints the Duke logo.
     */
    private static void logo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        print("Hello from\n" + logo);
    }
    /**
     * Prints our greeting.
     */
    private static void greet() {
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?";
        print(greeting);
    }
    /**
     * Prints our goodbye.
     */
    private static void bye() {
        String bye = "Bye! Hope to see you again soon!";
        print(bye);
    }
    /**
     * Echos the user's input.
     */
    private static void echo(String msg) {
        print(msg);  // Output user input
    }

    public static void main(String[] args) {
        // Level 1.
        logo();
        greet();
        Scanner sc = new Scanner(System.in);
        while (!isExited) {
            // String input
            String input = sc.nextLine();
            if (checkExit(input)) {
                bye();
            } else {
                echo(input);
            }
        }
    }
}
