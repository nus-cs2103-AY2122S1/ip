import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Chng Zi Hao
 */
public class Duke {
    // Constants for the program
    static final String DIVIDER = "--------------------------------------------------------------------------------";
    static final String PROMPT = "Enter Command: ";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(DIVIDER);
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
        System.out.print(PROMPT);

        String str = sc.nextLine();
        while (!str.equals("bye")) {
            printResponse(str);
            str = sc.nextLine();
        }

        format("Bye. Hope to see you again soon!");

    }

    /**
     * Formats the input and prints it in a formatted version.
     *
     * @param input Input to be printed.
     */
    public static void format(String input) {
        System.out.println(DIVIDER);
        System.out.println(input);
        System.out.println(DIVIDER);
    }

    /**
     * Prints the formatted response from given user input.
     *
     * @param input User's input.
     */
    public static void printResponse(String input) {
        format(input);
        System.out.print(PROMPT);
    }
}
