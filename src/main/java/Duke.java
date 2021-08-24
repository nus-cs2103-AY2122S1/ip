import java.util.Scanner;

/**
 * This class encapsulates a CLI bot named Duke.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class Duke {
    // Initialise message constants
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String EXIT_MESSAGE = "Goodbyeeee! Hope to see you again soon! :>";
    private static final String SEPARATOR = "\t-------------------------------------------------------";
    private static final String BLANK_INPUT_MESSAGE = "Please enter something! ANYTHING!";
    private static final String BLANK_DESCRIPTION_MESSAGE = "OOPS!!! The description of %s cannot be empty! x_x";
    private static final String TODO_ERROR_MESSAGE = "Invalid use of 'todo' command!! @_@\n\tTo add a new todo, use 'todo <task>'.";
    private static final String DEADLINE_ERROR_MESSAGE = "Invalid use of 'deadline' command!! @_@\n\tTo add a new deadline, use 'deadline <task> /by <due-date>'.";
    private static final String EVENT_ERROR_MESSAGE = "Invalid use of 'event' command!! @_@\n\tTo add a new event, use 'event <title> /at <time-stamp>'.";
    private static final String DONE_ERROR_MESSAGE = "Invalid use of 'done' command!! @_@\n\tTo mark a task as done, use 'done <task-number>'.";
    private static final String INPUT_PROMPT = "Enter a command *_*";
    private Storage storage;
    private TaskHandler taskHandler;
    /**
     * Formats the input and prints it in a formatted version.
     *
     * @param message message to be printed.
     */
    public static void prettify(String message) {
        System.out.printf("%s\n\t%s\n%s\n", SEPARATOR, message, SEPARATOR);
    }

    /** Prints introduction message when bot is first launched. **/
    private static void printIntroMessage() {
        prettify(
                "Hello! I'm Duke, your personal CLI bot! :D\n\t"
                        + "I function as a simple TodoList.\n\t"
                        + "I can keep track of 3 types of tasks:\n\t"
                        + "\t> Todo: To add a new todo task, enter 'todo' followed by a task description.\n\t"
                        + "\t> Deadline: To add a new deadline, enter 'deadline' followed by the task description "
                        + "and specify the due date using '/by <due-date>'\n\t"
                        + "\t> Event: To add a new event, enter 'event' followed by the event description "
                        + "and specify the time using '/by <time-stamp>'\n\t"
                        + "- To see all your tasks, enter 'list'.\n\t"
                        + "- To delete a task, enter 'delete' followed by the index of the task you wish to delete (e.g delete 2).\n\t"
                        + "- You can also mark tasks as done by typing 'done' followed by the index of the task you completed (e.g done 2).\n\t"
                        + "- Once you are done, just enter 'bye' to quit the program.\n\t"
                        + "Have fun! ^_^");

    }

    /** Runs Duke **/
    public void start() {
        // Welcome message
        printIntroMessage();
        Scanner sc = new Scanner(System.in);
        storage = new Storage();
        Command command;

        // Logic of program based on user input
        while (sc.hasNextLine()) {
            try {
                taskHandler = new TaskHandler(storage.loadTasks(), storage);
            } catch (DukeException e) {
                prettify(e.getMessage());
            }
            String input = sc.nextLine();
            String inputUpperCase = input.trim().toUpperCase();
            String commandWord = input.split("\\s+")[0];
            String descExtractedRaw = input.replace(commandWord, "").trim();
            command = Command.evaluateInput(commandWord);
            try {
                if (inputUpperCase.isEmpty()) {
                    throw new DukeException(BLANK_INPUT_MESSAGE);
                }
                switch (command) {
                    case LIST:
                        prettify(taskHandler.toString());
                        break;
                    case DONE:
                        validateFilled(descExtractedRaw, command);
                        validateDetails(descExtractedRaw, command);
                        int taskIndex = Integer.parseInt(descExtractedRaw);
                        taskHandler.markTaskAsDone(taskIndex);
                        taskHandler.updateData();
                        break;
                    case DEADLINE:
                        validateFilled(descExtractedRaw, command);
                        validateDetails(descExtractedRaw, command);
                        String[] deadlineDetails = descExtractedRaw.split("\\s+/by\\s+", 2);
                        Deadline dl = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                        taskHandler.addTask(dl);
                        storage.writeToFile(dl);
                        break;
                    case TODO:
                        validateFilled(descExtractedRaw, command);
                        Todo td = new Todo(descExtractedRaw);
                        taskHandler.addTask(td);
                        storage.writeToFile(td);
                        break;
                    case EVENT:
                        validateFilled(descExtractedRaw, command);
                        validateDetails(descExtractedRaw, command);
                        String[] eventDetails = descExtractedRaw.split("\\s+/at\\s+", 2);
                        Event event = new Event(eventDetails[0], eventDetails[1]);
                        taskHandler.addTask(event);
                        storage.writeToFile(event);
                        break;
                    case DELETE:
                        validateFilled(descExtractedRaw, command);
                        validateDetails(descExtractedRaw, command);
                        int deleteIndex = Integer.parseInt(descExtractedRaw);
                        taskHandler.deleteTask(deleteIndex);
                        taskHandler.updateData();
                        break;
                    case BYE:
                        prettify(EXIT_MESSAGE);
                        sc.close();
                        return;
                    default:
                        throw new DukeException(("I don't quite understand you. :-("));
                }
            } catch (DukeException e) {
                prettify(e.getMessage());
            } finally {
                if (!command.equals(Command.BYE)) {
                    prettify(INPUT_PROMPT);
                }
            }
        }
    }

    /**
     * Validates input by ensuring it is not empty, throws exception if input is empty.
     *
     * @param input raw input from user.
     * @param c the specific command given by the user.
     * @throws DukeException prevents empty inputs.
     */
    private static void validateFilled(String input, Command c) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException(String.format(BLANK_DESCRIPTION_MESSAGE, c.toString().toLowerCase()));
        }
    }

    /**
     *Validates the format of the given command, throws exception if format is wrong.
     *
     * @param input raw input from user.
     * @param c the specific command given by the user.
     * @throws DukeException prevents wrong details format.
     */
    public static void validateDetails(String input, Command c) throws DukeException {
        switch (c) {
            case DONE:
                try {
                    Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    throw new DukeException(DONE_ERROR_MESSAGE);
                }
                break;
            case DEADLINE:
                String[] deadlineTime = input.split("\\s+/by\\s+", 2);
                if (deadlineTime.length < 2) {
                    throw new DukeException(DEADLINE_ERROR_MESSAGE);
                }
                break;
            case TODO:
                if (input.isEmpty()) {
                    throw new DukeException(TODO_ERROR_MESSAGE);
                }
                break;
            case EVENT:
                String[] eventTime = input.split("\\s+/at\\s+", 2);
                if (eventTime.length < 2) {
                    throw new DukeException(EVENT_ERROR_MESSAGE);
                }
                break;
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}