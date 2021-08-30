package lifeline.command;

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
 * The class CommandHandler handles all commands supplied by the user
 */
public class CommandHandler {

    /**
     * Prints out given TaskList using the given Ui
     *
     * @param command User input
     * @param storage Storage to save or load tasks
     * @param taskList List of tasks
     * @param ui Ui to display information to user
     * @return String to represent all tasks
     */
    public static String handleList(String command, Storage storage, TaskList taskList, Ui ui) {
        return ui.showTaskList(taskList);
    }

    /**
     * Displays goodbye message to the user when user exits the program
     *
     * @param command User input
     * @param storage Storage to save or load tasks
     * @param taskList List of tasks
     * @param ui Ui to display information to user
     * @return Goodbye message
     */
    public static String handleBye(String command, Storage storage, TaskList taskList, Ui ui) {
        return ui.exit();
    }

    public static String handleHelp(String command, Storage storage, TaskList taskList, Ui ui) {
        return ui.showHelpMessage();
    }

    /**
     * Creates and saves an instance of a Deadline. Instance is added to TaskList
     *
     * @param command User input
     * @param storage Storage to save or load tasks
     * @param taskList List of tasks
     * @param ui Ui to display information to user
     * @return Message to notify user that the deadline has been created
     * @throws LifelineException if command is incomplete or not of the correct format or tasklist is not able to be
     *                           saved
     */
    public static String handleDeadline(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        String[] commands = getCommands(command);

        // Get Deadline name and details
        String[] description = commands[1].split("/by", 2);
        if (description.length != 2) {
            throw new LifelineException("Deadline is not of the correct format! Please use deadline <name> /by "
                    + "<dd/MM/yy HHmm>");
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
            throw new LifelineException("Deadline is not of the correct format! Please use deadline <name> /by "
                    + "<dd/MM/yy HHmm>");
        }
    }

    /**
     * Creates and saves an instance of an Event. Instance is added to TaskList
     *
     * @param command User input
     * @param storage Storage to save or load tasks
     * @param taskList List of tasks
     * @param ui Ui to display information to user
     * @return Message to notify user that the event has been created
     * @throws LifelineException if command is incomplete or not of the correct format or tasklist is not able to be
     *                           saved
     */
    public static String handleEvent(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        String errorMessage = "Event date/time not in proper format! Please use event <name> /at "
                + "<dd/MM/yy> <HHmm>-<HHmm>";
        String[] commands = getCommands(command);

        // Get event name and details
        String[] descriptions = commands[1].trim().split("/at", 2);
        if (descriptions.length != 2) {
            throw new LifelineException(errorMessage);
        }
        String eventName = descriptions[0].trim();

        // Get event date and duration
        String[] eventDateAndDuration = descriptions[1].trim().split("\\s", 2);
        if (eventDateAndDuration.length != 2) {
            throw new LifelineException(errorMessage);
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
                throw new LifelineException(errorMessage);
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
            throw new LifelineException(errorMessage);
        }
    }

    /**
     * Finds tasks in taskList with given keyword
     *
     * @param command User input
     * @param storage Storage to save or load tasks
     * @param taskList List of tasks
     * @param ui Ui to display information to user
     * @return All tasks whose name contains the keyword
     * @throws LifelineException if user does not specify a keyword or no tasks are found
     */
    public static String handleFind(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        String[] commands = getCommands(command);
        TaskList foundTasks = taskList.findTasks(commands[1].toLowerCase());
        return ui.showFoundTasks(foundTasks, commands[1]);
    }

    /**
     * Creates and saves an instance of ToDo. Instance is added to TaskList
     *
     * @param command User input
     * @param storage Storage to save or load tasks
     * @param taskList List of tasks
     * @param ui Ui to display information to user
     * @return Message to notify user that the todo has been created
     * @throws LifelineException if command is incomplete or not of the correct format or tasklist is not able to be
     *                           saved
     */
    public static String handleToDo(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        String[] commands = getCommands(command);
        Task newTask = new ToDo(commands[1].trim());
        taskList.add(newTask);
        storage.save(taskList);
        return ui.showAddedTask(newTask);
    }

    /**
     * Marks a task at specified index as completed
     *
     * @param command User input
     * @param storage Storage to save or load tasks
     * @param taskList List of tasks
     * @param ui Ui to display information to user
     * @return Message to notify user that the task has been marked as completed
     * @throws LifelineException if command is incomplete or the index given is out of bounds or not an integer or
     *                           taskList is not able to be saved
     */
    public static String handleDone(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        String[] commands = getCommands(command);
        int taskIndex = convertIndexToInt(commands[0], commands[1], taskList);
        taskList.completeTask(taskIndex);
        storage.save(taskList);
        return ui.showCompletedTask(taskList.get(taskIndex));
    }

    /**
     * Deletes a task at specified index
     *
     * @param command User input
     * @param storage Storage to save or load tasks
     * @param taskList List of tasks
     * @param ui Ui to display information to user
     * @return Message to the user that the task has been deleted
     * @throws LifelineException if command is incomplete or the index given is out of bounds or not an integer or
     *                           taskList is not able to be saved
     */
    public static String handleDelete(String command, Storage storage, TaskList taskList, Ui ui)
            throws LifelineException {
        String[] commands = getCommands(command);
        int taskIndex = convertIndexToInt(commands[0], commands[1], taskList);
        Task taskToDelete = taskList.get(taskIndex);
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
            throw new LifelineException("You did not specify an integer! Please use done <number>");
        case "delete":
            throw new LifelineException("You did not specify an integer! Please use delete <number>");
        case "todo":
            throw new LifelineException("Details of todo cannot be blank!");
        case "deadline":
            throw new LifelineException("Details of deadline cannot be blank!");
        case "event":
            throw new LifelineException("Details of event cannot be blank!");
        case "find":
            throw new LifelineException("Keyword was not provided! Please use find <keyword>");
        default:
            throw new LifelineException("I am sorry! I don't know what that means! \u2639");
        }

    }

    private static int convertIndexToInt(String command, String index, TaskList taskList) throws LifelineException {
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex >= taskList.size() || taskIndex < 0) {
                throw new LifelineException("Index is out of bounds!");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new LifelineException("Index is not an integer! Please use " + command + " <number>");
        }
    }
}
