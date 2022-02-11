package duke.exception;

public class DescriptionNotFoundException extends DukeException {
    public DescriptionNotFoundException() {
        super("Description of task is missing! Please try again");
    }
}
