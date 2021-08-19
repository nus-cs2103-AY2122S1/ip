/**
 * 
 * This represents a BotException that's thrown when a bad command is given.
 * 
 * @author Rishabh Anand
 * @version CS2103, AY21/22 Semester 1
 * 
 */

public class BotException extends RuntimeException {
    public BotException(String message) {
        super(message);
    }
}
