package duke.exceptions;

public class DeleteOutOfBoundsException extends DukeException {
    public DeleteOutOfBoundsException(int taskLength) {
        super("You only have " + taskLength + " tasks! Cannot delete task!");
    }
    
}
