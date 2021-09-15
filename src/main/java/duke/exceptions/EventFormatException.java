package duke.exceptions;

/**
 * Encapsulates exceptions thrown by the chat bot when user enter invalid event format.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class EventFormatException extends DukeException {
    public EventFormatException() {
        super("Please use the format: event <description> /from yyyy-mm-ddTHH:mm /to yyyy-mm-ddTHH:mm");
    }
}
