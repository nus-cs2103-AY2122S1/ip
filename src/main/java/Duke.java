import java.util.Locale;
import java.util.Scanner;

public class Duke {



    /**
     * Simple formatting tool to be used when printing commands
     * @param text The text to be printed.
     */
    public static void printMsg(String text) {
        String bar = "===============================================";
        System.out.printf("%s\n%s\n%s\n", bar, text, bar);
    }


    /**
     * Method that listens and scans for user input.
     * Program will exit when the command "bye" is given to the scanner.
     */
    public static void listen() {
        Scanner scanner = new Scanner(System.in);
        Duke.printMsg("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String text = scanner.nextLine().toLowerCase();
            if (text.equals("bye")) {
                Duke.printMsg("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            }
            else {
                Duke.printMsg(text);
            }
        }
    }

}
