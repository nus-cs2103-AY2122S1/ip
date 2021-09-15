package duke.Exceptions;

/**
 * Represents EmptyTaskListException class
 */
public class EmptyTaskListException extends Exception{
    /**
     * The Constructor for EmptyTaskListException
     * @param message
     */
    public EmptyTaskListException(String message) {
        super(String.format("☹ OOPS!!! Your enter number is wrong, The list is empty or out of list."));
    }
}
