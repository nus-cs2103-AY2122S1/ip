package duke;

import duke.exception.DukeException;
import duke.exception.IndexMismatchException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
        if (data.length < 3
                || !(data[1].equals("0") || data[1].equals("1"))
                || data[2].trim().isEmpty()) {
            throw new InvalidCommandException();
        }
        String proposedType = data[0];
        boolean isDone = data[1].equals("1");
        String description = data[2];

        switch (proposedType) {
        case "T":
            if (data.length == 3) {
                return new ToDo(description, isDone);
            } else {
                throw new InvalidCommandException();
            }
        case "D":
            if (data.length != 4) {
                throw new InvalidCommandException();
            }
            try {
                return new Deadline(description, isDone, LocalDate.parse(data[3]));
            } catch (DateTimeParseException e) {
                throw new DukeException("\tPlease use the YYYY-MM-DD format for the time!");
            }
        case "E":
            if (data.length != 4) {
                throw new InvalidCommandException();
            }
            try {
                return new Event(description, isDone, LocalDate.parse(data[3]));
            } catch (DateTimeParseException e) {
                throw new DukeException("\tPlease use the YYYY-MM-DD format for the time!");
            }
        default:
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
            if (!description.trim().isEmpty()) {
                task = new ToDo(description);
            } else {
                throw new InvalidCommandException();
            }
            break;
        }
        case DEADLINE: {
            if (description.contains("/by")) {
                String[] information = description.split("/by ", 2);
                if (information[0].isEmpty()) {
                    throw new InvalidCommandException();
                }
                try {
                    task = new Deadline(information[0], LocalDate.parse(information[1]));
                } catch (DateTimeParseException e) {
                    throw new DukeException("\tPlease use the YYYY-MM-DD format for the time!");
                }
            } else {
                throw new InvalidCommandException();
            }
            break;
        }
        case EVENT: {
            if (description.contains("/at")) {
                String[] information = description.split("/at ", 2);
                if (information[0].isEmpty()) {
                    throw new InvalidCommandException();
                }
                try {
                    task = new Deadline(information[0], LocalDate.parse(information[1]));
                } catch (DateTimeParseException e) {
                    throw new DukeException("\tPlease use the YYYY-MM-DD format for the time!");
                }
            } else {
                throw new InvalidCommandException();
            }
            break;
        }
        default:
            throw new InvalidCommandException();
        }
        return task;
    }

    /**
     * Parses the input command and perform actions to the specified {@code taskList} and {@code storage} according to
     * the result. If the command is invalid, then raises a {@code DukeException}.
     * <br>
     * The command can be in the following format. Each "()" means a non-empty input.
     * <ul>
     *     <li>{@code todo (description)}: add a new {@code ToDo} task;</li>
     *     <li>{@code deadline (description) /by (YYYY-MM-DD)}: add a new {@code Deadline} task;</li>
     *     <li>{@code event (description) /at (YYYY-MM-DD)}: add a new {@code Event} task;</li>
     *     <li>{@code done (item number)}: mark a certain task as done;</li>
     *     <li>{@code delete (item number)}: delete a certain task;</li>
     *     <li>{@code query (YYYY-MM-DD)}: query all tasks that will happen or due on a certain day;</li>
     *     <li>{@code list}: list all the undeleted tasks;</li>
     *     <li>{@code bye}: terminate the bot.</li>
     * </ul>
     *
     * @param command  The command to perform certain action.
     * @param taskList The task list to store the tasks.
     * @param storage  The file to store the tasks.
     * @throws DukeException When the command is invalid.
     */
    public static void parseCommand(String command, TaskList taskList, Storage storage) throws DukeException {
        command = command.trim();
        if (command.contains(" ")) {
            String commandType = command.split(" ", 2)[0];
            String description = command.split(" ", 2)[1].trim();

            switch (commandType) {
            case "todo": {
                Task task = descriptionToTask(Task.TaskType.TODO, description);
                taskList.addTask(task);
                storage.addTask(task);
                return;
            }
            case "deadline": {
                Task task = descriptionToTask(Task.TaskType.DEADLINE, description);
                taskList.addTask(task);
                storage.addTask(task);
                return;
            }
            case "event": {
                Task task = descriptionToTask(Task.TaskType.EVENT, description);
                taskList.addTask(task);
                storage.addTask(task);
                return;
            }
            case "done": {
                if (description.matches("\\d+")) {
                    int item = Integer.parseInt(description);
                    taskList.completeTask(item);
                    storage.refreshTask(taskList);
                } else {
                    throw new IndexMismatchException();
                }
                return;
            }
            case "delete": {
                if (description.matches("\\d+")) {
                    int item = Integer.parseInt(description);
                    taskList.removeTask(item);
                    storage.refreshTask(taskList);
                } else {
                    throw new IndexMismatchException();
                }
                return;
            }
            case "query": {
                try {
                    Ui.printMessage(taskList.printList(LocalDate.parse(description)));
                } catch (DateTimeParseException e) {
                    throw new InvalidCommandException();
                }
                return;
            }
            case "find": {
                Ui.printMessage(taskList.printList(description));
                return;
            }
            default: {
                throw new InvalidCommandException();
            }
            }
        }

        switch (command) {
        case "list":
            Ui.printMessage(taskList.printList());
            break;
        case "bye":
            Ui.farewell();
            break;
        default:
            throw new InvalidCommandException();
        }
    }
}
