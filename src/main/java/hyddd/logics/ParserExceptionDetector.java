package hyddd.logics;

import java.util.regex.Pattern;

import hyddd.exceptions.ExceptionType;
import hyddd.exceptions.HydddException;
import hyddd.task.TaskList;

/**
 * @@author Hang Zelin
 *
 * CommandExceptionDetector will take in the input and see if there is any invalid
 * command inside.
 */
public class ParserExceptionDetector {
    //Constant values
    private static final String EMPTY = "";
    private static final String SLASH = "/";
    private static final String SPACE = " ";
    private static final String BY = "/by";
    private static final String AT = "/at";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String EVENT = "event";
    private static final String FIND = "find";
    private static final String TELL = "tell";
    private final String input;

    /**
     * Constructor that takes in the input and will configure it later.
     *
     * @param input Input to be parsed.
     */
    public ParserExceptionDetector(String input) {
        this.input = input;
    }

    /**
     * Determines if operation type is valid by checking if it is in the domain of
     * the list of all operation types.
     *
     * @throws HydddException Exception is thrown if operationType does not occur in any
     * of the given type list.
     */
    public void detectOperationTypeException() throws HydddException {
        //If Input is empty throw empty error.
        if (input.equals(EMPTY) || input.startsWith(SPACE)) {
            throw new HydddException(ExceptionType.EMPTY_COMMAND_ERROR);
        }

        //If the task type does not belong to the three types, throw an error.
        TaskList.OperationType[] operationTypes = TaskList.OperationType.values();
        for (TaskList.OperationType o : operationTypes) {
            boolean isMatch = input.toUpperCase().startsWith(o.toString());
            if (isMatch) {
                return;
            }
        }

        throw new HydddException(ExceptionType.UNKNOWN_OPERATION);
    }

    /**
     * Determines whether there is no task info.
     *
     * @throws HydddException Exception is thrown when task cannot be read.
     */
    public void detectGetTaskException() throws HydddException {
        boolean isCorrectType;
        boolean hasSlash;
        boolean hasSpace;
        boolean isEmptyTask;

        isCorrectType = input.startsWith(DEADLINE) || input.startsWith(EVENT)
                || input.startsWith(TODO) || input.startsWith(FIND);
        hasSpace = input.contains(SPACE);
        hasSlash = input.contains(SLASH);
        if (!isCorrectType) {
            return;
        }
        if (!hasSpace) {
            throw new HydddException(ExceptionType.NO_TASK_ERROR);
        }
        isEmptyTask = hasSlash && !(input.startsWith(TODO) || input.startsWith(FIND))
                && (input.indexOf(SPACE) == input.lastIndexOf(SLASH) - 1);

        if (isEmptyTask) {
            throw new HydddException(ExceptionType.NO_TASK_ERROR);
        }
    }

    /**
     * Determines if there miss time info or the format of deadline, event, tell is incorrect.
     *
     * @throws HydddException Exception is thrown when the format of time is wrong or time info is missing.
     */
    public void detectGetTimeException() throws HydddException {
        boolean isContainTime;
        boolean isDeadlineFormat;
        boolean isEventFormat;
        boolean isTellFormat;

        isContainTime = input.startsWith(DEADLINE) || input.startsWith(EVENT) || input.startsWith(TELL);
        isDeadlineFormat = input.contains(SLASH) && input.contains(BY)
                && (input.charAt(input.indexOf(SLASH) - 1) == ' ');;
        isEventFormat = input.contains(SLASH) && input.contains(AT) && (input.charAt(input.indexOf(SLASH) - 1) == ' ');;
        isTellFormat = input.contains(SPACE);

        if (!isContainTime) {
            return;
        }

        //throw exceptions for deadline or events' format.
        if (input.startsWith(DEADLINE) && !isDeadlineFormat || input.endsWith(BY)) {
            throw new HydddException(ExceptionType.DEADLINE_FORMAT_ERROR);
        }

        if (input.startsWith(EVENT) && !isEventFormat || input.endsWith(AT)) {
            throw new HydddException(ExceptionType.EVENT_FORMAT_ERROR);
        }

        if (input.startsWith(TELL) && !isTellFormat) {
            throw new HydddException(ExceptionType.EVENT_FORMAT_ERROR);
        }
    }

    /**
     * Returns a boolean value of whether there is no index given to parser.
     *
     * @return Boolean value indicates whether there is no value for index
     * or wrong format.
     */
    public boolean detectIndexException() {
        boolean isCorrectStart;
        boolean hasSpace;
        boolean hasMultipleSpace;
        boolean isValidNumber;
        Pattern pattern;

        isCorrectStart = (input.startsWith(DONE) || input.startsWith(DELETE));
        hasSpace = input.contains(SPACE);

        if (!(isCorrectStart && hasSpace)) {
            return false;
        }
        hasMultipleSpace = input.indexOf(SPACE) != input.lastIndexOf(SPACE);
        pattern = Pattern.compile("^-?[0-9]+");
        isValidNumber = pattern.matcher(input.substring(input.indexOf(SPACE) + 1)).matches();

        return !hasMultipleSpace && isValidNumber;
    }
}
