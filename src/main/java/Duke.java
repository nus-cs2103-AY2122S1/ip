import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    /**
     * Sends a message to the user in the specified format
     * @param message The message to be sent to the user
     */
    protected static void sendMessage(String message) {
        System.out.println("    ____________________________________________________________\n    " +
                message.replace("\n", "\n    ") +
                "\n    ____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> listData = new ArrayList<>();

        final String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        sendMessage(welcomeMessage);

        String userInput;
        while (true) {
            userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                // EXIT command
                sendMessage("Bye. Hope to see you again soon!");
                System.exit(0);

            } else if (userInput.equals("list")) {
                // LIST command
                // Construct the string containing the list of items that have been stored in preparation to send to user
                StringBuilder listMessage = new StringBuilder("Here are the tasks in your list:");

                // Add all elements in the list
                for (int i = 0; i < listData.size(); ++i) {
                    listMessage.append("\n").append(i + 1).append(". ").append(listData.get(i));
                }

                sendMessage(listMessage.toString());
            } else if (userInput.indexOf("done ") == 0) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                listData.get(index).markAsDone();

                sendMessage("Nice! I've marked this task as done:\n  " + listData.get(index));
            } else {
                // ADD command
                sendMessage("added: " + userInput);
                listData.add(new Task(userInput));
            }
        }
    }
}
