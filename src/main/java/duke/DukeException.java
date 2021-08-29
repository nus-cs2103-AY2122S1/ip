package duke;

public abstract class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }

    public String getMsg() {
        return "";
    }
}

class NullTaskError extends DukeException {
    public NullTaskError() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }

    public String getMsg(String type) {
        return "OOPS!!! The description of a " + type + " cannot be empty.";
    }
}

class NonExistentKeyword extends DukeException {
    public NonExistentKeyword() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public String getMsg() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
