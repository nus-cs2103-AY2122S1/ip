/**
 * InvalidIndexException is thrown if done i, where i is the index of task is not a number or taskNumber
 * exceeds the current list size.
 */
public class InvalidIndexException extends Exception {
    private final static String SAD_ROBOT_ICON = "[~T-T~]";
    /**
     * Constructs error message for either delete or done command
     *
     * @param commandType is either delete or done command
     */
    public InvalidIndexException(String commandType) {
        super(SAD_ROBOT_ICON +": OOPS!!! The task to set as "+ commandType + " does not exists. Please try again!");
    }
}
