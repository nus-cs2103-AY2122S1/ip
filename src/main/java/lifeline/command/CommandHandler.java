package lifeline.command;

import static lifeline.util.ErrorString.ERROR_DEADLINE_INCORRECT_FORMAT;
import static lifeline.util.ErrorString.ERROR_DEADLINE_MISSING_DETAILS;
import static lifeline.util.ErrorString.ERROR_DELETE_MISSING_INDEX;
import static lifeline.util.ErrorString.ERROR_DONE_MISSING_INDEX;
import static lifeline.util.ErrorString.ERROR_EVENT_INCORRECT_FORMAT;
import static lifeline.util.ErrorString.ERROR_EVENT_MISSING_DETAILS;
import static lifeline.util.ErrorString.ERROR_FIND_MISSING_KEYWORD;
import static lifeline.util.ErrorString.ERROR_INDEX_OUT_OF_BOUNDS;
import static lifeline.util.ErrorString.ERROR_INVALID_COMMAND;
import static lifeline.util.ErrorString.ERROR_NON_INTEGER_INDEX;
import static lifeline.util.ErrorString.ERROR_TODO_MISSING_DETAILS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import lifeline.exception.LifelineException;
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
        assert Command.LIST.hasCommand(command);
        return ui.showTaskList(taskList);
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
        assert Command.BYE.hasCommand(command);
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
        assert command.equals("help");
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
        String[] commands = getCommands(command);
        assert Command.DEADLINE.hasCommand(commands[0]);
        // Get Deadline name and details
        String[] description = commands[1].split("/by", 2);
        if (description.length != 2) {
            throw new LifelineException(ERROR_DEADLINE_INCORRECT_FORMAT);
        }
        try {
            // Parse date from user input
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(description[1].trim(), formatter);

            // Create new task
            Task newTask = new Deadline(description[0].trim(), dateTime);
            taskList.add(newTask);
            storage.save(taskList);
            return ui.showAddedTask(newTask);
        } catch (DateTimeParseException e) {
            throw new LifelineException(ERROR_DEADLINE_INCORRECT_FORMAT);
        }
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
        String[] commands = getCommands(command);
        assert Command.EVENT.hasCommand(commands[0]);

        // Get event name and details
        String[] descriptions = commands[1].trim().split("/at", 2);
        if (descriptions.length != 2) {
            throw new LifelineException(ERROR_EVENT_INCORRECT_FORMAT);
        }
        String eventName = descriptions[0].trim();

        // Get event date and duration
        String[] eventDateAndDuration = descriptions[1].trim().split("\\s", 2);
        if (eventDateAndDuration.length != 2) {
            throw new LifelineException(ERROR_EVENT_INCORRECT_FORMAT);
        }
        String eventDate = eventDateAndDuration[0];
        String eventDuration = eventDateAndDuration[1];

        try {
            // Create date pattern
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

            // Parse input date as LocalDate object
            LocalDate date = LocalDate.parse(eventDate.trim(), dateFormatter);

            // Get start time and endtime of event
            String[] duration = eventDuration.split("-", 2);
            if (duration.length != 2) {
                throw new LifelineException(ERROR_EVENT_INCORRECT_FORMAT);
            }

            // Create time pattern
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

            // Parse start time and end time as LocalTime Object
            LocalTime startTime = LocalTime.parse(duration[0], timeFormatter);
            LocalTime endTime = LocalTime.parse(duration[1], timeFormatter);

            // Create new Task and add to tasklist
            Task newTask = new Event(eventName, date, startTime, endTime);
            taskList.add(newTask);
            storage.save(taskList);
            return ui.showAddedTask(newTask);
        } catch (DateTimeParseException e) {
            throw new LifelineException(ERROR_EVENT_INCORRECT_FORMAT);
        }
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
        String[] commands = getCommands(command);
        assert Command.FIND.hasCommand(commands[0]);
        TaskList foundTasks = taskList.findTasks(commands[1].toLowerCase());
        return ui.showFoundTasks(foundTasks, commands[1]);
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
        String[] commands = getCommands(command);
        assert Command.TODO.hasCommand(commands[0]);
        Task newTask = new ToDo(commands[1].trim());
        taskList.add(newTask);
        storage.save(taskList);
        return ui.showAddedTask(newTask);
    }

    /**
     * Marks a task at specified index as completed.
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
        String[] commands = getCommands(command);
        assert Command.DONE.hasCommand(commands[0]);
        int taskIndex = convertIndexToInt(commands[0], commands[1], taskList);
        taskList.completeTask(taskIndex);
        storage.save(taskList);
        return ui.showCompletedTask(taskList.getTask(taskIndex));
    }

    /**
     * Deletes a task at specified index.
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
        String[] commands = getCommands(command);
        assert Command.DELETE.hasCommand(commands[0]);
        int taskIndex = convertIndexToInt(commands[0], commands[1], taskList);
        Task taskToDelete = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);
        storage.save(taskList);
        return ui.showDeletedTask(taskToDelete);
    }

    private static String[] getCommands(String command) throws LifelineException {
        String[] commands = command.split("\\s", 2);
        if (commands.length < 2) {
            handleIncompleteCommand(commands[0]);
        }
        return commands;
    }

    private static void handleIncompleteCommand(String command) throws LifelineException {
        switch (command) {
        case "done":
            throw new LifelineException(ERROR_DONE_MISSING_INDEX);
        case "delete":
            throw new LifelineException(ERROR_DELETE_MISSING_INDEX);
        case "todo":
            throw new LifelineException(ERROR_TODO_MISSING_DETAILS);
        case "deadline":
            throw new LifelineException(ERROR_DEADLINE_MISSING_DETAILS);
        case "event":
            throw new LifelineException(ERROR_EVENT_MISSING_DETAILS);
        case "find":
            throw new LifelineException(ERROR_FIND_MISSING_KEYWORD);
        default:
            throw new LifelineException(ERROR_INVALID_COMMAND);
        }

    }

    private static int convertIndexToInt(String command, String index, TaskList taskList) throws LifelineException {
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex >= taskList.getSize() || taskIndex < 0) {
                throw new LifelineException(ERROR_INDEX_OUT_OF_BOUNDS);
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new LifelineException(ERROR_NON_INTEGER_INDEX);
        }
    }
}
