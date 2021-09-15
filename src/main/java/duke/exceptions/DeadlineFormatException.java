package duke.exceptions;

/**
 * Encapsulates exceptions thrown by the chat bot when user enter invalid deadline format.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class DeadlineFormatException extends DukeException {
    public DeadlineFormatException() {
        super("Please use the format: deadline <description> /by yyyy-mm-ddTHH:mm");
    }
}
