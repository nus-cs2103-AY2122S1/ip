package agent.exceptions;

/**
 * Represents a generic <code>Exception</code> originating due to functionality failure by the
 * Duke helper chat bot.
 *
 * @author kevin9foong
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
