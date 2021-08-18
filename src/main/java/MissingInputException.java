public class MissingInputException extends DukeException {
    public MissingInputException(TaskType taskType) {
        super("The description for " + taskType + " cannot be empty");
    }
}
