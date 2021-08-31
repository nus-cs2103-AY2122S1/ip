package duke;

/**
 * The possible type of DukeExceptions
 *
 * @author: Wei Yangken
 */

public class DukeException extends Exception {
    /**
     * Constructs the DukeException
     * @param errorMsg Message to be indicated when error occurs
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }

    /**
     * Exception when arguments for creation of tasks are insufficient
     */
    public static class InsufficientArgumentsException extends DukeException {
        public InsufficientArgumentsException(String errorMsg) {
            super(errorMsg);
        }
    }

    /**
     * Exception when task type is not available
     */
    public static class TaskTypeNotFoundException extends DukeException {
        public TaskTypeNotFoundException(String errorMsg) {
            super(errorMsg);
        }
    }

    /**
     * Exception when task has not been added
     */
    public static class TaskNotAddedException extends DukeException {
        public TaskNotAddedException(String errorMsg) {
            super(errorMsg);
        }
    }

    /**
     * Exception when to-do list is empty
     */
    public static class EmptyListException extends DukeException {
        public EmptyListException(String errorMsg) {
            super(errorMsg);
        }
    }

    /**
     * Exception when task is not found
     */
    public static class TaskNotFoundException extends DukeException {
        public TaskNotFoundException(String errorMsg) {
            super(errorMsg);
        }
    }

}
