import java.util.Scanner;

public class Duke {
    /**
     * Print with 4 spaces infront of param str.
     *
     * @param str A String to be printed
     */
    public static void printWithTabIndent(String str) {
        System.out.println(String.format("    %s", str));
    }

    /**
     * Print horizontal line.
     */
    public static void printLine() {
        printWithTabIndent("------------------------------------------");
    }

    /**
     * Pretty print the message with the horizontal lines and the param message.
     *
     * @param message A String to be printed
     */
    public static void printMessage(String message) {
        printLine();
        printWithTabIndent(message);
        printLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int count = -1;

        printMessage("Hello! I'm Duke\n    What can I do for you?");
        String message = scanner.nextLine();
        while (!message.equals("bye")) {
            if (message.equals("list") && count == -1) {
                printMessage("Nothing in the list!");
            } else if (message.equals("list")) {
                printLine();
                for (int i = 0; i <= count; i++) {
                    printWithTabIndent(String.format("%d. %s", i + 1, tasks[i]));
                }
                printLine();
            } else {
                printMessage("added: " + message);
                count++;
                tasks[count] = message;
            }
            message = scanner.nextLine();
        }
        printMessage("Bye. Hope to see you again soon!");
    }
}
