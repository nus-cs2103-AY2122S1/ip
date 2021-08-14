/**
 * Thrown to indicate that the chatbot has received an invalid command.
 *
 * @author Yeo Jun Wei
 */
public class DukeException extends Exception {

    /** Horizontal line for formatting */
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public DukeException(String message) {
        super(HORIZONTAL_LINE + "\n" + message + "\n" + HORIZONTAL_LINE);
    }
}

class EmptyTaskDescriptionException extends DukeException {

    public EmptyTaskDescriptionException(String taskType) {
        super("The description of a " + taskType + " cannot be empty!");
    }
}

class UnrecognisedCommandException extends DukeException {

    public UnrecognisedCommandException() {
        super("This command is not recognised! Please enter a valid command!");
    }
}

class TaskIndexOutOfBoundsException extends DukeException {

    public TaskIndexOutOfBoundsException() {
        super("Invalid task index specified!");
    }
}

class TaskAlreadyDoneException extends DukeException {

    public TaskAlreadyDoneException() {
        super("This task has already been done!");
    }
}

class TimeNotSpecifiedException extends DukeException {

    public TimeNotSpecifiedException(String taskSpecifier) {
        super("The date/time is not specified!\n"
                + "Please follow this format: [Task]" + taskSpecifier + "[Date/Time]");
    }
}