package tipsy;

public class MissingInputException extends TipsyException {
    public MissingInputException(TaskType taskType) {
        super("The description for " + taskType + " cannot be empty");
    }
}
