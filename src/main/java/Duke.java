import java.util.Scanner;

public class Duke {

    // Array for storing user inputs
    private static String[] store = new String[100];
    private static int count = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner((System.in));

        say("Hello, I'm Duke.", "Make me do something.");

        while (true) {
            String userInput = scanner.nextLine().strip();

            if (userInput.equals("bye")) {
                // Check if user is attempting to exit.
                say("Bye bye, see you next time.");
                break;
            } else if (userInput.equals("list")) {
                // Check if user is requesting to print list.
                list();
                continue;
            }

            // Add input to list and inform user.
            store[count] = userInput;
            count++;
            say("added: " + userInput);
        }
    }

    /**
     * Makes duke speak.
     *
     * Prints a list of lines with indentation.
     * @param lines list of lines for Duke
     */
    static void say(String... lines) {
        System.out.println("    ____________________________________________________________");
        for (String line : lines) {
            System.out.println("    " + line);
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the list of inputs that Duke has stored.
     */
    static void list() {

        if (count == 0) {
            // Inform user if nothing has been stored.
            say("The list is empty!");
            return;
        }

        String[] listItems = new String[count];

        for (int i = 0; i < count; ++i) {
            listItems[i] = String.format("%d. %s", i + 1, store[i]);
        }

        say(listItems);
    }
}
