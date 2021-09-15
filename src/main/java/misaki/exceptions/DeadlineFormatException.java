package misaki.exceptions;

/**
 * Encapsulates exceptions thrown by the chat bot when user enter invalid deadline format.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class DeadlineFormatException extends MisakiException {
    /**
     * Returns the correct format of a deadline task.
     */
    public DeadlineFormatException() {
        super("Try this format:\n"
                + "deadline <description> /by yyyy-mm-dd HH:mm\n"
                + "e.g. deadline submit homework /by 2020-01-30 23:59");
    }
}
