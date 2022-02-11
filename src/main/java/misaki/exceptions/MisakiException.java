package misaki.exceptions;

/**
 * Encapsulates exceptions thrown by the chat bot.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class MisakiException extends RuntimeException {
    public MisakiException(String errorMessage) {
        super(errorMessage);
    }
}
