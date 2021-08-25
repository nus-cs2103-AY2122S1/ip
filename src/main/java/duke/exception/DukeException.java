package duke.exception;

public abstract class DukeException extends Exception {

    DukeException(String message) {
        super(message);
    }

    public static class InvalidCommandException extends DukeException {
        public InvalidCommandException(String message) {
            super(message);
        }
    }
    
    public static class InvalidTaskDescriptionException extends DukeException {
        public InvalidTaskDescriptionException(String message) {
            super(message);
        }
    }
    
    public static class DuplicateTaskException extends DukeException {
        public DuplicateTaskException(String message) {
            super(message);
        }
    }

    public static class InvalidTaskNumException extends DukeException {
        public InvalidTaskNumException(String message) {
            super(message);
        }
    }

    public static class NoSuchTaskException extends DukeException {
        public NoSuchTaskException(String message) {
            super(message);
        }
    }

    public static class TaskAlreadyCompleteException extends DukeException {
        public TaskAlreadyCompleteException(String message) {
            super(message);
        }
    }

    public static class EmptyTaskListException extends DukeException {
        public EmptyTaskListException(String message) {
            super(message);
        }
    }
}
