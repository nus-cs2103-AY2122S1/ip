import java.util.Scanner;

public class Duke {
    private static final String LINE_SEPARATOR = "\t____________________________________________________________\n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        greet();
        while (!(input = scanner.nextLine()).equals("bye")) {
            echo(input);
        }
        exit();
    }

    /**
     * Message to be printed by Duke when Duke is first started.
     */
    private static void greet() {
        Duke.print("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Message to be printed by Duke upon exit.
     */
    private static void exit() {
        Duke.print("Bye. Hope to see you again soon!");
    }

    /**
     * Echos the input, printing it as a formatted message.
     *
     * @param input String to be echoed.
     */
    private static void echo(String input) {
        Duke.print(input);
    }

    /**
     * Prints a formatted message with line separator on top and bottom.
     *
     * @param message String to be printed.
     */
    private static void print(String message) {
        String indentedMessage = "\t " + message.replaceAll("\n", "\n\t ") + "\n";
        System.out.println(Duke.LINE_SEPARATOR + indentedMessage + Duke.LINE_SEPARATOR);
    }
}
