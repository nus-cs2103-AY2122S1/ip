package duke;

import duke.task.*;

import java.util.Arrays;

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
    
    public static DukeAction stringToDukeAction(String s, int listSize) throws DukeException {
        // Remove all leading whitespaces
        s = s.stripLeading();

        if (s.equals(WORD_EXIT)) {
            return DukeAction.EXIT;
        }
        else if (s.equals(WORD_LIST)) {
            return DukeAction.PRINT_LIST;
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
            throw Arrays.asList(new String[] {"todo", "done", "event", "delete", "deadline"})
                    .contains(s)
                    ? new DukeException(ExceptionType.MISSING_OPERAND)
                    : new DukeException(ExceptionType.INVALID_COMMAND);
        }
    }

    private static boolean isMarkDown(String s, int listSize) throws DukeException {
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

    protected static String[] parseDeadlineString(String s) {
        return s.substring(WORD_DEADLINE.length()).split(WORD_DEADLINE_BY, 2);
    }

    protected static String[] parseEventString(String s) throws DukeException {
        return s.substring(WORD_EVENT.length()).split(WORD_EVENT_AT, 2);
    }

    protected static int parseMarkString(String s) {
        return Integer.parseInt(s.substring(WORD_MARK.length())) - 1;
    }

    protected static int parseDeleteString(String s) {
        return Integer.parseInt(s.substring(WORD_DELETE.length())) - 1;
    }
    
    protected static Task fileContentsToTask(String s) throws DukeException{
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
