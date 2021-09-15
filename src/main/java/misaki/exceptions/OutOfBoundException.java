package misaki.exceptions;

/**
 * Encapsulates exceptions thrown by the chat bot when user enter invalid index.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class OutOfBoundException extends MisakiException {
    public OutOfBoundException(int end) {
        super("Try entering an index number between 1 to " + end + "!");
    }
}
