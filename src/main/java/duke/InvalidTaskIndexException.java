package duke;

/**
 * An exception thrown in Duke when
 * the index of the task from the Task List is invalid.
 */
public class InvalidTaskIndexException extends Exception {

    /**
     * A public constructor for Invalid Task Index Exception.
     * @param str The error message that is passed from the main code.
     */
    public InvalidTaskIndexException(String str) {
        super(str);
    }

    /**
     * Returns the string representation of Invalid Task Index Exception.
     * @return the string representation of Invalid Task Index Exception.
     */
    @Override
    public String toString() {
        return getMessage() ;
    }
}