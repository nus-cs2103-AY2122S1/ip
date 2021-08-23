/**
 * 
 * This represents a BotException that's thrown when a bad command is given.
 * 
 * @author Rishabh Anand
 * @version CS2103, AY21/22 Semester 1
 * 
 */

package duke;

public class BotException extends Exception {
    public BotException(String message) {
        super(message);
    }
}
