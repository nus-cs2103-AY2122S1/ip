import java.util.Scanner;
/**
 * This class encapsulates a CLI bot named Duke.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Duke {
    // Set up constant
    static final String divider = "\t--------------------------------------------------------";

    static final String banner =
            "____    __    ____  _______  __        ______   ______   .___  ___.  _______    .___________.  ______       _______   __    __   __  ___  _______  __  \r\n"
                    + "\\   \\  /  \\  /   / |   ____||  |      /      | /  __  \\  |   \\/   | |   ____|   |           | /  __  \\     |       \\ |  |  |  | |  |/  / |   ____||  | \r\n"
                    + " \\   \\/    \\/   /  |  |__   |  |     |  ,----'|  |  |  | |  \\  /  | |  |__      `---|  |----`|  |  |  |    |  .--.  ||  |  |  | |  '  /  |  |__   |  | \r\n"
                    + "  \\            /   |   __|  |  |     |  |     |  |  |  | |  |\\/|  | |   __|         |  |     |  |  |  |    |  |  |  ||  |  |  | |    <   |   __|  |  | \r\n"
                    + "   \\    /\\    /    |  |____ |  `----.|  `----.|  `--'  | |  |  |  | |  |____        |  |     |  `--'  |    |  '--'  ||  `--'  | |  .  \\  |  |____ |__| \r\n"
                    + "    \\__/  \\__/     |_______||_______| \\______| \\______/  |__|  |__| |_______|       |__|      \\______/     |_______/  \\______/  |__|\\__\\ |_______|(__) \r\n"
                    + "                                                                                                                                                       ";

    static final ToDoList list = new ToDoList();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printWelcomeMessage();
        String input = sc.nextLine();
        Command command = detectCommand(input);
        while (command != Command.EXIT) {
            switch (command) {
                case TODO:
                    try {
                        handleTodo(input);
                    } catch (DukeException e) {
                        prettyPrint(e.getMessage());
                    }
                    break;
                case DEADLINE:
                    try {
                        handleDeadline(input);
                    } catch (DukeException e) {
                        prettyPrint(e.getMessage());
                    }
                    break;
                case EVENT:
                    try {
                        handleEvent(input);
                    } catch (DukeException e) {
                        prettyPrint(e.getMessage());
                    }
                    break;
                case LIST:
                    list.printList();
                    break;
                case DONE:
                    try {
                        int index = extractIndex(input);
                        list.markTaskAsDone(index);
                    } catch (DukeException e) {
                        prettyPrint(e.getMessage());
                    }
                    break;
                case DELETE:
                    try {
                        int index = extractIndex(input);
                        list.removeFromList(index);
                    } catch (DukeException e) {
                        prettyPrint(e.getMessage());
                    }
                    break;
                case UNRECOGNISED:
                    Duke.prettyPrint(
                            String.format(
                                    "NoSuchCommandError: Unrecognised command `%s`. Perhaps you made a typo?",
                                    input.split(" ", 2)[0]));
                    break;
            }
            input = sc.nextLine();
            command = detectCommand(input);
        }
        printExitMessage();
    }

    /**
     * Formats message passed in and prints it out to the screen.
     *
     * @param message Message to be pretty printed.
     */
    public static void prettyPrint(String message) {
        System.out.printf("%s\r\n\t%s\r\n%s\r\n", divider, message, divider);
    }

    /** Prints the welcome message when a user uses the bot for the first time. */
    private static void printWelcomeMessage() {
        System.out.println(banner);
        prettyPrint(
                "Hello! I'm Duke, your personal CLI bot!\r\n\t"
                        + "I now function as a simple ToDoList.\r\n\t"
                        + "I can keep track of 3 different types of tasks:\r\n\t"
                        + "\t- ToDo: To add a new todo task, type 'todo' followed by a task description.\r\n\t"
                        + "\t- Deadline: To add a new deadline, type 'deadline' followed by the task description "
                        + "and specify the deadline using '/by <dateTime>'\r\n\t"
                        + "\t- Event: To add a new event, type 'event' followed by the event description "
                        + "and specify the timing using '/by <dateTime>'\r\n\t"
                        + "To see all your task currently, type 'list'.\r\n\t"
                        + "To delete a task, type 'delete' followed by the index of the task you wish to delete (e.g delete 2).\r\n\t"
                        + "You can also mark tasks as done by typing 'done' followed by the index of the task you completed (e.g done 2).\r\n\t"
                        + "Once you are done, just type 'exit' to quit the program.");
    }

    /**
     * Classifies the user's input into one of the Command.
     *
     * @param input Raw user's input.
     * @return The corresponding Command.
     */
    private static Command detectCommand(String input) {
        String[] inputs = input.split(" ");
        return Command.convertInput(inputs[0]);
    }

    /**
     * Extracts out index for commands that deals with modifying specific tasks.
     *
     * @param input Raw user's input.
     * @return Desired index specified by user.
     */
    private static int extractIndex(String input) throws DukeException {
        String[] inputs = input.split(" ", 2);

        // Check whether user input index
        if (inputs.length < 2) {
            throw new DukeException(
                    "NoIndexError: Please enter an index to indicate your task of interest.");
        }

        return Integer.parseInt(inputs[1]);
    }

    /**
     * Handler for ToDos task creation.
     *
     * @param input Raw user's input.
     */
    private static void handleTodo(String input) throws DukeException {
        String[] extracted = input.split(" ", 2);

        // Check whether description is entered
        if (extracted.length < 2) {
            throw new DukeException(
                    "NoDescriptionError: todo has to be followed by a description.");
        }

        ToDo task = new ToDo(extracted[1]);
        list.addToList(task);
    }

    /**
     * Handler for Deadline task creation.
     *
     * @param input Raw user's input.
     */
    private static void handleDeadline(String input) throws DukeException {
        // Check whether description is entered
        if (input.split(" ").length < 2) {
            throw new DukeException(
                    "NoDescriptionError: deadline has to be followed by a description.");
        }

        String[] extracted = input.split(" ", 2)[1].split(" /by ");

        // Check whether deadline is specified correctly
        if (extracted.length < 2) {
            throw new DukeException("NoDeadlineError: Please specify a deadline with '/by'.");
        } else if (extracted.length > 2) {
            throw new DukeException("MultipleDeadLineError: Please input only one deadline!");
        }

        String description = extracted[0];
        String deadline = extracted[1];
        Deadline task = new Deadline(description, deadline);
        list.addToList(task);
    }

    /**
     * Handler for Event task creation.
     *
     * @param input Raw user's input.
     */
    private static void handleEvent(String input) throws DukeException {
        // Check whether description is entered
        if (input.split(" ").length < 2) {
            throw new DukeException(
                    "NoDescriptionError: event has to be followed by a description.");
        }

        String[] extracted = input.split(" ", 2)[1].split(" /at ");

        // Check whether deadline is specified correctly
        if (extracted.length < 2) {
            throw new DukeException("NoDateTimeError: Please specify a date/time with '/at'.");
        } else if (extracted.length > 2) {
            throw new DukeException("MultipleDateTimeError: Please input only one date/time!");
        }

        String description = extracted[0];
        String dateTime = extracted[1];
        Event task = new Event(description, dateTime);
        list.addToList(task);
    }

    /** Prints the exit message when user types in the exit command. */
    private static void printExitMessage() {
        prettyPrint("Bye bye! See you again soon!");
    }
}
