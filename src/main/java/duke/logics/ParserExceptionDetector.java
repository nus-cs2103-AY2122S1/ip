package duke.logics;

import duke.exceptions.DukeException;
import duke.exceptions.ExceptionType;
import duke.task.TaskList;

/**
 * @@author Hang Zelin
 *
 * CommandExceptionDetector will take in the message and see if there is any invalid
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
    private final String message;

    /**
     * Constructor that takes in the message and will configure it later.
     *
     * @param message Message to be parsed.
     */
    public ParserExceptionDetector(String message) {
        this.message = message;
    }

    /**
     * Determines if operation type is valid by checking if it is in the domain of
     * the list of all operation types.
     *
     * @throws DukeException Exception is thrown if operationType does not occur in any
     * of the given type list.
     */
    public void detectOperationTypeException() throws DukeException {
        //If Input is empty throw empty error.
        if (message.equals(EMPTY) || message.startsWith(SPACE)) {
            throw new DukeException(ExceptionType.EMPTY_COMMAND_ERROR);
        }

        //If the task type does not belong to the three types, throw an error.
        TaskList.OperationType[] operationTypes = TaskList.OperationType.values();
        for (TaskList.OperationType o : operationTypes) {
            boolean isMatch = message.toUpperCase().startsWith(o.toString());
            if (isMatch) {
                return;
            }
        }

        throw new DukeException(ExceptionType.UNKNOWN_OPERATION);
    }

    /**
     * Determines whether there is no task info.
     *
     * @throws DukeException Exception is thrown when task cannot be read.
     */
    public void detectGetTaskException() throws DukeException {
        boolean isCorrectType = message.startsWith(DEADLINE) || message.startsWith(EVENT)
                || message.startsWith(TODO) || message.startsWith(FIND);
        boolean isInCorrectFormat = message.contains(SPACE);

        if (!isCorrectType) {
            return;
        }

        if (!isInCorrectFormat) {
            throw new DukeException(ExceptionType.NO_TASK_ERROR);
        }
    }

    /**
     * Determines if there miss time info or the format of deadline, event, tell is incorrect.
     *
     * @throws DukeException Exception is thrown when the format of time is wrong or time info is missing.
     */
    public void detectGetTimeException() throws DukeException {
        boolean isContainTime = message.startsWith(TODO) || message.startsWith(DEADLINE)
                || message.startsWith(EVENT) || message.startsWith(TELL);
        boolean isDeadlineFormat = message.contains(SLASH) && message.contains(BY);
        boolean isEventFormat = message.contains(SLASH) && message.contains(AT);
        boolean isTellFormat = message.contains(SPACE);

        if (!isContainTime) {
            return;
        }

        //throw exceptions for deadline or events' format.
        if (message.startsWith(DEADLINE) && !isDeadlineFormat) {
            throw new DukeException(ExceptionType.DEADLINE_FORMAT_ERROR);
        }

        if (message.startsWith(EVENT) && !isEventFormat) {
            throw new DukeException(ExceptionType.EVENT_FORMAT_ERROR);
        }

        if (message.startsWith(TELL) && !isTellFormat) {
            throw new DukeException(ExceptionType.EVENT_FORMAT_ERROR);
        }
    }

    /**
     * Returns a boolean value of whether there is no index given to parser.
     *
     * @return Boolean value indicates whether there is no value for index.
     */
    public boolean detectIndexException() {
        boolean isCorrectFormat = message.contains(SPACE) && (message.startsWith(DONE) || message.startsWith(DELETE));

        return isCorrectFormat;
    }
}
