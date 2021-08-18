public class EmptyTaskDescriptionException extends DukeException {

    public EmptyTaskDescriptionException(String taskName) {
        super(String.format("The description of a %s cannot be empty!", taskName));
    }
}
