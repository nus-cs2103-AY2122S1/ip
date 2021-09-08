package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.IncompleteDescriptionException;
import duke.exception.InvalidDateFormatException;
import duke.exception.MissingArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class that handles task-addition command.
 */
public class AddCommand extends Command {

    private enum TaskType {
        TODO, EVENT, DEADLINE
    }

    private static final String errorMessage = "Task description is not found!";

    private final boolean hasTaskDate;
    private final TaskType taskType;
    private final String taskTag;
    private final String taskDescription;
    private final LocalDate taskDate;

    /**
     * Constructs an AddCommand instance that handles the logic of task-addition.
     *
     * @param taskType The type of the to-be-added task.
     * @param taskFullDetail The description of the to-be-added task.
     * @throws IncompleteDescriptionException The exception for handling command with incomplete description.
     * @throws InvalidDateFormatException The exception for handling command with invalid date format.
     */
    public AddCommand(String taskType, String taskTag, String taskFullDetail) throws IncompleteDescriptionException,
            InvalidDateFormatException, MissingArgumentException {
        this.taskType = getTaskType(taskType);
        this.taskTag = taskTag;
        this.hasTaskDate = checksHasTaskDate(taskType);

        String[] taskDetails = splitDetail(taskFullDetail);
        this.taskDescription = getTaskDescription(taskDetails);
        this.taskDate = getTaskDate(taskDetails);
    }

    /** The following methods are the method used in constructing an AddCommand instance. */
    // Returns the enumeration of task type string.
    private TaskType getTaskType(String taskTypeString) {
        return TaskType.valueOf(taskTypeString.toUpperCase());
    }

    // Checks whether the task has Date
    private boolean checksHasTaskDate(String taskType) {
        return !taskType.equals("todo");
    }

    // Returns an array consisting task details and throws exception if fullDetail cannot be split.
    private String[] splitDetail(String fullDetail) throws MissingArgumentException {
        String delimiter = getDelimiter();
        // Splits the full detail into 2 if the task type is not a todo.
        String[] details = fullDetail.split(delimiter, taskType.equals(TaskType.TODO) ? 1 : 2);
        identifyMissingArgument(details);

        return details;
    }

    // Returns task description if it is not empty, else, throws exception.
    private String getTaskDescription(String[] taskDetails) throws IncompleteDescriptionException {
        String description = taskDetails[0];
        identifyAllWhiteSpace(description);

        return description;
    }

    // Returns task date if it is not empty/invalid, else, throws exception.
    private LocalDate getTaskDate(String[] taskDetails) throws IncompleteDescriptionException,
            InvalidDateFormatException {
        LocalDate date = null;
        if (hasTaskDate) {
            String taskDateString = taskDetails[1];
            identifyAllWhiteSpace(taskDateString);
            identifyInvalidDateFormat(taskDateString);
            date = getLocalDate(taskDateString);
        }

        return date;
    }

    /** The following methods are getter helper function. */
    // Returns the regex used to split the command.
    private String getDelimiter() {
        return taskType.equals(TaskType.EVENT)
                ? " /at "
                : taskType.equals(TaskType.DEADLINE)
                ? " /by "
                : "";
    }

    // Returns hint for the possibly missing argument in the command.
    private static String getHint(TaskType taskType) {
        switch (taskType) {
        case TODO:
            return "some description (eg. borrow book)";
        case EVENT:
            return " /at ";
        case DEADLINE:
            return " /by ";
        default:
            return "miss any argument";
        }
    }

    // Parses date string to LocalDate instance, then, returns it.
    private LocalDate getLocalDate(String dateString) throws InvalidDateFormatException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(e.getMessage());
        }
    }

    /** The followings are methods that identify if exceptions should be thrown. */
    // Throws exception if detail array has missing argument(s).
    private void identifyMissingArgument(String[] details) throws MissingArgumentException {
        boolean isShortDescription = details.length < 2;
        if (hasTaskDate && isShortDescription) {
            String taskTypeString = taskType.toString().toLowerCase();
            String hint = getHint(taskType);
            throw new MissingArgumentException(taskTypeString, hint);
        }
    }

    // Throws IncompleteDescriptionException when the task description or the task date consists purely whitespace(s).
    private void identifyAllWhiteSpace(String detail) throws IncompleteDescriptionException {
        boolean isAllWhiteSpace = detail.isBlank();
        if (isAllWhiteSpace) {
            throw new IncompleteDescriptionException(errorMessage);
        }
    }

    // Throws InvalidDateFormatException when the task date specified has invalid format.
    private void identifyInvalidDateFormat(String dateString) throws InvalidDateFormatException {
        boolean isInvalidDateFormat = !dateString.matches("\\d{4}-\\d{2}-\\d{2}");
        String invalidDateFormatMessage = "Please specify the date in yyyy-mm-dd format!";
        if (isInvalidDateFormat) {
            throw new InvalidDateFormatException(invalidDateFormatMessage);
        }
    }

    /** The followings are methods used in 'execute(...)' method. */
    // Returns a task instance according to the type, description and date, if any.
    private Task createTask() {
        switch (taskType) {
        case TODO:
            return new Todo(taskTag, taskDescription);
        case EVENT:
            return new Event(taskTag, taskDescription, taskDate);
        case DEADLINE:
            return new Deadline(taskTag, taskDescription, taskDate);
        default:
            return null;
        }
    }

    // Returns a response telling the user that the task has been successfully added and stored.
    private String createResponse(TaskList tasks, Task task) {
        String prefix = "Got it. I've added this task:\n ";
        int taskNum = tasks.getTaskNum();
        String summary = "\nNow you have " + taskNum + " tasks in the list.";

        return String.format("%s%s", prefix + task, summary);
    }

    /**
     * Returns the response after executing the task-addition command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = createTask();
            tasks.add(task);
            storage.save(tasks);

            return createResponse(tasks, task);
        } catch (DateTimeParseException e) {
            return new Ui().showError(e.getMessage());
        }
    }

    /**
     * Returns the boolean false since it is not a command that exits the program.
     *
     * @return The boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
