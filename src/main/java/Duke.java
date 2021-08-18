import java.util.Scanner;

public class Duke {

    // Constants
    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    // Commands
    private static final String EXIT_COMMAND = "exit";

    // Message functions
    public static void horizontal_line() {
        System.out.print("____________________________________________________________\n");
    }

    public static void display_message(String message) {
        horizontal_line();
        System.out.println(message);
        horizontal_line();
    }

    public static void main(String[] args) {
        System.out.println(LOGO);
        display_message(WELCOME_MESSAGE);

        Scanner sc = new Scanner(System.in);
        for (String input = sc.nextLine(); !input.equalsIgnoreCase(EXIT_COMMAND); input = sc.nextLine()) {
            display_message(input);
        }
        sc.close();

        display_message(EXIT_MESSAGE);
    }
}
