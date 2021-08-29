package duke.exceptions;

public class DoneOutOfBoundsException extends DukeException {
    public DoneOutOfBoundsException(int taskLength) {
        super("You only have " + taskLength + " tasks! Cannot mark as Done!");
    }
}
