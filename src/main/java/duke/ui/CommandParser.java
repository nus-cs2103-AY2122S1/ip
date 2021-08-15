package duke.ui;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.ToDoTask;

import java.util.StringTokenizer;

/**
 * Helper class to parse commands.
 *
 * @author Jay Aljelo Saez Ting
 */
public class CommandParser {
    private static final String EXIT_COMMAND = "bye";
    private static final String ADD_TODO_COMMAND = "todo";
    private static final String LIST_TASKS_COMMAND = "list";
    private static final String MARK_TASK_DONE_COMMAND = "done";
    private static final String ADD_DEADLINE_TASK_COMMAND = "deadline";
    private static final String ADD_EVENT_TASK_COMMAND = "event";
    private static final String DELETE_TASK_COMMAND = "delete";

    /**
     * Parses the commands to get the command name.
     *
     * @param command The command.
     * @return The type of command.
     * @throws DukeInvalidCommandException If the command is empty or the command does not exist.
     */
    public CommandType getCommandTypeFromCommand(String command) throws DukeInvalidCommandException {
        String[] tokens = command.strip().split(" ");
        if (tokens.length == 0 || tokens[0].length() == 0) {
            throw new DukeInvalidCommandException("This command is empty.");
        }
        String commandName = tokens[0];
        switch (commandName) {
        case EXIT_COMMAND:
            return CommandType.EXIT;
        case ADD_TODO_COMMAND:
            return CommandType.ADD_TODO_TASK;
        case LIST_TASKS_COMMAND:
            return CommandType.LIST_TASKS;
        case MARK_TASK_DONE_COMMAND:
            return CommandType.MARK_TASK_DONE;
        case ADD_DEADLINE_TASK_COMMAND:
            return CommandType.ADD_DEADLINE_TASK;
        case ADD_EVENT_TASK_COMMAND:
            return CommandType.ADD_EVENT_TASK;
        case DELETE_TASK_COMMAND:
            return CommandType.DELETE_TASK;
        default:
            throw new DukeInvalidCommandException("This command does not exist.");
        }
    }

    /**
     * Parses a "Mark Task Done" command to get the index of the task to be marked done in the list of tasks.
     *
     * @param command The "Mark Task Done" command.
     * @return The index of the task.
     * @throws DukeInvalidCommandException If the command is empty, not a "Mark Task Done" command, or malformed.
     */
    public int getTaskIndexOfTaskMarkedDone(String command) throws DukeInvalidCommandException {
        String[] tokens = command.strip().split(" ");
        if (tokens.length == 0 || tokens[0].length() == 0) {
            throw new DukeInvalidCommandException("This command is empty.");
        }
        String commandName = tokens[0];
        if (!commandName.equals(MARK_TASK_DONE_COMMAND)) {
            throw new DukeInvalidCommandException("This command is not a 'Mark Task Done' command.");
        }
        StringBuilder taskNumberSb = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            taskNumberSb.append(tokens[i]);
            if (taskNumberSb.length() > 0) {
                break;
            }
        }
        if (taskNumberSb.length() == 0) {
            throw new DukeInvalidCommandException("A task number is required for a 'Mark Task Done' command.");
        }
        try {
            return Integer.parseInt(taskNumberSb.toString()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeInvalidCommandException("The task number is not a number.");
        }
    }

    /**
     * Parses a "Delete Task" command to get the index of the task to be deleted in the list of tasks.
     *
     * @param command The "Delete Task" command.
     * @return The index of the task.
     * @throws DukeInvalidCommandException If the command is empty, not a "Delete Task" command, or malformed.
     */
    public int getTaskIndexOfTaskDeleted(String command) throws DukeInvalidCommandException {
        String[] tokens = command.strip().split(" ");
        if (tokens.length == 0 || tokens[0].length() == 0) {
            throw new DukeInvalidCommandException("This command is empty.");
        }
        String commandName = tokens[0];
        if (!commandName.equals(DELETE_TASK_COMMAND)) {
            throw new DukeInvalidCommandException("This command is not a 'Delete Task' command.");
        }
        StringBuilder taskNumberSb = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            taskNumberSb.append(tokens[i]);
            if (taskNumberSb.length() > 0) {
                break;
            }
        }
        if (taskNumberSb.length() == 0) {
            throw new DukeInvalidCommandException("A task number is required for a 'Delete Task' command.");
        }
        try {
            return Integer.parseInt(taskNumberSb.toString()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeInvalidCommandException("The task number is not a number.");
        }
    }

    /**
     * Parses an "Add To-do Task" command to get the to-do task.
     *
     * @param command The "Add To-do Task" command.
     * @return The to-do task.
     * @throws DukeInvalidCommandException If the command is empty, not an 'Add To-do Task' command, or malformed.
     */
    public ToDoTask getToDoTask(String command) throws DukeInvalidCommandException {
        String[] tokens = command.strip().split(" ");
        if (tokens.length == 0 || tokens[0].length() == 0) {
            throw new DukeInvalidCommandException("This command is empty.");
        }
        String commandName = tokens[0];
        if (!commandName.equals(ADD_TODO_COMMAND)) {
            throw new DukeInvalidCommandException("This command is not an 'Add To-do Task' command.");
        }
        StringBuilder taskDescriptionSb = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            taskDescriptionSb.append(tokens[i]).append(" ");
        }
        String taskDescription = taskDescriptionSb.toString().strip();
        if (taskDescription.length() == 0) {
            throw new DukeInvalidCommandException("A description is required for an 'Add To-do Task' command.");
        }
        return new ToDoTask(taskDescription);
    }

    /**
     * Parses an "Add Deadline Task" command to get the deadline task.
     *
     * @param command The "Add Deadline Task" command.
     * @return The deadline task.
     * @throws DukeInvalidCommandException If the command is empty, not an "Add Deadline Task" command, or malformed.
     */
    public DeadlineTask getDeadlineTask(String command) throws DukeInvalidCommandException {
        String[] tokens = command.strip().split(" ");
        if (tokens.length == 0 || tokens[0].length() == 0) {
            throw new DukeInvalidCommandException("This command is empty.");
        }
        String commandName = tokens[0];
        if (!commandName.equals(ADD_DEADLINE_TASK_COMMAND)) {
            throw new DukeInvalidCommandException("This command is not an 'Add Deadline Task' command.");
        }
        StringBuilder taskDescriptionSb = new StringBuilder();
        int timeStartIndex = tokens.length;
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("/by")) {
                timeStartIndex = i + 1;
                break;
            }
            taskDescriptionSb.append(token).append(" ");
        }
        StringBuilder deadlineSb = new StringBuilder();
        for (int i = timeStartIndex; i < tokens.length; i++) {
            deadlineSb.append(tokens[i]).append(" ");
        }
        String taskDescription = taskDescriptionSb.toString().strip();
        String deadline = deadlineSb.toString().strip();
        if (taskDescription.length() == 0) {
            throw new DukeInvalidCommandException("A description is required for an 'Add Deadline Task' command.");
        }
        if (deadline.length() == 0) {
            throw new DukeInvalidCommandException("A deadline is required for an 'Add Deadline Task' command.");
        }
        return new DeadlineTask(taskDescription, deadline);
    }

    /**
     * Parses an "Add Event Task" command to get the event task.
     *
     * @param command The "Add Event Task" command.
     * @return The event task.
     * @throws DukeInvalidCommandException If the command is empty, not an "Add Event Task" command, or malformed.
     */
    public EventTask getEventTask(String command) throws DukeInvalidCommandException {
        String[] tokens = command.strip().split(" ");
        if (tokens.length == 0 || tokens[0].length() == 0) {
            throw new DukeInvalidCommandException("This command is empty.");
        }
        String commandName = tokens[0];
        if (!commandName.equals(ADD_EVENT_TASK_COMMAND)) {
            throw new DukeInvalidCommandException("This command is not an 'Add Event Task' command.");
        }
        StringBuilder taskDescriptionSb = new StringBuilder();
        int timeStartIndex = tokens.length;
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("/at")) {
                timeStartIndex = i + 1;
                break;
            }
            taskDescriptionSb.append(token).append(" ");
        }
        StringBuilder timeSb = new StringBuilder();
        for (int i = timeStartIndex; i < tokens.length; i++) {
            timeSb.append(tokens[i]).append(" ");
        }
        String taskDescription = taskDescriptionSb.toString().strip();
        String time = timeSb.toString().strip();
        if (taskDescription.length() == 0) {
            throw new DukeInvalidCommandException("A description is required for an 'Add Event Task' command.");
        }
        if (time.length() == 0) {
            throw new DukeInvalidCommandException("An event time is required for an 'Add Event Task' command.");
        }
        return new EventTask(taskDescription, time);
    }
}
