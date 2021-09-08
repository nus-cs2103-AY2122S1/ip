package duke.exception;

public abstract class DukeException extends Exception {

    DukeException(String message) {
        super(message);
    }

    /**
     * An exception that is thrown if user types an unrecognisable command.
     */
    public static class InvalidCommandException extends DukeException {
        public InvalidCommandException(String message) {
            super(message);
        }
    }

    /**
     * An exception that is thrown if the user gives an incomplete or invalid command e.g. missing date and time for
     * {@link duke.task.Event} and {@link duke.task.Deadline} classes, missing task name etc.
     */
    public static class InvalidTaskDescriptionException extends DukeException {
        public InvalidTaskDescriptionException(String message) {
            super(message);
        }
    }

    /**
     * An exception that is thrown if the user tries to add a task with the same name as a task already in the list
     * of tasks.
     */
    public static class DuplicateTaskException extends DukeException {
        public DuplicateTaskException(String message) {
            super(message);
        }
    }

    /**
     * An exception that is thrown if the user specifies an invalid task number to delete, e.g. if the task number
     * specified is {@literal <} 0 or {@literal >} the number of tasks.
     */
    public static class InvalidTaskNumException extends DukeException {
        public InvalidTaskNumException(String message) {
            super(message);
        }
    }

    /**
     * An exception that is thrown when the user attempts to modify or look for a task that does not exist.
     */
    public static class NoSuchTaskException extends DukeException {
        public NoSuchTaskException(String message) {
            super(message);
        }
    }

    /**
     * An exception that is thrown when the user tries to mark an already completed task as completed.
     */
    public static class TaskAlreadyCompleteException extends DukeException {
        public TaskAlreadyCompleteException(String message) {
            super(message);
        }
    }

    /**
     * An exception that is thrown when the user tries to print or access the list of tasks when there are no tasks
     * in the list.
     */
    public static class EmptyTaskListException extends DukeException {
        public EmptyTaskListException(String message) {
            super(message);
        }
    }

    public static class EmptyScheduleException extends DukeException {
        public EmptyScheduleException(String message) {
            super(message);
        }
    }
}
