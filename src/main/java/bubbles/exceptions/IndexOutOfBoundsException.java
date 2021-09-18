package bubbles.exceptions;

/**
 * A class that represents the exception that is thrown
 * when the index of the task that the user wants to perform an action to (eg. mark
 * as done, or to delete the Task) is out of bounds of the TaskList.
 */
public class IndexOutOfBoundsException extends Exception {
    /**
     * A public constructor for the IndexOutOfBoundsException.
     * @param message The message containing details of the IndexOutOfBoundsException.
     */
    public IndexOutOfBoundsException(String message) {
        super(message);
    }

    /**
     * Returns the String representation of the Exception.
     *
     * @return The String representation of the Exception.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The number you have entered is out of bounds, please enter another number! ☹";
    }
}
