package duke.exceptions;

/**
 * Encapsulates exceptions thrown by the chat bot when user enter an empty description.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("The description of a task cannot be empty.");
    }
}
