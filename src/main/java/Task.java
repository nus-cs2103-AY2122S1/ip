public class Task {
    private String taskName;
    private int taskNumber;
    private boolean isDone;

    public Task(int taskNumber, String taskName) {
        this.taskNumber = taskNumber;
        this.taskName = taskName;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getCheckMark() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    @Override
    public String toString() {
        String result = String.format("%d.[%s] %s", taskNumber, this.getCheckMark(), taskName);
        return result;
    }
}
