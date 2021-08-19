/**
 * The possible type of DukeExceptions
 *
 * @author: Wei Yangken
 */

public class DukeException extends Exception{
    /**
     * Constructs the DukeException
     * @param errorMsg Message to be indicated when error occurs
     */
    DukeException(String errorMsg) {
        super(errorMsg);
    }

    /**
     * Exception when arguments for creation of tasks are insufficient
     */
    public static class InsufficientArgumentsException extends DukeException {
        InsufficientArgumentsException(String errorMsg) {
            super(errorMsg);
        }
    }

    /**
     * Exception when task type is not available
     */
    public static class TaskTypeNotFoundException extends DukeException {
        TaskTypeNotFoundException(String errorMsg) {
            super(errorMsg);
        }
    }

    /**
     * Exception when task has not been added
     */
    public static class TaskNotAddedException extends DukeException {
        TaskNotAddedException(String errorMsg) {
            super(errorMsg);
        }
    }

    /**
     * Exception when to-do list is empty
     */
    public static class EmptyListException extends DukeException {
        EmptyListException(String errorMsg) {
            super(errorMsg);
        }
    }

}
