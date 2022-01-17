package duke;

/**
 * A class of exceptions that are thrown for invalid user inputs when parsed.
 */
public class DukeException extends Exception {

    /**
     * Creates a DukeException.
     */
    public DukeException() {
        super();
    }

    /**
     * A class of <code>DukeException</code> thrown when the index for <code>Task</code> number is not found within the
     * <code>TaskList</code>.
     */
    static class DukeTaskNotFoundException extends DukeException {

        @Override
        public String toString() {
            return "Sorry, this task number cannot be found :(";
        }
    }

    /**
     * A class of <code>DukeException</code> thrown when an invalid <code>Task</code> number is provided.
     */
    static class DukeTaskFailException extends DukeException {

        @Override
        public String toString() {
            return "Sorry, I am not sure what the task number is :(";
        }
    }

    /**
     * A class of <code>DukeException</code> thrown when an invalid <code>description</code> is provided.
     */
    static class DukeNoDescriptionException extends DukeException {

        @Override
        public String toString() {
            return "Oops :( Please key in a valid description!";
        }
    }

    /**
     * A class of <code>DukeException</code> thrown when no time for a <code>Deadline</code> or
     * <code>Event</code> is provided.
     */
    static class DukeNoTimeGivenException extends DukeException {

        @Override
        public String toString() {
            return "Oops :( Please key in a valid time to proceed";
        }
    }

    /**
     * A class of <code>DukeException</code> thrown when an invalid <code>input</code> is provided and
     * cannot be handled appropriately.
     */
    static class DukeInvalidInputException extends DukeException {

        @Override
        public String toString() {
            return "Oops :( Friend does not recognise this command! "
                + "Did you spell your command correctly?";
        }
    }

    static class DukeNoSearchFoundException extends DukeException {
        @Override
        public String toString() {
            return "Oops :( Please key in a valid search to proceed!";
        }
    }

    static class DukeInvalidTagException extends DukeException {
        @Override
        public String toString() {
            return "Oops :( For adding tags, please follow the format 'tag [task index] [your tag]'.";
        }
    }
}
