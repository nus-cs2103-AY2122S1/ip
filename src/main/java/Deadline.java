public class Deadline extends Task {
    private String taskType = "[D]";
    private String taskName;
    private String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.taskName = taskName;
        this.deadline = deadline;
    }

    public String getDeadline() {
        return String.format("(by: %s)", deadline);
    }

    @Override
    public String toString() {
        String result = taskType + this.getCheckBox()
                + " " + this.taskName + " "
                + getDeadline();
        return result;
    }
}
