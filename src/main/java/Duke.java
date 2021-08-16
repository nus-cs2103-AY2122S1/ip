import java.util.Scanner;

public class Duke {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        greet();
        add();

        // close scanner
        scanner.close();
    }

    /**
     * Print a message that is enclosed by 2 horizontal lines.
     *
     * @param message The message to be printed between 2 horizontal lines.
     */
    private static void printMessage(String message) {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine + "\n" + message + "\n" + horizontalLine);
    }

    /**
     * Greeting message of Duke.
     */
    static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printMessage(logo + "\nWelcome! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Echos commands entered by the user, and exits when the user types bye.
     */
    static void echo() {
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            printMessage(command);
            command = scanner.nextLine();
        }
        printMessage("Bye. See you next time!");
    }

    /**
     * Add and store text entered and display them back when requested.
     */
    static void add() {
        String command = scanner.nextLine();
        String[] list = new String[100];
        int index = 0;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                printMessage(listToString(list));
            } else {
                list[index] = command;
                index++;
                printMessage("added: " + command);
            }
            command = scanner.nextLine();
        }
        printMessage("Bye. See you next time!");
    }

    /**
     * Convert a list of text to a message string.
     * @param list The list of text stored.
     * @return The string of messages.
     */
    private static String listToString(String[] list) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) break;
            result.append(i+1).append(". ").append(list[i]);
            if (list[i+1] != null) result.append("\n");
        }
        return result.toString();
    }
}
