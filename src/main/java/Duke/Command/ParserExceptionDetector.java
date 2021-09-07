package duke.command;

import duke.exceptions.DukeException;
import duke.task.TaskList;

/**
 * @@author Hang Zelin
 *
 * CommandExceptionDetector will take in the message and see if there is any invalid
 * command inside.
 */
public class ParserExceptionDetector {
    private final String message;

    /**
     * Takes in the message and will configure it later.
     *
     * @param message
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
        //If the task type does not belong to the three types, throw an error.
        TaskList.OperationType[] operationTypes = TaskList.OperationType.values();
        for (TaskList.OperationType o : operationTypes) {
            boolean isMatch = message.toUpperCase().startsWith(o.toString());
            if (isMatch) {
                return;
            }
        }
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Determines whether there is no task info.
     *
     * @throws DukeException Exception is thrown when task cannot be read
     */
    public void detectGetTaskException() throws DukeException {
        boolean isCorrectType = message.startsWith("deadline") || message.startsWith("event")
                || message.startsWith("todo") || message.startsWith("find");
        boolean isInCorrectFormat = message.contains(" ");

        if (!isCorrectType) {
            return;
        }

        if (!isInCorrectFormat) {
            throw new DukeException("OOPS!!! The description of a " + message + " cannot be empty.");
        }
    }

    /**
     * Determines if there miss time info or the format of deadline, event, tell is incorrect.
     *
     * @throws DukeException Exception is thrown when the format of time is wrong or time info is missing.
     */
    public void detectGetTimeException() throws DukeException {
        boolean isContainTime = message.startsWith("todo") || message.startsWith("deadline")
                || message.startsWith("event") || message.startsWith("tell");
        boolean isDeadlineFormat = message.contains("/") && message.contains("/by");
        boolean isEventFormat = message.contains("/") && message.contains("/at");
        boolean isTellFormat = message.contains(" ");

        //throw exceptions for deadline or events' format.
        if (!isContainTime) {
            return;
        }

        if (message.startsWith("deadline") && !isDeadlineFormat) {
            throw new DukeException("OOPS!!! I'm sorry, but the format of deadline is wrong :-(");
        }

        if (message.startsWith("event") && !isEventFormat) {
            throw new DukeException("OOPS!!! I'm sorry, but the format of event is wrong :-(");
        }

        if (message.startsWith("tell") && !isTellFormat) {
            throw new DukeException("OOPS!!! I'm sorry, but the format of tell is wrong :-(");
        }
    }

    /**
     * Returns a boolean value of whether there is no index given to parser.
     *
     * @return Boolean value indicates whether there is no value for index.
     */
    public boolean detectIndexException() {
        boolean isCorrectFormat = message.contains(" ") && (message.startsWith("done") || message.startsWith("delete"));

        if (isCorrectFormat) {
            return true;
        } else {
            return false;
        }
    }
}