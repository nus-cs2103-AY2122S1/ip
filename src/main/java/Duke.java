
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;

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


    public static void printTaskList(List<Task> taskData) {
        // Construct the string containing the list of items that have been stored in preparation to send to user
        StringBuilder listMessage = new StringBuilder("Here are the tasks in your list:");

        // Add all elements in the list
        for (int i = 0; i < taskData.size(); ++i) {
            listMessage.append("\n").append(i + 1).append(". ").append(taskData.get(i));
        }

        sendMessage(listMessage.toString());
    }

    public static void printTaskList(List<Task> taskData, LocalDate date) {
        // Construct the string containing the list of items that have been stored in preparation to send to user
        StringBuilder listMessage = new StringBuilder("Here are the tasks in your list that are due on ")
                .append(date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))).append(":");

        // Add all elements in the list
        for (int i = 0; i < taskData.size(); ++i) {
            listMessage.append("\n").append(i + 1).append(". ").append(taskData.get(i));
        }

        sendMessage(listMessage.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> taskData = new ArrayList<>();

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
                    printTaskList(taskData);
                } else if (userInput.startsWith("done")) {
                    int index = Integer.parseInt(userInput.substring(5)) - 1;
                    taskData.get(index).markAsDone();

                    sendMessage("Nice! I've marked this task as done:\n  " + taskData.get(index));
                } else if (userInput.startsWith("delete")) {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    Task removedTask = taskData.remove(index);

                    sendMessage("Noted. I've removed this task:\n  " + removedTask +
                            "\nNow you have " + taskData.size() + " tasks in the list.");
                } else if (userInput.startsWith("todo")) {
                    // If no arguments provided
                    if (userInput.length() == 4) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    } else if (!userInput.startsWith("todo ")) {
                        // If it does not start with "todo " after trimming, it is an invalid word
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }

                    Todo newTodo = new Todo(userInput.substring(5).trim());
                    taskData.add(newTodo);

                    sendMessage("Got it. I've added this task:\n  " + newTodo + "\nNow you have " + taskData.size() + " tasks in the list.");
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
                    taskData.add(newDeadline);

                    sendMessage("Got it. I've added this task:\n  " + newDeadline + "\nNow you have " + taskData.size() + " tasks in the list.");
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
                    taskData.add(newEvent);

                    sendMessage("Got it. I've added this task:\n  " + newEvent + "\nNow you have " + taskData.size() + " tasks in the list.");
                } else if (userInput.startsWith("date")) {
                    if (userInput.length() == 4) {
                        throw new DukeException("A date must be provided to find events and deadlines occurring on that day");
                    } else if (!userInput.startsWith("date ")) {
                        // If it does not start with "date " after trimming, it is an invalid command
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    LocalDate queryDate = LocalDate.parse(userInput.substring(5));

                    List<Task> dueTasks = taskData.stream().filter(x -> x.hasDueDate(queryDate)).collect(Collectors.toList());

                    printTaskList(dueTasks, queryDate);
                } else {
                    // Unknown command
                    throw new DukeException(("I'm sorry, but I don't know what that means :-("));
                }

            } catch (DateTimeParseException e) {
                    sendMessage("Unknown date format. Please input a valid date in the format: YYYY-MM-DD");
            } catch (Exception e) {
                    sendMessage(e.getMessage());
            }
        }
    }
}
