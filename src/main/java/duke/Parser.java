package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ModifyTaskCommand;
import duke.command.QueryCommand;
import duke.command.UpdateTaskCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A class that deal with making sense of the user command.
 */
public class Parser {
    /**
     * Takes an array of strings as input and check if it makes sense. If it does, parse it into a {@code Task} object
     * and return it, and if it does not, throw a {@code DukeException}. The array make sense if and only if it is in
     * the format {@code [taskType, isDone, description, (optional) date]}:
     * <ul>
     *     <li>{@code taskType}: must be either {@code "T"}, {@code "D"} or {@code "E"};</li>
     *     <li>{@code isDone}: must be either {@code 0} or {@code 1};</li>
     *     <li>{@code description}: must be non-empty;</li>
     *     <li>{@code date}: Only needed when {@code taskType} is {@code "D"} or {@code "E"}. Must be in the format
     *     "YYYY-MM-DD".</li>
     * </ul>
     *
     * @param data The string array to be parsed.
     * @return A {@code Task} object parsed from the input.
     * @throws DukeException If the input data does not make sense.
     */
    public static Task arrayCommandToTask(String[] data) throws DukeException {
        boolean hasValidLength = data.length == 3 || data.length == 4;
        boolean hasValidDoneIndicator = data[1].equals("0") || data[1].equals("1");
        boolean hasDescription = !data[2].trim().isEmpty();
        if (!hasValidLength || !hasValidDoneIndicator || !hasDescription) {
            throw new InvalidCommandException();
        }
        String proposedType = data[0];
        boolean isDone = data[1].equals("1");
        String description = data[2];

        switch (proposedType) {
        case "T":
            if (data.length != 3) {
                throw new InvalidCommandException();
            }
            return new ToDo(description, isDone);
        case "D":
            if (data.length != 4) {
                throw new InvalidCommandException();
            }
            try {
                return new Deadline(description, isDone, LocalDate.parse(data[3]));
            } catch (DateTimeParseException e) {
                throw new DukeException("Please use the YYYY-MM-DD format for the time!");
            }
        case "E":
            if (data.length != 4) {
                throw new InvalidCommandException();
            }
            try {
                return new Event(description, isDone, LocalDate.parse(data[3]));
            } catch (DateTimeParseException e) {
                throw new DukeException("Please use the YYYY-MM-DD format for the time!");
            }
        default:
            assert false;
            throw new InvalidCommandException();
        }
    }

    /**
     * Transforms a description string to a {@code Task} object with the specified task type. If the description does
     * not meet the criteria:
     * <ul>
     *     <li>for type {@code TODO}: the description must be non-empty;</li>
     *     <li>for type {@code DEADLINE}: the description must be in the format {@code "(non-empty description) /by
     *     (YYYY-MM-DD)"};</li>
     *     <li>for type {@code DEADLINE}: the description must be in the format {@code "(non-empty description) /at
     *     (YYYY-MM-DD)"},</li>
     * </ul>
     * then a {@code DukeException} will be thrown.
     *
     * @param type        The task type you want to transform the description to.
     * @param description The necessary information to create a {@code Task} object.
     * @return A {@code Task} object parsed from the description.
     * @throws DukeException If the description does not make sense.
     */
    public static Task descriptionToTask(Task.TaskType type, String description) throws DukeException {
        Task task;
        switch (type) {
        case TODO: {
            if (description.trim().isEmpty()) {
                throw new InvalidCommandException();
            }
            task = new ToDo(description);
            break;
        }
        case DEADLINE: {
            if (!description.contains("/by ")) {
                throw new InvalidCommandException();
            }
            String[] information = description.split("/by ", 2);
            if (information[0].isEmpty()) {
                throw new InvalidCommandException();
            }
            try {
                task = new Deadline(information[0], LocalDate.parse(information[1]));
            } catch (DateTimeParseException e) {
                throw new DukeException("Please use the YYYY-MM-DD format for the time!");
            }
            break;
        }
        case EVENT: {
            if (!description.contains("/at ")) {
                throw new InvalidCommandException();
            }
            String[] information = description.split("/at ", 2);
            if (information[0].isEmpty()) {
                throw new InvalidCommandException();
            }
            try {
                task = new Event(information[0], LocalDate.parse(information[1]));
            } catch (DateTimeParseException e) {
                throw new DukeException("Please use the YYYY-MM-DD format for the time!");
            }
            break;
        }
        default:
            assert false;
            throw new InvalidCommandException();
        }
        return task;
    }

    /**
     * Parses the input command and return the corresponding {@code Command}. If the command is invalid, then raises a
     * {@code DukeException}.
     * <br>
     * The command can be in the following format. Each "()" means a non-empty input.
     * <ul>
     *     <li>{@code todo (description)}: add a new {@code ToDo} task;</li>
     *     <li>{@code deadline (description) /by (YYYY-MM-DD)}: add a new {@code Deadline} task;</li>
     *     <li>{@code event (description) /at (YYYY-MM-DD)}: add a new {@code Event} task;</li>
     *     <li>{@code done (item number)}: mark a certain task as done;</li>
     *     <li>{@code undone (item number)}: undo a certain task;</li>
     *     <li>{@code delete (item number)}: delete a certain task;</li>
     *     <li>{@code query (YYYY-MM-DD)}: query all tasks that will happen or due on a certain day;</li>
     *     <li>{@code find (keyword)}: query all tasks that contain the keyword;</li>
     *     <li>{@code list}: list all the undeleted tasks;</li>
     *     <li>{@code bye}: terminate the bot.</li>
     * </ul>
     *
     * @param command The command to perform certain action.
     * @throws DukeException When the command is invalid.
     */
    public static Command parseCommand(String command) throws DukeException {
        command = command.trim();
        if (command.contains(" ")) {
            String commandType = command.split(" ", 2)[0];
            String description = command.split(" ", 2)[1].trim();

            switch (commandType) {
            case "todo": {
                return new AddTaskCommand(Task.TaskType.TODO, description);
            }
            case "deadline": {
                return new AddTaskCommand(Task.TaskType.DEADLINE, description);
            }
            case "event": {
                return new AddTaskCommand(Task.TaskType.EVENT, description);
            }
            case "done": {
                return new ModifyTaskCommand(ModifyTaskCommand.CommandType.DONE, description);
            }
            case "undone": {
                return new ModifyTaskCommand(ModifyTaskCommand.CommandType.UNDONE, description);
            }
            case "delete": {
                return new ModifyTaskCommand(ModifyTaskCommand.CommandType.DELETE, description);
            }
            case "query": {
                return new QueryCommand(QueryCommand.CommandType.QUERY, description);
            }
            case "find": {
                return new QueryCommand(QueryCommand.CommandType.FIND, description);
            }
            case "update": {
                return new UpdateTaskCommand(description);
            }
            default: {
                assert false;
                throw new InvalidCommandException();
            }
            }
        }

        switch (command) {
        case "list":
            return new QueryCommand(QueryCommand.CommandType.LIST, "");
        case "bye":
            return new ExitCommand();
        default:
            throw new InvalidCommandException();
        }
    }
}
