package misaki.exceptions;

/**
 * Encapsulates exceptions thrown by the chat bot when user enter an empty description.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class EmptyDescriptionException extends MisakiException {
    public EmptyDescriptionException() {
        super("Description of a task cannot be empty! Try again?");
    }
}
