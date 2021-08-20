public class DukeException extends Exception{

    private String errorMessage;
    public enum Type {
        DESCRIPTION, INDEX
    }
    private Type type;

    public DukeException(Type s) {
        type = s;
        if (s.equals(Type.DESCRIPTION)) {
            this.errorMessage = "☹ OOPS!!! The description of a task cannot be empty.";
        } else if (s.equals(Type.INDEX)) {
            this.errorMessage = "☹ OOPS!!! The index of the task is out of range.";
        }
    }

    @Override
    public String toString() {
        return errorMessage;
    }

    public Type getType() {
        return type;
    }
}
