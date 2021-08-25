package duke.exception;

public class MultipleTimeSlotsException extends DukeException {
    public MultipleTimeSlotsException(String taskType) {
        super(String.format("☹ OOPS!!! %s cannot occupy multiple time slots.", taskType));
    }
}
