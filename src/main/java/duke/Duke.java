package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.data.Storage;
import duke.data.TaskList;
import duke.io.Command;
import duke.io.OutputFormatter;
import duke.io.Parser;
import duke.tasks.*;

/**
 * Main program managing all main functions.
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructs the main Duke object to run the program.
     *
     * @param filePath The path of the save file to be used/created
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);

        try {
            taskList = storage.loadTaskData();
        } catch (IOException | DukeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Constructs the main Duke object to run the program with a hardcoded default filepath.
     */
    public Duke() {
        this.storage = new Storage("data/Duke.txt");

        try {
            taskList = storage.loadTaskData();
        } catch (IOException | DukeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Runs the main program loop.
     * Manages the main loop, handling all user input and saving data/etc.
     */
    public void run() {
        // Deprecated
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Returns a String response to be sent to the user after performing the respective action indicated by user.
     *
     * @param userInput A string containing the commands that the user desires to execute
     * @return A string containing the response from the application after performing the desired command
     */
    public String getResponse(String userInput) {
        while (true) {
            try {
                Command command = Parser.parse(userInput.trim());

                switch (command.getCommand()) {
                case BYE:
                    return getResponseBye();
                case LIST:
                    return getResponseList();
                case DONE:
                    return getResponseDone(command);
                case DELETE:
                    return getResponseDelete(command);
                case TODO:
                    return getResponseTodo(userInput);
                case DEADLINE:
                    return getResponseDeadline(command);
                case EVENT:
                    return getResponseEvent(command);
                case DOWITHINPERIOD:
                    return getResponseDoWithinPeriod(command);
                case DATE:
                    return getResponseDate(userInput);
                case FIND:
                    return getResponseFind(command);
                default:
                    assert(true); // Should never reach here
                    break;
                }
            } catch (DukeException | IOException e) {
                return e.getMessage();
            } catch (DateTimeParseException e) {
                return "Unknown date format. Please input a valid date in the format: YYYY-MM-DD";
            }
        }
    }

    /**
     * Returns a message that the application will respond to the user with when BYE command is used.
     *
     * @return A String containing the message to be transmitted to the user
     */
    public String getResponseBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message that the application will respond to the user with when LIST command is used.
     *
     * @return A String containing the message to be transmitted to the user
     */
    public String getResponseList() {
        return OutputFormatter.formatTaskList(taskList);
    }

    /**
     * Returns a message that the application will respond to the user with when DONE command is used.
     *
     * @param command The object containing all the details about the requested action by the user
     * @return A String containing the message to be transmitted to the user
     * @throws IOException Thrown when IO exceptions occurs when saving task data to hard disk
     */
    public String getResponseDone(Command command) throws IOException {
        // -1 to account for zero-indexing
        int index = Integer.parseInt(command.getArgs()[0]) - 1;
        taskList.get(index).markAsDone();

        // update file
        storage.saveTaskData(taskList);

        return "Nice! I've marked this task as done:\n  " + taskList.get(index);
    }

    /**
     * Returns a message that the application will respond to the user with when DELETE command is used.
     *
     * @param command The object containing all the details about the requested action by the user
     * @return A String containing the message to be transmitted to the user
     * @throws IOException Thrown when IO exceptions occurs when saving task data to hard disk
     */
    public String getResponseDelete(Command command) throws IOException {
        // -1 to account for zero-indexing
        Task removedTask = taskList.remove(Integer.parseInt(command.getArgs()[0]) - 1);

        // update file
        storage.saveTaskData(taskList);

        return "Noted. I've removed this task:\n  " + removedTask + "\nNow you have "
                + taskList.size() + " tasks in the list.";

    }

    /**
     * Returns a message that the application will respond to the user with when TODO command is used.
     *
     * @param userInput The string containing the original unparsed command by the user
     * @return A String containing the message to be transmitted to the user
     * @throws IOException Thrown when IO exceptions occurs when saving task data to hard disk
     */
    public String getResponseTodo(String userInput) throws IOException {
        Todo newTodo = new Todo(userInput.substring(5).trim());
        this.taskList.add(newTodo);

        // update file
        storage.saveTaskData(taskList);

        return "Got it. I've added this task:\n  "
                + newTodo + "\nNow you have " + this.taskList.size()
                + " tasks in the list.";
    }

    /**
     * Returns a message that the application will respond to the user with when DEADLINE command is used.
     *
     * @param command The object containing all the details about the requested action by the user
     * @return A String containing the message to be transmitted to the user
     * @throws IOException Thrown when IO exceptions occurs when saving task data to hard disk
     */
    public String getResponseDeadline(Command command) throws IOException {
        String description = command.getArgs()[0];
        LocalDate dateTime = LocalDate.parse(command.getArgs()[1]);
        Deadline newDeadline = new Deadline(description, dateTime);
        taskList.add(newDeadline);

        // update file
        storage.saveTaskData(taskList);

        return "Got it. I've added this task:\n  " + newDeadline + "\nNow you have "
                + taskList.size() + " tasks in the list.";
    }

    /**
     * Returns a message that the application will respond to the user with when EVENT command is used.
     *
     * @param command The object containing all the details about the requested action by the user
     * @return A String containing the message to be transmitted to the user
     * @throws IOException Thrown when IO exceptions occurs when saving task data to hard disk
     */
    public String getResponseEvent(Command command) throws IOException {
        String description = command.getArgs()[0];
        LocalDate dateTime = LocalDate.parse(command.getArgs()[1]);
        Event newEvent = new Event(description, dateTime);
        taskList.add(newEvent);

        // update file
        storage.saveTaskData(taskList);

        return "Got it. I've added this task:\n  "
                + newEvent + "\nNow you have " + taskList.size()
                + " tasks in the list.";
    }

    /**
     * Returns a message that the application will respond to the user with when DOWITHINPERIOD command is used.
     *
     * @param command The object containing all the details about the requested action by the user
     * @return A String containing the message to be transmitted to the user
     * @throws IOException Thrown when IO exceptions occurs when saving task data to hard disk
     */
    public String getResponseDoWithinPeriod(Command command) throws IOException {
        String description = command.getArgs()[0];
        LocalDate startOfPeriod = LocalDate.parse(command.getArgs()[1]);
        LocalDate endOfPeriod = LocalDate.parse(command.getArgs()[2]);
        DoWithinPeriodTask newDoWithinPeriodTask = new DoWithinPeriodTask(description, startOfPeriod, endOfPeriod);
        taskList.add(newDoWithinPeriodTask);

        // update file
        storage.saveTaskData(taskList);

        return "Got it. I've added this task:\n  "
                + newDoWithinPeriodTask + "\nNow you have " + taskList.size()
                + " tasks in the list.";
    }

    /**
     * Returns a message that the application will respond to the user with when DATE command is used.
     *
     * @param userInput The string containing the original unparsed command by the user
     * @return A String containing the message to be transmitted to the user
     * @throws IOException Thrown when IO exceptions occurs when saving task data to hard disk
     */
    public String getResponseDate(String userInput) {
        LocalDate queryDate = LocalDate.parse(userInput.substring(5));
        TaskList dueTasks = taskList.filterByDate(queryDate);

        return OutputFormatter.formatTaskList(dueTasks, queryDate);
    }

    /**
     * Returns a message that the application will respond to the user with when EVENT command is used.
     *
     * @param command The object containing all the details about the requested action by the user
     * @return A String containing the message to be transmitted to the user
     * @throws IOException Thrown when IO exceptions occurs when saving task data to hard disk
     */
    public String getResponseFind(Command command) {
        String keyword = command.getArgs()[0];

        return OutputFormatter.formatTaskList(taskList.containsKeyword(keyword));
    }
}
