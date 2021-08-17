public class DukeException extends IllegalArgumentException {

    public DukeException(String message) {
        super(message);
    }

    public static class EmptyTimelineDescription extends DukeException {
        public EmptyTimelineDescription(String message) {
            super("the timeline of a " + message + " cannot be empty!");
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static class EmptyDescriptionException extends DukeException {
        public EmptyDescriptionException(String message) {
            super("the description of a " + message + " cannot be empty!");
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static class InvalidCommandException extends DukeException {
        public InvalidCommandException(String message) {
            super("he does not recognize your command! Try another command!");
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @Override
    public String toString() {
        return "Duke is confused! Duke says that " + getMessage();
    }
}
