import java.util.Scanner;
import static java.lang.Integer.parseInt;

/**
 * Project Duke: Incrementally building a Chatbot.
 *
 * Current Progress: Level 8. Date and Times
 *
 * Description:
 * On running the program, Duke greets the user and awaits for inputted commands:
 *   - 'todo x' -> adds a ToDo task of x with no date/time attached
 *   - 'deadline x /by a b' -> adds a Deadline task of x that needs to be done by date a and time b (time is optional)
 *   - 'event x /at a b c' -> adds an Event task of x that is on date a and starts at time b and ends at time c
 *   - 'list' -> displays current list of tasks
 *   - 'check x' -> displays list of tasks due on date x
 *   - 'done x' -> marks Task x as done
 *   - 'delete x' -> deletes Task x from the task list
 *   - 'bye' -> exits the program
 *   - any other String of different patterns -> throws an exception
 *
 * CS2103T Individual Project AY 21/22 Sem 1
 * @author Benedict Chua
 */
public class Duke {
    private enum Command {
        LIST, CHECK, DONE, TODO, DEADLINE, EVENT, DELETE, BYE, UNKNOWN
    }

    private static final String INDENTATION = "     ";
    private static final String LINE_SEPARATOR = "    ____________________________________________________________";
    private static final String[] GREETING = {"Hey! I'm Duke (Tsun)!", "What do you want?",
            "...It's not like I want to help you or anything!"};
    private static final String[] GOODBYE = {"Hmph! It's not like I want to see you again or anything!"};

    private static TaskList tasksList;

    /**
     * From the input given by the user, returns the Command (first keyword) in it.
     *
     * @param input the String that the user enters into Duke.
     * @return the corresponding Command in the input.
     */
    private static Command getCommand(String input) {
        switch (input.split(" ")[0]) {
        case "bye":
            return Command.BYE;
        case "list":
            return Command.LIST;
        case "check":
            return Command.CHECK;
        case "done":
            return Command.DONE;
        case "todo":
            return Command.TODO;
        case "deadline":
            return Command.DEADLINE;
        case "event":
            return Command.EVENT;
        case "delete":
            return Command.DELETE;
        default:
            return Command.UNKNOWN;
        }
    }

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

    private static void displayTasks(String dateString) {
        System.out.println(LINE_SEPARATOR);
        tasksList.printList(dateString);
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
        tasksList = new TaskList(FileAssistant.retrieveData()); // add in file contents
        Scanner sc = new Scanner(System.in);

        // Greets user
        displayMessage(GREETING);

        // Carries out commands inputted by user into the Scanner
        String input = sc.nextLine();
        Command command = getCommand(input);
        while(command != Command.BYE) {
            try {
                switch (command) {
                case LIST:
                    displayTasks();
                    break;
                case CHECK:
                    displayTasks(filterTaskDescription(input));
                    break;
                case DONE:
                    completeTask(filterTaskIndex(input));
                    break;
                case DELETE:
                    deleteTask(filterTaskIndex(input));
                    break;
                case TODO:
                    addToList(filterTaskDescription(input), "ToDo");
                    break;
                case DEADLINE:
                    addToList(filterTaskDescription(input), "Deadline");
                    break;
                case EVENT:
                    addToList(filterTaskDescription(input), "Event");
                    break;
                default:
                    throw new InvalidCommandException(input);
                }
            } catch (DukeException e) {
                displayMessage(new String[] {e.toString()});
            } finally {
                input = sc.nextLine();
                command = getCommand(input);
            }
        }

        // Bids user farewell and program stops
        displayMessage(GOODBYE);
    }
}
