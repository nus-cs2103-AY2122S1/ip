public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public boolean checkIsDone() {
        return this.isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void markComplete() {
        if (isDone) {
            System.out.println(taskName + " is already completed.");
        } else {
            this.isDone = true;
            System.out.println(
                    "Finally! Took you long enough to complete `" + taskName + "`"
            );
        }
    }

    public void markIncomplete() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }
}
