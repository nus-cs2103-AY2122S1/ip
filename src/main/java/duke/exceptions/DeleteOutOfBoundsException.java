package duke.exceptions;

/**
 * Class of exception that handles deleting task
 * number greater than number odf task available.
 */
public class DeleteOutOfBoundsException extends DukeException {

    /**
     * Instantiates a deleting exception.
     * @param taskLength Integer of the length of the taskList
     */
    public DeleteOutOfBoundsException(int taskLength) {
        super("You only have " + taskLength + " tasks! Cannot delete task!");
    }
}
