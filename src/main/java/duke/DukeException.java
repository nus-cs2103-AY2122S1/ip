package duke;

public class DukeException extends Exception{
    public DukeException() {
      super ("    OOPS!!! The description of a todo cannot be empty.");
    }

    public DukeException(String message) {
        super(message);
    }
}
