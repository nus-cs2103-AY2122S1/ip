package duke.exception;

/**
 * Represents the main class that contains all the possible exceptions
 * thrown by Duke whenever the user interacts with the program
 * illegally.
 *
 * @author yeo-yiheng
 */
public class DukeException extends IllegalArgumentException {

    /**
     * Constructs an instance of a general Duke Exception.
     *
     * @param message error message upon exception thrown
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Represents the class of exception that is thrown whenever
     * a user fails to input the timeline of a deadline / event
     * task.
     */
    public static class EmptyTimelineDescription extends DukeException {

        /**
         * Constructs an instance of an Empty Timeline Duke Exception.
         *
         * @param message error message upon exception thrown
         */
        public EmptyTimelineDescription(String message) {
            super("The timeline of a " + message + " cannot be empty!");
        }

        /**
         * Returns the string representation of the particular exception.
         *
         * @return String representation of the particular exception
         */
        @Override
        public String toString() {
            return super.toString();
        }
    }

    /**
     * Represents the class of exception that is thrown whenever
     * a user fails to input the description of a task.
     */
    public static class EmptyDescriptionException extends DukeException {

        /**
         * Constructs an instance of an empty description Duke Exception.
         */
        public EmptyDescriptionException() {
            super("The description cannot be empty!");
        }

        /**
         * Returns the string representation of the particular exception.
         *
         * @return String representation of the particular exception
         */
        @Override
        public String toString() {
            return super.toString();
        }
    }

    /**
     * Represents the class of exception that is thrown whenever
     * a user fails to input the keyword of a find command.
     */
    public static class EmptyKeywordException extends DukeException {

        /**
         * Constructs an instance of an empty keyword Duke Exception.
         */
        public EmptyKeywordException() {
            super("Please provide a keyword!");
        }

        /**
         * Returns the string representation of the particular exception.
         *
         * @return String representation of the particular exception
         */
        @Override
        public String toString() {
            return super.toString();
        }
    }

    /**
     * Represents the class of exception that is thrown whenever
     * a user inputs an invalid command.
     */
    public static class InvalidCommandException extends DukeException {

        /**
         * Constructs an instance of an invalid command Duke Exception.
         *
         * @param message error message upon exception thrown
         */
        public InvalidCommandException(String message) {
            super("I do not recognize your command \"" + message + "\". Try another command!");
        }

        /**
         * Returns the string representation of the particular exception.
         *
         * @return String representation of the particular exception
         */
        @Override
        public String toString() {
            return super.toString();
        }
    }

    /**
     * Represents the class of exception that is thrown whenever
     * a user inputs an invalid integer.
     */
    public static class InvalidIntegerException extends DukeException {

        /**
         * Constructs an instance of an invalid integer Duke Exception.
         */
        public InvalidIntegerException() {
            super("Please input a valid integer!");
        }

        /**
         * Returns the string representation of the particular exception.
         *
         * @return String representation of the particular exception
         */
        @Override
        public String toString() {
            return super.toString();
        }
    }

    /**
     * Represents the class of exception that is thrown whenever
     * a user tries to add a duplicate task.
     */
    public static class DuplicateTaskException extends DukeException {

        /**
         * Constructs an instance of a duplicate task Duke Exception.
         *
         * @param message error message upon exception thrown
         */
        public DuplicateTaskException(String message) {
            super("Your task \"" + message + "\" already exists! Try adding another task.");
        }

        /**
         * Returns the string representation of a duplicate task exception.
         *
         * @return String representation of the general exception
         */
        public String toString() {
            return super.toString();
        }
    }

    /**
     * Represents the class of exception that is thrown whenever
     * the specified file is not found.
     */
    public static class FileNotFoundException extends DukeException {

        /**
         * Constructs an instance of a file not found exception.
         */
        public FileNotFoundException() {
            super("File is not found, are you sure it the file exists?");
        }

        /**
         * Returns the string representation of the particular exception.
         *
         * @return String representation of the particular exception
         */
        @Override
        public String toString() {
            return super.toString();
        }
    }

    /**
     * Returns the string representation of the general exception.
     *
     * @return String representation of the general exception
     */
    @Override
    public String toString() {
        return "Duke is confused! Duke says that " + getMessage();
    }
}
