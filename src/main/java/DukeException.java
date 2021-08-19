abstract class DukeException extends Exception {

    DukeException(String message) {
        super(message);
    }

    static class InvalidCommandException extends DukeException {
        InvalidCommandException(String message) {
            super(message);
        }
    }
    
    static class InvalidTaskDescriptionException extends DukeException {
        InvalidTaskDescriptionException(String message) {
            super(message);
        }
    }
    
    static class DuplicateTaskException extends DukeException {
        DuplicateTaskException(String message) {
            super(message);
        }
    }
}
