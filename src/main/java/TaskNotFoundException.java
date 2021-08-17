public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return String.format("Task Not Found: %s", super.getMessage());
    }
}
