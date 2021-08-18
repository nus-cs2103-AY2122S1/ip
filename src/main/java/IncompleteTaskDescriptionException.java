public class IncompleteTaskDescriptionException extends DukeException {
    private String taskName;

    public IncompleteTaskDescriptionException(String taskName) {
        super();
        this.taskName = taskName;
    }

    @Override
    public String getMessage() {
        return String.format("☹ OOPS!!! The description of a %s is either empty or incomplete.", this.taskName);
    }
}
