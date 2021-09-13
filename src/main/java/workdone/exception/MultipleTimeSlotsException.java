package workdone.exception;

/**
 * Represents an exception thrown when more than one time slots are given. A subclass of WorkDoneException.
 */
public class MultipleTimeSlotsException extends WorkDoneException {
    /**
     * Constructor of the class `MultipleTimeSlotsException`.
     *
     * @param taskType Type of a task.
     */
    public MultipleTimeSlotsException(String taskType) {
        super(String.format("☹ OOPS!!! %s cannot occupy multiple time slots.", taskType));
    }
}
