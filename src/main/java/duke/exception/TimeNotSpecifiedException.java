package duke.exception;

public class TimeNotSpecifiedException extends DukeException {

    public TimeNotSpecifiedException(String taskSpecifier) {
        super("The date/time is not specified!\n"
                + "Please follow this format: [Task]" + taskSpecifier + "[Date/Time]");
    }
}
