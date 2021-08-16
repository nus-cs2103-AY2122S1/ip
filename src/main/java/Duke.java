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
            } else if (command.length() > 5 && command.substring(0, 5).equals("done ")) {
                // done command
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
                        printMessage("Duke can only save up to 100 task ;(");
                    }
                } catch (NumberFormatException e) {
                    // command done is not followed by a number
                    list[index] = new Task(command);
                    index++;
                    printMessage("added: " + command);
                }
            } else {
                list[index] = new Task(command);
                index++;
                printMessage("added: " + command);
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
