package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Parser {

    private String parseAction(String command) {
        return command.split(" ")[0];
    }

    /**
     * Retrieves the action type of the command.
     *
     * @param command Instruction input by the user.
     * @return The action type of the command.
     */
    public ActionType parseActionType(String command) {
        String action = parseAction(command);
        switch (action) {
        case "list":
            return ActionType.LIST;
        case "done":
            return ActionType.DONE;
        case "deadline":
            return ActionType.DEADLINE;
        case "event":
            return ActionType.EVENT;
        case "todo":
            return ActionType.TODO;
        case "delete":
            return ActionType.DELETE;
        case "bye":
            return ActionType.BYE;
        case "find":
            return ActionType.FIND;
        default:
            return ActionType.UNRECOGNIZED;
        }
    }

    /**
     * Retrieves the task as a string from a command.
     *
     * @param command Instruction input by the user.
     * @return Task as s string.
     * @throws EmptyDescriptionException If the command description is empty.
     */
    public String parseTask(String command) throws EmptyDescriptionException {
        String action = this.parseAction(command);
        try {
            return command.substring(action.length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(action);
        }
    }

    /**
     * Validates a date.
     *
     * @param date Date in the format yyyy-mm-dd.
     * @throws InvalidDateTimeException If the date or its format is invalid.
     */
    private void validateDateTime(String date) throws InvalidDateTimeException {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Converts a date string to a LocalDate object.
     *
     * @param date A date as a string.
     * @return LocalDate.
     */
    public LocalDate stringToLocalDate(String date) throws InvalidDateTimeException {
        validateDateTime(date);
        String[] dateInfo = date.split("-");
        String year = dateInfo[0];
        String month = dateInfo[1];
        String day = dateInfo[2];
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    /**
     * Converts a task string to a Task object.
     *
     * @param taskString A task string.
     * @return A Task object.
     */
    public Task taskStringToTask(String taskString) {
        String[] taskInfo = taskString.split(" \\| ");
        String taskType = taskInfo[0];
        boolean isDone = taskInfo[1].equals("[X]");
        String taskDescription = taskInfo[2];
        switch (taskType) {
        case "[D]":
            return new DeadLine(taskDescription,
                    LocalDate.parse(taskInfo[3], DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)), isDone);
        case "[E]":
            return new Event(taskDescription,
                    LocalDate.parse(taskInfo[3], DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)), isDone);
        default:
            return new ToDo(taskDescription, isDone);
        }
    }

    /**
     * Retrieves the task index from a command.
     *
     * @param command  Instruction input by the user.
     * @param listSize Size of the task list.
     * @return Index of the task in the command.
     * @throws InvalidTaskIndexException If the task index is not a number.
     * @throws NoSuchTaskException       If the task index is out of range of the listSize.
     * @throws EmptyDescriptionException If the task index is empty.
     */
    public int getTaskIdx(String command, int listSize)
            throws InvalidTaskIndexException, NoSuchTaskException, EmptyDescriptionException {
        String action = parseAction(command);
        String idxString = parseTask(command);
        int taskIdx;
        try {
            taskIdx = Integer.parseInt(idxString);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException(action);
        }
        if (taskIdx > 0 && taskIdx <= listSize) {
            return taskIdx;
        } else {
            throw new NoSuchTaskException();
        }
    }

}
