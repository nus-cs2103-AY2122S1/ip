import java.util.Arrays;
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
     * Add and store tasks entered and display them back with complete status when requested.
     */
    static void add() {
        String command = "";
        Task[] list = new Task[100];
        int index = 0;
        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                // end bot
                break;
            } else if (command.equals("list")) {
                // view list
                printMessage(listToString(list));
            } else {
                // split command to check for actions
                String[] actions = command.split(" ");

                switch (actions[0]) {
                    case "done":
                        try {
                            int doneIndex = Integer.parseInt(command.substring(5));
                            try {
                                list[doneIndex - 1].markAsDone();
                                printMessage("Nice! I've marked this task as done:\n\t" + list[doneIndex - 1]);
                            } catch (NullPointerException e) {
                                // Task at doneIndex does not exist
                                printMessage("Task " + doneIndex + " does not exist. Please check your task list!");
                            } catch (ArrayIndexOutOfBoundsException e) {
                                // mark done index more than 100
                                printMessage("Duke can only save up to 100 tasks ;(");
                            }
                        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                            // command done is not followed by a number
                            printMessage("Invalid input. Please check your done command format.");
                        }
                        break;
                    case "todo":
                        StringBuilder todoDescription = new StringBuilder();
                        todoDescription.append(actions[1]);
                        for (int i = 2; i < actions.length; i++) {
                            todoDescription.append(" ").append(actions[i]);
                        }
                        Todo todo = new Todo(todoDescription.toString());
                        list[index] = todo;
                        index++;
                        printMessage("Got it. I've added this task:\n\t" + todo + "\nNow you have " + index + " tasks in the list.");
                        break;
                    case "event":
                        String[] eventSplit = command.split(" /at ");
                        String at = "";
                        try {
                            at = eventSplit[1];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            // no /at found in command
                            printMessage("Invalid input. Please check your task format.");
                            break;
                        }
                        String eventDescription = eventSplit[0].split("event ")[1];
                        Event event = new Event(eventDescription, at);
                        list[index] = event;
                        index++;
                        printMessage("Got it. I've added this task:\n\t" + event + "\nNow you have " + index + " tasks in the list.");
                        break;
                    case "deadline":
                        String[] ddlSplit = command.split(" /by ");
                        String by = "";
                        try {
                            by = ddlSplit[1];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            // no /by found in command
                            printMessage("Invalid input. Please check your task format.");
                            break;
                        }
                        String ddlDescription = ddlSplit[0].split("deadline ")[1];
                        Deadline deadline = new Deadline(ddlDescription, by);
                        list[index] = deadline;
                        index++;
                        printMessage("Got it. I've added this task:\n\t" + deadline + "\nNow you have " + index + " tasks in the list.");
                        break;
                    default:
                        // any other command creates a task by default
                        Task task = new Task(command);
                        list[index] = task;
                        index++;
                        printMessage("Got it. I've added this task:\n\t" + task + "\nNow you have " + index + " tasks in the list.");
                        break;
                }
            }
        }
        printMessage("Bye. See you next time!");
    }

    /**
     * Convert a list of tasks to a message string.
     *
     * @param list The list of tasks stored.
     * @return The string of messages.
     */
    private static String listToString(Task[] list) {
        StringBuilder result = new StringBuilder();
        if (list[0] != null) {
            result.append("Here are the tasks in your list:\n");
            for (int i = 0; i < list.length; i++) {
                if (list[i] == null) break;
                result.append(i + 1).append(". ").append(list[i]);
                if (list[i + 1] != null) result.append("\n");
            }
        } else {
            result.append("There is not task yet. Try to add a task first.");
        }
        return result.toString();
    }
}
