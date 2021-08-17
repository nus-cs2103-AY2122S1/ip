public class Todo extends Task {
    private String taskType = "[T]";
    private String taskName;

    public Todo(String taskName) {
        super(taskName);
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        String result = taskType + this.getCheckBox()
                + " " + this.taskName;
        return result;
    }
}
