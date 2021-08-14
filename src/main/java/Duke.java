import java.util.Scanner;
import static java.lang.Integer.parseInt;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * Current Progress: Level 6. Delete
 *
 * Description:
 * On running the program, Duke greets the user and awaits for inputted commands:
 *   - 'todo x' -> adds a ToDo task of x with no date/time attached
 *   - 'deadline x /by y' -> adds a Deadline task of x that needs to be done by y
 *   - 'event x /at y' -> adds an Event task of x that starts and ends at a specific time y
 *   - 'list' -> displays current list of tasks
 *   - 'done x' -> marks Task x as done
 *   - 'delete x' -> deletes Task x from the task list
 *   - 'bye' -> exits the program
 *   - any other String of different patterns -> throws an exception
 *
 * @author Benedict Chua
 */
public class Duke {
    private static final String INDENTATION = "     ";
    private static final String LINE_SEPARATOR = "    ____________________________________________________________";
    private static final String[] GREETING = {"Hey! I'm Duke (Tsun)!", "What do you want?",
        "...It's not like I want to help you or anything!"};
    private static final String[] GOODBYE = {"Hmph! It's not like I want to see you again or anything!"};

    private static TaskList tasksList;

    /**
     * Prints messages line by line enclosed within line separators.
     *
     * @param messages an array of String that contains the messages to be printed
     */
    private static void displayMessage(String[] messages) {
        System.out.println(LINE_SEPARATOR);
        for (String message : messages) {
            System.out.println(INDENTATION + message);
        }
        System.out.println(LINE_SEPARATOR + "\n");
    }

    /**
     * Prints tasks line by line enclosed within line separators.
     *
     * Each task is indexed by the order they were added into the task list, starting from 1.
     */
    private static void displayTasks() {
        System.out.println(LINE_SEPARATOR);
        tasksList.printList();
        System.out.println(LINE_SEPARATOR + "\n");
    }

    /**
     * Takes in the original inputted command and filters it for the task description.
     *
     * The description is the command string minus the first word which is the command itself.
     * Throws an EmptyDescriptionException when description is missing (including if it contains only white space).
     *
     * @param command the original command inputted by the user.
     * @return the filtered String that contains only the description of the task.
     * @throws EmptyDescriptionException when description is missing
     */
    private static String filterTaskDescription(String command) throws EmptyDescriptionException {
        String[] commandItems = command.split(" ", 2);
        if (commandItems.length == 1) {
            throw new EmptyDescriptionException();
        }
        String filteredDescription  = command.split(" ", 2)[1];
        if (filteredDescription.trim().isEmpty()) {
            throw new EmptyDescriptionException();
        }
        return filteredDescription.trim();
    }

    /**
     * Takes in the original inputted command and filters and parses it for the given index.
     *
     * The index is the command string minus the first word which is the command itself.
     * Throws an MissingIndexException when index is missing (including if it contains only white space).
     *
     * @param command the original command inputted by the user.
     * @return the filtered and parsed int that refers to the index of the task.
     * @throws MissingIndexException when index is missing
     */
    private static int filterTaskIndex(String command) throws MissingIndexException {
        String[] commandItems = command.split(" ", 2);
        if (commandItems.length == 1) {
            throw new MissingIndexException();
        }
        String indexString  = command.split(" ", 2)[1];
        if (indexString.trim().isEmpty()) {
            throw new MissingIndexException();
        }
        return parseInt(indexString);
    }

    /**
     * Adds the given task into the task list and prints the outcome using displayMessage.
     *
     * @param task description of task
     * @param typeOfTask type of task from ToDo, Deadline, Event. Handled by switch case.
     */
    private static void addToList(String task, String typeOfTask) {
        displayMessage(tasksList.addToList(task, typeOfTask));
    }

    /**
     * Marks task in the given index as completed (done) and prints the outcome using displayMessage.
     *
     * @param index index of task to be mark as completed (indexing starts from 1)
     */
    private static void completeTask(int index) {
        displayMessage(tasksList.markTaskAsDone(index));
    }

    private static void deleteTask(int index) {
        displayMessage(tasksList.deleteTask(index));
    }

    public static void main(String[] args) {
        // Initialise program
        tasksList = new TaskList();
        Scanner sc = new Scanner(System.in);

        // Greets user
        displayMessage(GREETING);

        // Carries out commands inputted by user into the Scanner
        String command = sc.nextLine();
        while(!command.equals("bye")) {
            try {
                switch (command.split(" ")[0]) {
                case "list":
                    displayTasks();
                    break;
                case "done":
                    completeTask(filterTaskIndex(command));
                    break;
                case "delete":
                    deleteTask(filterTaskIndex(command));
                    break;
                case "todo":
                    addToList(filterTaskDescription(command), "ToDo");
                    break;
                case "deadline":
                    addToList(filterTaskDescription(command), "Deadline");
                    break;
                case "event":
                    addToList(filterTaskDescription(command), "Event");
                    break;
                default:
                    throw new InvalidCommandException(command);
                }
            } catch (DukeException e) {
                displayMessage(new String[] {e.toString()});
            } finally {
                command = sc.nextLine();
            }
        }

        // Bids user farewell and program stops
        displayMessage(GOODBYE);
    }
}
