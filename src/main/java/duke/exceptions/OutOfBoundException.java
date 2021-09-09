package duke.exceptions;

/**
 * Encapsulates exceptions thrown by the chat bot when user enter invalid index.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class OutOfBoundException extends DukeException {
    public OutOfBoundException(int end) {
        super("Please enter an index number between 1 to " + end);
    }
}
