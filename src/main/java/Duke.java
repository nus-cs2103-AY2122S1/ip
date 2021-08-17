import java.util.Locale;
import java.util.Scanner;

public class Duke {
    /**
     * Sends a message to the user in the specified format
     * @param message The message to be sent to the user
     */
    public static void sendMessage(String message) {
        System.out.println("    ____________________________________________________________\n" +
                "    " + message.replace("\n", "\n    ") +
                "\n    ____________________________________________________________");
    }

    /**
     * Returns a String containing the input command by the user
     * @param sc Scanner object to receive user input
     * @return The given command. Currently, takes the entire line as a single string (command) and in lowercase
     */
    public static String getCommand(Scanner sc) {
        return sc.nextLine().toLowerCase();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        sendMessage(welcomeMessage);

        String userCommand = "";
        while (true) {
            userCommand = getCommand(sc);

            if (userCommand.equals("bye")) {
                // EXIT command
                sendMessage("Bye. Hope to see you again soon!");
                System.exit(0);
            } else {
                // ECHO command
                sendMessage(userCommand);
            }
        }
    }
}
