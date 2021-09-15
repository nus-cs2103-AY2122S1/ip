package lifeline.command;

import static lifeline.util.ErrorString.ERROR_DEADLINE_INCORRECT_FORMAT;
import static lifeline.util.ErrorString.ERROR_DEADLINE_MISSING_DETAILS;
import static lifeline.util.ErrorString.ERROR_DELETE_MISSING_INDEX;
import static lifeline.util.ErrorString.ERROR_DONE_MISSING_INDEX;
import static lifeline.util.ErrorString.ERROR_ENDTIME_AFTER_STARTTIME;
import static lifeline.util.ErrorString.ERROR_EVENT_INCORRECT_FORMAT;
import static lifeline.util.ErrorString.ERROR_EVENT_MISSING_DETAILS;
import static lifeline.util.ErrorString.ERROR_FIND_MISSING_KEYWORD;
import static lifeline.util.ErrorString.ERROR_INDEX_OUT_OF_BOUNDS;
import static lifeline.util.ErrorString.ERROR_INVALID_COMMAND;
import static lifeline.util.ErrorString.ERROR_MISSING_INDEX;
import static lifeline.util.ErrorString.ERROR_NON_INTEGER_INDEX;
import static lifeline.util.ErrorString.ERROR_TODO_MISSING_DETAILS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import lifeline.exception.LifelineException;
import lifeline.parser.DateTimeParser;
import lifeline.storage.Storage;
import lifeline.task.Deadline;
import lifeline.task.Event;
import lifeline.task.Task;
import lifeline.task.TaskList;
import lifeline.task.ToDo;
import lifeline.ui.Ui;

/**
 * The class CommandHandler handles all commands supplied by the user.
 */
public class CommandHandler {

    /**
     * Prints out given TaskList using the given Ui.
     *
     * @param command User input.
     * @param storage Storage to save or load tasks.
     * @param taskList List of tasks.
     * @param ui Ui to display information to user.
     * @return String to represent all tasks.
     */
    public static String handleList(String command, Storage storage, TaskList taskList, Ui ui) {
        assert Command.LIST.hasCommand(command.split("\\s")[0]);
        return ui.showTaskList(taskList);
    }

    /**
     * Displays available alias to user.
     *
     * @param command User input.
     * @param storage Storage to save or load tasks.
     * @param taskList List of tasks.
     * @param ui Ui to display information to user.
     * @return Available alias to user.
     */
    public static String handleAlias(String command, Storage storage, TaskList taskList, Ui ui) {
        assert Command.ALIAS.hasCommand(command.split("\\s")[0]);
        return ui.showAlias();
    }

    /**
     * Displays goodbye message to the user when user exits the program.
     *
     * @param command User input.
     * @param storage Storage to save or load tasks.
     * @param taskList List of tasks.
     * @param ui Ui to display information to user.
     * @return Goodbye message.
     */
    public static String handleBye(String command, Storage storage, TaskList taskList, Ui ui) {
        assert Command.BYE.hasCommand(command.split("\\s")[0]);
        return ui.exit();
    }

    /**
     * Displays help message to inform users on available commands and its usage.
     * @param command User input.
     * @param storage Storage to save or load tasks.
     * @param taskList List of tasks.
     * @param ui Ui to display information to user.
     * @return Help message to inform users on available commands and its usage.
     */
    public static String handleHelp(String command, Storage storage, TaskList taskList, Ui ui) {
        assert Command.HELP.hasCommand(command.split("\\s")[0]);
        return ui.showHelpMessage();
    }

    /**
     * Creates and saves an instance of a Deadline. Instance is added to TaskList.
     *
     * @param command User input.
     * @param storage Storage to save or load tasks.
     * @param taskList List of tasks.
     * @param ui Ui to display information to user.
     * @return Message to notify user that the deadline has been created.
     * @throws LifelineException if command is incomplete or not of the correct format or tasklist is not able to be.
     *                           saved
     */
    public static String handleDeadline(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        assert Command.DEADLINE.hasCommand(splitCommands(command)[0]);
        Task newTask = createDeadline(command);
        taskList.add(newTask);
        storage.save(taskList);
        return ui.showAddedTask(newTask);
    }

    /**
     * Creates and saves an instance of an Event. Instance is added to TaskList.
     *
     * @param command User input.
     * @param storage Storage to save or load tasks.
     * @param taskList List of tasks.
     * @param ui Ui to display information to user.
     * @return Message to notify user that the event has been created.
     * @throws LifelineException if command is incomplete or not of the correct format or tasklist is not able to be
     *                           saved.
     */
    public static String handleEvent(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        assert Command.EVENT.hasCommand(splitCommands(command)[0]);
        Task newTask = createEvent(command);
        taskList.add(newTask);
        storage.save(taskList);
        return ui.showAddedTask(newTask);
    }

    /**
     * Finds tasks in taskList with given keyword.
     *
     * @param command User input.
     * @param storage Storage to save or load tasks.
     * @param taskList List of tasks.
     * @param ui Ui to display information to user.
     * @return All tasks whose name contains the keyword.
     * @throws LifelineException if user does not specify a keyword or no tasks are found.
     */
    public static String handleFind(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        String[] commands = splitCommands(command);
        assert Command.FIND.hasCommand(commands[0]);
        String keyword = commands[1];
        TaskList foundTasks = taskList.findTasks(keyword.toLowerCase());
        return ui.showFoundTasks(foundTasks, keyword);
    }

    /**
     * Creates and saves an instance of ToDo. Instance is added to TaskList.
     *
     * @param command User input.
     * @param storage Storage to save or load tasks.
     * @param taskList List of tasks.
     * @param ui Ui to display information to user.
     * @return Message to notify user that the todo has been created.
     * @throws LifelineException if command is incomplete or not of the correct format or tasklist is not able to be
     *                           saved.
     */
    public static String handleToDo(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        assert Command.TODO.hasCommand(splitCommands(command)[0]);
        Task newTask = createToDo(command);
        taskList.add(newTask);
        storage.save(taskList);
        return ui.showAddedTask(newTask);
    }

    /**
     * Marks a tasks at indices as completed.
     *
     * @param command User input.
     * @param storage Storage to save or load tasks.
     * @param taskList List of tasks.
     * @param ui Ui to display information to user.
     * @return Message to notify user that the task has been marked as completed.
     * @throws LifelineException if command is incomplete or the index given is out of bounds or not an integer or
     *                           taskList is not able to be saved.
     */
    public static String handleDone(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        assert Command.DONE.hasCommand(splitCommands(command)[0]);
        ArrayList<Integer> indicesToDelete = getIndicesFromCommand(command);
        checkIndexBounds(indicesToDelete, taskList);
        taskList.completeMultipleTasks(indicesToDelete);
        storage.save(taskList);
        return ui.showCompletedTaskMessage(taskList);
    }

    /**
     * Deletes tasks at specified indices.
     *
     * @param command User input.
     * @param storage Storage to save or load tasks.
     * @param taskList List of tasks.
     * @param ui Ui to display information to user.
     * @return Message to the user that the task has been deleted.
     * @throws LifelineException if command is incomplete or the index given is out of bounds or not an integer or
     *                           taskList is not able to be saved.
     */
    public static String handleDelete(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        assert Command.DELETE.hasCommand(splitCommands(command)[0]);
        ArrayList<Integer> indicesToDelete = getIndicesFromCommand(command);
        checkIndexBounds(indicesToDelete, taskList);
        taskList.deleteMultipleTasks(indicesToDelete);
        storage.save(taskList);
        return ui.showDeletedTaskMessage(taskList);
    }

    private static ArrayList<Integer> getIndicesFromCommand(String command) throws LifelineException {
        String[] commands = splitCommands(command);
        String indices = commands[1];
        String[] splitIndices =
                Arrays.stream(indices.split("\\s*,\\s*"))
                        .filter(index -> index.length() > 0)
                        .toArray(String[]::new);

        if (splitIndices.length == 0) {
            throw new LifelineException(ERROR_MISSING_INDEX);
        }

        ArrayList<Integer> indicesAsInteger = new ArrayList<>();
        for (int i = 0; i < splitIndices.length; i++) {
            try {
                int taskIndex = Integer.parseInt(splitIndices[i]) - 1;
                indicesAsInteger.add(taskIndex);
            } catch (NumberFormatException e) {
                throw new LifelineException(ERROR_NON_INTEGER_INDEX);
            }
        }
        return indicesAsInteger;
    }

    private static void checkIndexBounds(ArrayList<Integer> indices, TaskList taskList) throws LifelineException {
        for (int i = 0; i < indices.size(); i++) {
            int currIndex = indices.get(i);
            if (currIndex >= taskList.getSize() || currIndex < 0) {
                throw new LifelineException(ERROR_INDEX_OUT_OF_BOUNDS);
            }
        }
    }

    private static String[] splitCommands(String command) throws LifelineException {
        String[] commands = command.split("\\s", 2);
        if (commands.length < 2 || commands[1].trim().startsWith("/by") || commands[1].trim().startsWith("/at")) {
            handleIncompleteCommand(commands[0]);
        }
        return commands;
    }

    private static void handleIncompleteCommand(String command) throws LifelineException {
        switch (command) {
        case "done":
            // Fallthrough
        case "complete":
            throw new LifelineException(ERROR_DONE_MISSING_INDEX);
        case "del":
            // Fallthrough
        case "rm":
            // Fallthrough
        case "delete":
            throw new LifelineException(ERROR_DELETE_MISSING_INDEX);
        case "t":
            // Fallthrough
        case "td":
            // Fallthrough
        case "todo":
            throw new LifelineException(ERROR_TODO_MISSING_DETAILS);
        case "d":
            // Fallthrough
        case "dl":
            // Fallthrough
        case "deadline":
            throw new LifelineException(ERROR_DEADLINE_MISSING_DETAILS);
        case "e":
            // Fallthrough
        case "event":
            throw new LifelineException(ERROR_EVENT_MISSING_DETAILS);
        case "f":
            // Fallthrough
        case "find":
            throw new LifelineException(ERROR_FIND_MISSING_KEYWORD);
        default:
            throw new LifelineException(ERROR_INVALID_COMMAND);
        }

    }

    private static Task createToDo(String command) throws LifelineException {
        String[] commands = splitCommands(command);
        Task newTask = new ToDo(commands[1]);
        return newTask;
    }

    private static Deadline createDeadline(String command) throws LifelineException {
        String[] details = splitCommands(command);
        String[] descriptions = details[1].trim().split("/by", 2);

        if (descriptions.length < 2) {
            throw new LifelineException(ERROR_DEADLINE_INCORRECT_FORMAT);
        }

        String[] dateAndTime = descriptions[1].trim().split("\\s", 2);

        if (dateAndTime.length < 2) {
            throw new LifelineException(ERROR_DEADLINE_INCORRECT_FORMAT);
        }

        try {
            LocalDate date = DateTimeParser.parseDate(dateAndTime[0].trim());
            LocalTime time = DateTimeParser.parseTime(dateAndTime[1].trim());
            return new Deadline(descriptions[0].trim(), date, time);
        } catch (DateTimeParseException e) {
            throw new LifelineException(ERROR_DEADLINE_INCORRECT_FORMAT);
        }
    }

    private static Event createEvent(String command) throws LifelineException {
        String[] details = splitCommands(command);
        String[] descriptions = details[1].trim().split("/at", 2);

        if (descriptions.length < 2) {
            throw new LifelineException(ERROR_EVENT_INCORRECT_FORMAT);
        }

        String[] eventDateAndDuration = descriptions[1].trim().split("\\s", 3);

        if (eventDateAndDuration.length < 3) {
            throw new LifelineException(ERROR_EVENT_INCORRECT_FORMAT);
        }

        String eventDate = eventDateAndDuration[0];
        String eventStartTime = eventDateAndDuration[1];
        String eventEndTime = eventDateAndDuration[2];

        try {
            LocalDate date = DateTimeParser.parseDate(eventDate.trim());
            LocalTime startTime = DateTimeParser.parseTime(eventStartTime.trim());
            LocalTime endTime = DateTimeParser.parseTime(eventEndTime.trim());

            if (startTime.isAfter(endTime)) {
                throw new LifelineException(ERROR_ENDTIME_AFTER_STARTTIME);
            }

            return new Event(descriptions[0].trim(), date, startTime, endTime);
        } catch (DateTimeParseException e) {
            throw new LifelineException(ERROR_EVENT_INCORRECT_FORMAT);
        }
    }
}
