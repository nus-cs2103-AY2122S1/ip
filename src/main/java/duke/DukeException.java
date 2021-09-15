package duke;

public class DukeException extends IllegalArgumentException {
    private Type type;

    public enum Type {
        LOADING, TODO, INVALID, DATE
    }
    public DukeException(Type t) {
        type = t;
    }

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
