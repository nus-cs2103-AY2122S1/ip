package duke;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import duke.command.AddCommand;
import duke.command.DeleteAllCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ICommand;
import duke.command.ListCommand;
import duke.task.Task;

/**
 * Provides methods to parse user command and saved contents.
 */
public class Parser {
    // Constant words
    protected static final String WORD_EXIT = "bye";
    protected static final String WORD_LIST = "list";
    protected static final String WORD_DELETE_ALL = "deleteall";
    protected static final String WORD_DONE = "done";
    protected static final String WORD_DELETE = "delete";
    protected static final String WORD_FIND = "find";

    protected static final String WORD_TODO = "todo";
    protected static final String WORD_DEADLINE = "deadline";
    protected static final String WORD_DEADLINE_BY = "/by";
    protected static final String WORD_EVENT = "event";
    protected static final String WORD_EVENT_AT = "/at";

    protected static final String DIVIDER_WORD = " \\| ";


    /**
     * Convert user input to {@link duke.command.ICommand ICommand}.
     *
     * @param s user command
     * @param listSize size of current task list
     * @return command for duke to execute
     * @throws DukeException if user command is missing operand or invalid
     */
    public static ICommand parse(String s, int listSize) throws DukeException {
        requireNonNull(s);
        if (listSize < 0) {
            throw new IllegalArgumentException("list size cannot be" + listSize);
        }

        s = s.strip();
        String[] strArr = s.split(" ", 2);
        if (strArr.length == 1) {
            switch (strArr[0]) {
            case WORD_EXIT:
                return new ExitCommand();
            case WORD_LIST:
                return new ListCommand();
            case WORD_DELETE_ALL:
                return new DeleteAllCommand();
            case WORD_DONE:
            case WORD_DELETE:
            case WORD_FIND:
            case WORD_TODO:
            case WORD_DEADLINE:
            case WORD_EVENT:
                throw new DukeException(ExceptionType.MISSING_OPERAND);
            default:
                throw new DukeException(ExceptionType.INVALID_COMMAND);
            }
        } else {
            switch (strArr[0]) {
            case WORD_DONE:
                return new DoneCommand(tryParseDoneOrDelete(strArr[1], listSize));
            case WORD_DELETE:
                return new DeleteCommand(tryParseDoneOrDelete(strArr[1], listSize));
            case WORD_FIND:
                return new FindCommand(strArr[1]);
            case WORD_TODO:
                return new AddCommand(strArr);
            case WORD_DEADLINE:
                return new AddCommand(tryParseDeadline(strArr[1]));
            case WORD_EVENT:
                return new AddCommand(tryParseEvent(strArr[1]));
            default:
                throw new DukeException(ExceptionType.INVALID_COMMAND);
            }
        }
    }

    /**
     * Parses mark_done/delete command to retrieve line index.
     *
     * @param s mark_done/delete command
     * @param listSize size of current list
     * @return Line index in save file to mark as done/delete.
     * @throws DukeException If command is lacking a number or line index is out of bound.
     */
    private static int tryParseDoneOrDelete(String s, int listSize) throws DukeException {
        assert s != null : "input string is null";

        try {
            int taskIndex = Integer.parseInt(s) - 1;
            if (taskIndex >= 0 && taskIndex < listSize) {
                return taskIndex;
            } else {
                throw new DukeException(ExceptionType.INDEX_OUT_OF_BOUND);
            }
        } catch (NumberFormatException e) {
            throw new DukeException(ExceptionType.INVALID_OPERAND);
        }
    }

    /**
     * Parses deadline command to description and due time.
     *
     * @param s deadline command
     * @return An string array of length 2, with the first element being description and the second being due time.
     * @throws DukeException If command is missing operand (description, due time) or missing keyword (" /by ")
     */
    private static String[] tryParseDeadline(String s) throws DukeException {
        assert s != null : "input string is null";

        s = s.strip();
        List<String> strList = Arrays.asList(s.split(" "));
        if (strList.contains(WORD_DEADLINE_BY)) {
            int i = strList.indexOf(WORD_DEADLINE_BY);
            if (i == 0 || i == strList.size() - 1) {
                throw new DukeException(ExceptionType.MISSING_OPERAND);
            }
            return new String[]{"deadline",
                    s.split(WORD_DEADLINE_BY, 2)[0].strip(),
                    s.split(WORD_DEADLINE_BY, 2)[1].strip(),
            };
        } else {
            throw new DukeException(ExceptionType.DDL_MISSING_KEYWORD);
        }
    }

    /**
     * Parses event command to description and time period.
     *
     * @param s event command
     * @return An string array of length 3, with the second element being description and the third being time period
     * @throws DukeException If command is missing operand (description, time period) or missing keyword (" /by ")
     */
    private static String[] tryParseEvent(String s) throws DukeException {
        assert s != null : "input string is null";
        s = s.strip();
        List<String> strList = Arrays.asList(s.split(" "));
        if (strList.contains(WORD_EVENT_AT)) {
            int i = strList.indexOf(WORD_EVENT_AT);
            if (i == 0 || i == strList.size() - 1) {
                throw new DukeException(ExceptionType.MISSING_OPERAND);
            }
            return new String[]{"event",
                    s.split(WORD_EVENT_AT, 2)[0].strip(),
                    s.split(WORD_EVENT_AT, 2)[1].strip(),
            };
        } else {
            throw new DukeException(ExceptionType.EVENT_MISSING_KEYWORD);
        }
    }

    /**
     * Converts save data string to task.
     *
     * @param s save data string
     * @return a new task constructed based on the given string
     * @throws DukeException if the string is not aligned with save data format
     */
    public static Task fileContentsToTask(String s) throws DukeException {
        requireNonNull(s, "String to write cannot be null");

        String[] arr = s.split(DIVIDER_WORD, 4);
        try {
            int i = Integer.parseInt(arr[1]);
            if (arr.length != 4 || i < 0 || i > 1) {
                throw new DukeException(ExceptionType.FAIL_TO_WRITE, "Unknown symbol in save file.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(ExceptionType.FAIL_TO_WRITE, "Unknown symbol in save file.");
        }

        boolean isDone = Integer.parseInt(arr[1]) == 1;
        switch (arr[0]) {
        case "T":
            return Task.getToDo(arr[2], isDone);
        case "D":
            return Task.getDeadline(arr[2], arr[3], isDone);
        case "E":
            return Task.getEvent(arr[2], arr[3], isDone);
        default:
            throw new DukeException(ExceptionType.FAIL_TO_WRITE, "Unknown symbol in save file.");
        }
    }
}
