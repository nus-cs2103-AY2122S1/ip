/**
 * InvalidDoneCommandException is thrown if done command has more than 2 words.
 */
public class InvalidDoneCommandException extends Exception{
    private final static String SAD_ROBOT_ICON = "[~T-T~]";

    public InvalidDoneCommandException() {
        super(SAD_ROBOT_ICON +": OOPS!!! Done command is wrong. Please try again.");
    }
}
