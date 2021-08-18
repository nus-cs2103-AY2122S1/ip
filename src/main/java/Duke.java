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
            try {
                userInput = sc.nextLine().trim();

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
                } else if (userInput.startsWith("done")) {
                    int index = Integer.parseInt(userInput.substring(5)) - 1;
                    listData.get(index).markAsDone();

                    sendMessage("Nice! I've marked this task as done:\n  " + listData.get(index));
                } else if (userInput.startsWith("todo")) {
                    // If no arguments provided
                    if (userInput.length() == 4) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    } else if (!userInput.startsWith("todo ")) {
                        // If it does not start with "todo " after trimming, it is an invalid word
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }

                    Todo newTodo = new Todo(userInput.substring(5).trim());
                    listData.add(newTodo);

                    sendMessage("Got it. I've added this task:\n  " + newTodo + "\nNow you have " + listData.size() + " tasks in the list.");
                } else if (userInput.startsWith("deadline")) {
                    // If no arguments provided
                    if (userInput.length() == 8) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    } else if (!userInput.startsWith("deadline ")) {
                        // If it does not start with "deadline " after trimming, it is an invalid word
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    } else if (!userInput.contains("/by ")) {
                        throw new DukeException(("A deadline must contain a deadline indicated after the /by command"));
                    }

                    String description = userInput.substring(9, userInput.indexOf("/by")).trim();
                    String deadlineDate = userInput.substring(userInput.indexOf("/by")).trim(); // Inclusive of the /by command
                    if (description.length() == 0) {
                        throw new DukeException("The description of a deadline cannot be empty");
                    }

                    Deadline newDeadline = new Deadline(description, deadlineDate.substring(4));
                    listData.add(newDeadline);

                    sendMessage("Got it. I've added this task:\n  " + newDeadline + "\nNow you have " + listData.size() + " tasks in the list.");
                } else if (userInput.startsWith("event")) {
                    // If no arguments provided
                    if (userInput.length() == 5) {
                        throw new DukeException("The description of a event cannot be empty.");
                    } else if (!userInput.startsWith("event ")) {
                        // If it does not start with "deadline " after trimming, it is an invalid word
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    } else if (!userInput.contains("/at ")) {
                        throw new DukeException(("An event must contain a datetime indicated after the /at command"));
                    }

                    String description = userInput.substring(5, userInput.indexOf("/at")).trim();
                    String datetime = userInput.substring(userInput.indexOf("/at")).trim(); // Inclusive of the /by command
                    if (description.length() == 0) {
                        throw new DukeException("The description of an event cannot be empty");
                    }

                    Event newEvent = new Event(description, datetime.substring(4));
                    listData.add(newEvent);

                    sendMessage("Got it. I've added this task:\n  " + newEvent + "\nNow you have " + listData.size() + " tasks in the list.");
                } else {
                    // Unknown command
                    throw new DukeException(("I'm sorry, but I don't know what that means :-("));
                }
            } catch (Exception e) {
                sendMessage(e.getMessage());
            }
        }
    }
}
