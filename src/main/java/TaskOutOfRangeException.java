public class TaskOutOfRangeException extends InvalidInputException {
    public TaskOutOfRangeException() {
        super("Task does not exist!");
    }
}
