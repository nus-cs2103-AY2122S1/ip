package duke;

public class EmptyEventException extends DukeException {

    public EmptyEventException() {
        super("☹ OOPS!!! The description of a event cannot be empty.");
    }
}

