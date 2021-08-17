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
            } else if (userInput.indexOf("todo ") == 0) {
                Todo newTodo = new Todo(userInput.substring(5));
                listData.add(newTodo);

                sendMessage("Got it. I've added this task:\n  " + newTodo + "\nNow you have " + listData.size() + " tasks in the list.");
            } else if (userInput.indexOf("deadline ") == 0) {
                Deadline newDeadline = new Deadline(userInput.substring(9, userInput.indexOf(" /by ")), userInput.substring(userInput.indexOf("/by ") + 4));
                listData.add(newDeadline);

                sendMessage("Got it. I've added this task:\n  " + newDeadline + "\nNow you have " + listData.size() + " tasks in the list.");
            } else if (userInput.indexOf("event ") == 0) {
                Event newEvent = new Event(userInput.substring(6, userInput.indexOf(" /at")), userInput.substring(userInput.indexOf("/at ") + 4));
                listData.add(newEvent);

                sendMessage("Got it. I've added this task:\n  " + newEvent + "\nNow you have " + listData.size() + " tasks in the list.");
            } else {
                // ECHO command
                sendMessage(userInput);
            }
        }
    }
}
