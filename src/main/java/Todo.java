public class Todo extends Task {
    private String taskType = "[T]";
    private String taskName;
    private boolean isDone;

    public Todo(String taskName) {
        super(taskName);
        this.taskName = taskName;
    }

    public Todo(String taskName, boolean isDone) {
        super(taskName);
        this.taskName = taskName;
        if (isDone) {
            this.markAsDone();
        }
    }

    @Override
    public String toString() {
        String result = taskType + this.getCheckBox()
                + " " + this.taskName;
        return result;
    }
}
