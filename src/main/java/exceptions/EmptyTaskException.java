package exceptions;

public class EmptyTaskException extends Exception {
    private String taskType;

    public EmptyTaskException(String taskType) {
        super(taskType);
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a " + taskType + " cannot be empty. ☹\n";
    }
}