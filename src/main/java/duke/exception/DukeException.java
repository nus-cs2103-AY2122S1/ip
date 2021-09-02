package duke.exception;

import duke.Task;

/**
 * A class that represents exceptions unique to duke.Duke.
 * A DukeException is thrown when a given input is not recognised a valid
 * input for duke.Duke.
 */
public class DukeException extends Exception {

    @Override
    public String getMessage() {
        return "Sorry, I don't know what that means :(";
    }

    /**
     * An exception which is thrown when the description of a duke.Task is not provided.
     */
    public static class EmptyDescriptionException extends DukeException {
        private final Task.Type type;

        public EmptyDescriptionException(Task.Type type) {
            this.type = type;
        }

        @Override
        public String getMessage() {
            return "The description of a " + type + " cannot be empty!";
        }
    }

    /**
     * An exception that is thrown when the date/time of a Deadline or Event is not provided.
     */
    public static class NoTimeException extends DukeException {
        private final Task.Type type;

        public NoTimeException(Task.Type type) {
            this.type = type;
        }

        @Override
        public String getMessage() {
            return "The " + type + " must have a date / time!";
        }
    }

    /**
     * Exception thrown when duke.Duke is unable to parse data in the file
     */
    public static class FileDataInvalidException extends DukeException {
        @Override
        public String getMessage() {
            return "Oh no! duke.Duke cannot retrieve data from the file :(";
        }
    }

    /**
     * Exception thrown when the input date string is in the wrong format
     */
    public static class DateInputInvalidException extends DukeException {
        @Override
        public String getMessage() {
            return "duke.Duke cannot parse the date! Please ensure the date is in the format dd-MM-yyyy";
        }
    }
}
