import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class runs a personal assistant chatbot that helps a person keep track of various tasks.
 */
public class Duke {

    // To represent line separators in the chat.
    private static final String LINE_HORIZONTAL =
            "___________________________________________________________";
    // To store the tasks to be done.
    private static ArrayList<Task> taskList = new ArrayList<>();
    // The possible commands for the bot.
    private enum Command {
        TODO, DEADLINE, EVENT, LIST, MARK, DELETE, BYE
    }

    /***
     * Initializes the chatbot.
     */
    public static void main(String[] args) {

        // Prints initial message as prompt.
        System.out.printf("%s\nGreetings! This is Elsa.\n" +
                "What can I do for you?\n" +
                "%s\n",
                LINE_HORIZONTAL, LINE_HORIZONTAL);

        // Initializes scanner to take input from user.
        Scanner scanner = new Scanner(System.in);
        String input;

        // Tracks if the user is done with the assistant.
        boolean exit = false;

        // Takes in input and performs actions accordingly.
        while(!(exit)) {
            input = scanner.nextLine().trim();
            try {
                Command commandToExecute = interpretCommand(input);
                switch (commandToExecute) {
                    case LIST:
                        printList();
                        break;
                    case MARK:
                        int toMark = Integer.parseInt(input.substring(5));
                        markTaskAsDone(toMark);
                        break;
                    case DELETE:
                        int toDelete = Integer.parseInt(input.substring(7));
                        deleteTask(toDelete);
                        break;
                    case TODO:
                        addToDo(input);
                        break;
                    case DEADLINE:
                        addDeadline(input);
                        break;
                    case EVENT:
                        addEvent(input);
                        break;
                    case BYE:
                        printBye();
                        exit = true;
                        break;
                }
            } catch (InvalidCommandException e) {
                System.out.printf("%s\n" +
                        "I don't quite understand what that means.\n" +
                        "Could you please rephrase that?\n" +
                        "%s\n", LINE_HORIZONTAL, LINE_HORIZONTAL);
            } catch (MissingTaskException e) {
                System.out.printf("%s\n" +
                        "You might have missed out on the task.\n" +
                        "Could you please enter it again?\n" +
                        "%s\n", LINE_HORIZONTAL, LINE_HORIZONTAL);
            } catch (MissingTimeException e) {
                System.out.printf("%s\n" +
                        "You might have missed out on the time.\n" +
                        "Could you please enter it again?\n" +
                        "%s\n", LINE_HORIZONTAL, LINE_HORIZONTAL);
            } catch (InvalidTaskException e) {
                System.out.printf("%s\n" +
                        "You might have mistyped the task number.\n" +
                        "Please ensure task number is between 1 and %d.\n" +
                        "%s\n", LINE_HORIZONTAL, taskList.size(), LINE_HORIZONTAL);
            }
        }
        scanner.close();
    }

    /***
     * Interprets the command entered by user and returns its enum.
     *
     * @param input The input entered by user.
     * @return The command enum in the input.
     */
    public static Command interpretCommand(String input) throws InvalidCommandException {
        if (input.equalsIgnoreCase("list")) {
            return Command.valueOf("LIST");
        } else if (input.toLowerCase().indexOf("done") != -1) {
            return Command.valueOf("MARK");
        } else if (input.toLowerCase().indexOf("delete") != -1) {
            return Command.valueOf("DELETE");
        } else if (input.toLowerCase().indexOf("todo") != -1) {
            return Command.valueOf("TODO");
        } else if (input.toLowerCase().indexOf("deadline") != -1) {
            return Command.valueOf("DEADLINE");
        } else if (input.toLowerCase().indexOf("event") != -1) {
            return Command.valueOf("EVENT");
        } else if (input.toLowerCase().equals("bye")) {
            return Command.valueOf("BYE");
        } else {
            throw new InvalidCommandException("Invalid command!");
        }
    }

    /***
     * Prints out every task in the list.
     */
    public static void printList() {
        System.out.println(LINE_HORIZONTAL);

        if (taskList.size() == 0) {
            System.out.printf("There are no tasks to be done! Hooray!\n");
        } else {
            System.out.println("Here is your list of tasks:");

            for (int i = 0; i < taskList.size(); i++) {
                String taskName = taskList.get(i).toString();
                System.out.printf("%d.%s\n", i + 1, taskName);
            }
        }

        System.out.println(LINE_HORIZONTAL);
    }

    /***
     * Marks the corresponding task as done and prints confirmation.
     *
     * @param toMark The index of the task to be marked.
     */
    public static void markTaskAsDone(int toMark) throws InvalidTaskException {
        if (toMark <= 0 || toMark > taskList.size()) {
            throw new InvalidTaskException("Task is not found");
        }

        taskList.get(toMark - 1).markAsDone();

        System.out.printf("%s\n" +
                "Great job!\n" +
                "The following task is marked as done:\n" +
                "\t%s\n" +
                "%s\n",
                LINE_HORIZONTAL, taskList.get(toMark - 1).toString(), LINE_HORIZONTAL);
    }

    /***
     * Deletes the corresponding task as done and prints confirmation.
     *
     * @param toDelete The index of the task to be deleted.
     */
    public static void deleteTask(int toDelete) throws InvalidTaskException {
        if (toDelete <= 0 || toDelete > taskList.size()) {
            throw new InvalidTaskException("Task is not found");
        }

        System.out.printf("%s\n" +
                "Done!\n" +
                "The following task has been removed:\n" +
                "\t%s\n" +
                "You now have %d tasks left in your list!\n" +
                "%s\n",
                LINE_HORIZONTAL, taskList.get(toDelete - 1).toString(), taskList.size() - 1, LINE_HORIZONTAL);

        taskList.remove(toDelete - 1);
    }

    /***
     * Adds the to do entered by the user to the list and prints it.
     *
     * @param input The to do inputted by the user.
     */
    public static void addToDo(String input) throws MissingTaskException {
        if (input.length() < 6) {
            throw new MissingTaskException("Task not found.");
        }

        String taskName = input.substring(5);
        taskList.add(new ToDo(taskName));
        printTaskAdded(taskName);
    }

    /***
     * Adds the deadline entered by the user to the list and prints it.
     *
     * @param input The deadline inputted by the user.
     */
    public static void addDeadline(String input)
            throws MissingTaskException, MissingTimeException {
        int separation = input.indexOf("/by");

        if (separation == -1) {
            throw new MissingTimeException("Time not found");
        }

        if (separation < 11) {
            throw new MissingTaskException("Task not found");
        }
        String taskName = input.substring(9, separation - 1);

        if (input.substring(separation + 4).length() < 1) {
            throw new MissingTimeException("Time not found");
        }

        String time = input.substring(separation + 4);
        taskList.add(new Deadline(taskName, time));
        printTaskAdded(taskName);
    }

    /***
     * Adds the event entered by the user to the list and prints it.
     *
     * @param input The event inputted by the user.
     */
    public static void addEvent(String input)
            throws MissingTaskException, MissingTimeException {
        int separation = input.indexOf("/at");
        if (separation == -1) {
            throw new MissingTimeException("Time not found");
        }

        if (separation < 8) {
            throw new MissingTaskException("Task not found");
        }
        String taskName = input.substring(6, separation - 1);

        if (input.substring(separation + 4).length() < 1) {
            throw new MissingTimeException("Time not found");
        }

        String time = input.substring(separation + 4);
        taskList.add(new Event(taskName, time));
        printTaskAdded(taskName);
    }

    /***
     * Prints the confirmation of the addition of the last task.
     *
     * @param taskName The name of the task just added.
     */
    public static void printTaskAdded(String taskName) {
        System.out.printf("%s\n" +
                "Gotcha! The following task has been added:\n" +
                "\t%s\n" +
                "You now have %d tasks in your list!\n" +
                "%s\n",
                LINE_HORIZONTAL, taskName,
                taskList.size(), LINE_HORIZONTAL);
    }

    /***
     * Prints the farewell message for the user.
     */
    public static void printBye() {
        System.out.printf("%s\n" +
                "Goodbye. Hope to see you again soon!\n",
                LINE_HORIZONTAL);
    }
}
