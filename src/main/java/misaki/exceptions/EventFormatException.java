package misaki.exceptions;

/**
 * Encapsulates exceptions thrown by the chat bot when user enter invalid event format.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class EventFormatException extends MisakiException {
    /**
     * Returns the correct format of an event task.
     */
    public EventFormatException() {
        super("Try this format:\n"
                + "event <description> /at yyyy-mm-dd HH:mm\n"
                + "e.g. event birthday party /at 2021-09-19 18:00");
    }

}
