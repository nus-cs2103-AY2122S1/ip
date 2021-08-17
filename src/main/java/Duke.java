import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    /**
     * Sends a message to the user in the specified format
     * @param message The message to be sent to the user
     */
    public static void sendMessage(String message) {
        System.out.println("    ____________________________________________________________\n    " +
                message.replace("\n", "\n    ") +
                "\n    ____________________________________________________________");
    }

    /**
     * Returns a String containing the input command by the user
     * @param sc Scanner object to receive user input
     * @return The given command. Currently, takes the entire line as a single string (command) wiuthout parsing
     */
    public static String getCommand(Scanner sc) {
        return sc.nextLine();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> listData = new ArrayList<>();

        final String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        sendMessage(welcomeMessage);

        String userCommand = "";
        while (true) {
            userCommand = getCommand(sc);

            if (userCommand.equals("bye")) {
                // EXIT command
                sendMessage("Bye. Hope to see you again soon!");
                System.exit(0);

            } else if (userCommand.equals("list")) {
                // LIST command
                // Construct the string containing the list of items that have been stored in preparation to send to user
                StringBuilder listMessage = new StringBuilder();

                // Add first element (if it exists)
                if (!listData.isEmpty()) {
                    listMessage.append("1. ").append(listData.get(0));
                }

                // Add remaining elements (if any)
                for (int i = 1; i < listData.size(); ++i) {
                    listMessage.append("\n").append(i + 1).append(". ").append(listData.get(i));
                }

                sendMessage(listMessage.toString());
            } else {
                // ADD command
                sendMessage("added: " + userCommand);
                listData.add(userCommand);
            }
        }
    }
}
