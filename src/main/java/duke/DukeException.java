package duke;

/**Class that handles the exceptions*/
public abstract class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }

    public String getMsg() {
        return "";
    }
}

/**Class that handles the null task input exceptions*/
class NullTaskError extends DukeException {
    public NullTaskError() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }

    public String getMsg(String type) {
        return "OOPS!!! The description of a " + type + " cannot be empty.";
    }
}

/**Class that handles the non exist keyword exceptions*/
class NonExistentKeyword extends DukeException {
    public NonExistentKeyword() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public String getMsg() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
