package duke.exceptions;

public class MissingEventDetailsException extends DukeException {
    public MissingEventDetailsException() { }

    @Override
    public String toString() {
        return "OOPS!!! The details of an event cannot be empty.";
    }
}
