public class EmptyTaskDescriptionException extends DukeException {
    private String taskType;

    public EmptyTaskDescriptionException(String errorMessage, String taskType) {
        super(errorMessage);
        this.taskType = taskType;
    }

    @Override
    public String getMessage() {
        switch (taskType) {
        case "todo":
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        case "deadline":
            return "☹ OOPS!!! The description of a deadline cannot be empty.";
        case "event":
            return "☹ OOPS!!! The description of a event cannot be empty.";
        default:
            return "Error: EmptyTaskDescriptionException";
        }
    }
}
