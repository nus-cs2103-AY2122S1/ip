public class EmptyTaskDescriptionException extends DukeException {
    private String taskName;

    public EmptyTaskDescriptionException(String taskName) {
        super();
        this.taskName = taskName;
    }

    @Override
    public String getMessage() {
        return String.format("☹ OOPS!!! The description of a %s cannot be empty.", this.taskName);
    }
}
