package duke;

import duke.task.*;

import java.util.Arrays;

/**
 * Provides methods to parse user command and saved contents.
 */
public class Parser {
    // Constant words
    protected static final String WORD_EXIT = "bye";
    protected static final String WORD_LIST = "list";
    protected static final String WORD_MARK = "done ";
    protected static final String WORD_TODO = "todo ";
    protected static final String WORD_DEADLINE = "deadline ";
    protected static final String WORD_DEADLINE_BY = " /by ";
    protected static final String WORD_EVENT = "event ";
    protected static final String WORD_EVENT_AT = " /at ";
    protected static final String WORD_DELETE = "delete ";
    protected static final String DIVIDER_WORD = " \\| ";
    protected static final String WORD_FIND = "find ";

    /**
     * Convert user command to {@link duke.DukeAction DukeAction}.
     * @param s user command
     * @param listSize size of current task list
     * @return type of duke action
     * @throws DukeException if user command is missing operand or invalid
     */
    public static DukeAction stringToDukeAction(String s, int listSize) throws DukeException {
        // Remove all leading whitespaces
        s = s.stripLeading();

        if (s.equals(WORD_EXIT)) {
            return DukeAction.EXIT;
        }
        else if (s.equals(WORD_LIST)) {
            return DukeAction.PRINT_LIST;
        }
        else if (isFind(s)) {
            return DukeAction.FIND;
        }
        else if (isMarkDown(s, listSize)) {
            return DukeAction.MARK_DONE;
        }
        else if (isDelete(s, listSize)) {
            return DukeAction.DELETE;
        }
        else if (isToDo(s)) {
                return DukeAction.TODO;
        }
        else if (isDeadline(s)) {
            return DukeAction.DEADLINE;
        }
        else if (isEvent(s)) {
            return DukeAction.EVENT;
        } else {
            throw Arrays.asList(new String[] {"todo", "done", "event", "delete", "deadline", "find"})
                    .contains(s)
                    ? new DukeException(ExceptionType.MISSING_OPERAND)
                    : new DukeException(ExceptionType.INVALID_COMMAND);
        }
    }

    private static boolean isFind(String s) throws DukeException {
        if (s.equals(WORD_FIND)) {
            throw new DukeException(ExceptionType.MISSING_OPERAND);
        }
        return s.length() > WORD_FIND.length() && s.startsWith(WORD_FIND);
    }

    private static boolean isMarkDown(String s, int listSize) throws DukeException {
        if (s.equals(WORD_MARK)) {
            throw new DukeException(ExceptionType.MISSING_OPERAND);
        }
        if (s.length() > WORD_MARK.length() && s.startsWith(WORD_MARK)) {
            try {
                int taskIndex = parseMarkString(s);
                if (taskIndex >= 0 && taskIndex < listSize) {
                    return true;
                } else {
                    throw new DukeException(ExceptionType.INDEX_OUT_OF_BOUND);
                }
            } catch (NumberFormatException e) {
                throw new DukeException(ExceptionType.INVALID_OPERAND);
            }
        }
        return false;
    }

    private static boolean isDelete(String s, int listSize) throws DukeException {
        if (s.length() > WORD_DELETE.length() && s.startsWith(WORD_DELETE)) {
            try {
                int taskIndex = parseDeleteString(s);
                if (taskIndex >= 0 && taskIndex < listSize) {
                    return true;
                }
                else {
                    throw new DukeException(ExceptionType.INDEX_OUT_OF_BOUND);
                }
            } catch (NumberFormatException e) {
                throw new DukeException(ExceptionType.INVALID_OPERAND);
            }
        }
        return false;
    }

    private static boolean isToDo(String s) throws DukeException {
        if (s.startsWith(WORD_TODO)) {
            if (s.length() <= WORD_TODO.length()) {
                throw new DukeException(ExceptionType.MISSING_OPERAND);
            } else {
                return true;
            }
        }
        return false;
    }

    private static boolean isDeadline(String s) throws DukeException {
        if (s.startsWith(WORD_DEADLINE)) {
            if (s.length() <= WORD_DEADLINE.length()) {
                throw new DukeException(ExceptionType.MISSING_OPERAND);
            } else if (s.contains(WORD_DEADLINE_BY)) {
                String[] strArr = parseDeadlineString(s);
                if (strArr.length == 2 && !strArr[0].equals("") && !strArr[1].equals("")) {
                    return true;
                } else {
                    throw new DukeException(ExceptionType.MISSING_OPERAND);
                }
            } else {
                throw new DukeException(ExceptionType.DDL_MISSING_KEYWORD);
            }
        }
        return false;
    }

    private static boolean isEvent(String s) throws DukeException {
        if (s.startsWith(WORD_EVENT)) {
            if (s.length() <= WORD_EVENT.length()) {
                throw new DukeException(ExceptionType.MISSING_OPERAND);
            } else if (s.contains(WORD_EVENT_AT)) {
                String[] strArr = parseEventString(s);
                if (strArr.length == 2 && !strArr[0].equals("") && !strArr[1].equals("")) {
                    return true;
                } else {
                    throw new DukeException(ExceptionType.MISSING_OPERAND);
                }
            } else {
                throw new DukeException(ExceptionType.EVENT_MISSING_KEYWORD);
            }
        }
        return false;
    }

    /**
     * Parses find command to get keyword.
     * @param s find command
     * @return keyword as string
     */
    protected static String parseFindString(String s) {
        return s.substring(WORD_FIND.length());
    }

    /**
     * Parses deadline command to description and due time.
     * @param s deadline command
     * @return An string array of length 2, with the first element being description and the second being due time.
     */
    protected static String[] parseDeadlineString(String s) {
        return s.substring(WORD_DEADLINE.length()).split(WORD_DEADLINE_BY, 2);
    }

    /**
     * Parses event command to description and time period.
     * @param s event command
     * @return An string array of length 2, with the first element being description and the second being time period.
     */
    protected static String[] parseEventString(String s) throws DukeException {
        return s.substring(WORD_EVENT.length()).split(WORD_EVENT_AT, 2);
    }

    /**
     * Parses mark command to index of task to mark as done.
     * @param s mark command
     * @return index of task to mark as done
     */
    protected static int parseMarkString(String s) {
        return Integer.parseInt(s.substring(WORD_MARK.length())) - 1;
    }

    /**
     * Parses delete command to index of task to remove.
     * @param s delete command
     * @return index of task to remove
     */
    protected static int parseDeleteString(String s) {
        return Integer.parseInt(s.substring(WORD_DELETE.length())) - 1;
    }

    /**
     * Converts save data string to task.
     * @see Task#populateSaveData()
     * @param s save data string
     * @return a new task constructs based on the given string
     * @throws DukeException if the string is not aligned with save data format
     */
    public static Task fileContentsToTask(String s) throws DukeException{
        String[] arr = s.split(DIVIDER_WORD, 4);
        boolean isDone = arr[1].equals("1");
        switch (arr[0]) {
            case "T":
                return new ToDo(arr[2], isDone);
            case "D":
                return new Deadline(arr[2], arr[3], isDone);
            case "E":
                return new Event(arr[2], arr[3], isDone);
            default:
                throw new DukeException(ExceptionType.FAIL_TO_WRITE, "Unknown symbol in file.");
        }
    }
}
