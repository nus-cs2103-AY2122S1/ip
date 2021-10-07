package duke.exception;

import duke.Task;

/**
 * The exception thrown when the user attempts to create a Deadline or an Event without providing a date.
 */
public class EmptyDateException extends DukeException {
    private final Task.Type type;

    public EmptyDateException(Task.Type type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        String result = "The " + type + " must have a date / time!\n"
                + "Please user the format:\n";
        if (type == Task.Type.DEADLINE) {
            result += "deadline <description> /by dd-MM-YYYY";
        } else {
            result += "event <description> /at dd-MM-YYYY";
        }
        return result;
    }
}
