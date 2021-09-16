package duke;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteAllCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
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
     * Convert user input to {@link duke.command.Command ICommand}.
     *
     * @param userInput user command
     * @param listSize size of current task list
     * @return command for duke to execute
     * @throws DukeException if user command is missing operand or invalid
     */
    public static Command parse(String userInput, int listSize) throws DukeException {
        requireNonNull(userInput);
        if (listSize < 0) {
            throw new IllegalArgumentException("list size cannot be" + listSize);
        }

        String s = userInput.strip();
        String[] words = s.split(" ", 2);
        if (words.length == 1) {
            switch (words[0]) {
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
            switch (words[0]) {
            case WORD_DONE:
                return new DoneCommand(tryParseDoneOrDelete(words[1], listSize));
            case WORD_DELETE:
                return new DeleteCommand(tryParseDoneOrDelete(words[1], listSize));
            case WORD_FIND:
                return new FindCommand(words[1]);
            case WORD_TODO:
                return new AddCommand(words);
            case WORD_DEADLINE:
                return new AddCommand(tryParseDeadline(words[1]));
            case WORD_EVENT:
                return new AddCommand(tryParseEvent(words[1]));
            default:
                throw new DukeException(ExceptionType.INVALID_COMMAND);
            }
        }
    }

    /**
     * Parses mark_done/delete command to retrieve line index.
     *
     * @param indexStr index string
     * @param listSize size of current list
     * @return Line index in save file to mark as done/delete.
     * @throws DukeException If command is lacking a number or line index is out of bound.
     */
    private static int tryParseDoneOrDelete(String indexStr, int listSize) throws DukeException {
        assert indexStr != null : "index of done/delete is null";

        try {
            int taskIndex = Integer.parseInt(indexStr) - 1;
            Objects.checkIndex(taskIndex, listSize);

            return taskIndex;
        } catch (NumberFormatException e) {
            throw new DukeException(ExceptionType.INVALID_OPERAND);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ExceptionType.INDEX_OUT_OF_BOUND);
        }
    }

    /**
     * Parses deadline operand string to description and due time.
     *
     * @param operand operand string in deadline command
     * @return An string array of length 2, with the first element being description and the second being due time.
     * @throws DukeException If command is missing operand (description, due time) or missing keyword (" /by ")
     */
    private static String[] tryParseDeadline(String operand) throws DukeException {
        assert operand != null : "input operand string is null";

        String s = operand.strip();
        List<String> words = Arrays.asList(s.split(" "));
        int i = words.indexOf(WORD_DEADLINE_BY);
        boolean hasKeyWord = i > 0 && i < words.size() - 1;

        if (!hasKeyWord) {
            throw new DukeException(ExceptionType.MISSING_OPERAND);
        } else {
            return new String[]{"deadline",
                    s.split(WORD_DEADLINE_BY, 2)[0].strip(),
                    s.split(WORD_DEADLINE_BY, 2)[1].strip()
            };
        }
    }

    /**
     * Parses event operand string to description and time period.
     *
     * @param operand operand string in event command
     * @return An string array of length 3, with the second element being description and the third being time period
     * @throws DukeException If command is missing operand (description, time period) or missing keyword (" /by ")
     */
    private static String[] tryParseEvent(String operand) throws DukeException {
        assert operand != null : "input operand string is null";

        String s = operand.strip();
        List<String> words = Arrays.asList(s.split(" "));
        int i = words.indexOf(WORD_EVENT_AT);
        boolean hasKeyWord = i > 0 && i < words.size() - 1;

        if (!hasKeyWord) {
            throw new DukeException(ExceptionType.MISSING_OPERAND);
        } else {
            return new String[]{"event",
                    s.split(WORD_EVENT_AT, 2)[0].strip(),
                    s.split(WORD_EVENT_AT, 2)[1].strip()
            };
        }
    }

    /**
     * Converts save data string to task.
     *
     * @param lineString string of a line in save file
     * @return a new task constructed based on the given string
     * @throws DukeException if the string is not aligned with save data format
     */
    public static Task fileContentsToTask(String lineString) throws DukeException {
        requireNonNull(lineString, "string of line is null");

        String[] words = lineString.split(DIVIDER_WORD, 4);
        try {
            int isDoneIndex = Integer.parseInt(words[1]);

            boolean isValidIndex = isDoneIndex == 0 || isDoneIndex == 1;
            boolean isValidLine = words.length == Task.getNumOfSaveParams();
            if (!(isValidIndex && isValidLine)) {
                throw new DukeException(ExceptionType.FAIL_TO_WRITE, "Unknown symbol in save file.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(ExceptionType.FAIL_TO_WRITE, "Unknown symbol in save file.");
        }

        boolean isDone = Integer.parseInt(words[1]) == 1;
        switch (words[0]) {
        case "T":
            return Task.getToDo(words[2], isDone);
        case "D":
            return Task.getDeadline(words[2], words[3], isDone);
        case "E":
            return Task.getEvent(words[2], words[3], isDone);
        default:
            throw new DukeException(ExceptionType.FAIL_TO_WRITE, "Unknown symbol in save file.");
        }
    }

    // getters

    public static String getWordTodo() {
        return WORD_TODO;
    }

    public static String getWordDeadline() {
        return WORD_DEADLINE;
    }

    public static String getWordEvent() {
        return WORD_EVENT;
    }
}
