/**
 * InvalidDoneIndexException is thrown if done i, where i is the index of task is not a number or taskNumber
 * exceeds the current list size.
 */
public class InvalidDoneIndexException extends Exception{
    private final static String SAD_ROBOT_ICON = "[~T-T~]";

    public InvalidDoneIndexException() {
        super(SAD_ROBOT_ICON +": OOPS!!! The task to mark as done does not exists. Please try again!");
    }
}
