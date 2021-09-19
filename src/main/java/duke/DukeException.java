package duke;

/**
 * DukeException is a class for exceptions related to Duke.
 *
 * @author Samuel Lau
 */
public class DukeException extends IllegalArgumentException {
    private Type type;

    /**
     * Types of Duke Exceptions.
     */
    public enum Type {
        LOADING, TODO, INVALID, DATE
    }

    /**
     * Constructor for DukeException.
     *
     * @param t Type of DukeException to be constructed.
     */
    public DukeException(Type t) {
        type = t;
    }

    /**
     * Returns the message related to the type of Duke Exception.
     *
     * @return Message string.
     */
    @Override
    public String getMessage() {
        switch(type) {
        case TODO:
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        case LOADING:
            return "☹ OOPS!!! Your file could not be loaded";
        case INVALID:
            return "☹ OOPS!!! Your input is invalid. Try again";
        case DATE:
            return "☹ OOPS!!! Wrong format for date. Should be in yyyy-mm-dd";
        default:
            return super.getMessage();
        }
    }
}
