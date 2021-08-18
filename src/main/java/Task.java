public class Task {
    private boolean done;
    private String taskName;

    private Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public static Task newTask(String taskName) {
        return new Task(taskName);
    }

    public void done() {
        this.done = true;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        String isDone = this.done ? "[X]" : "[ ]";
        return isDone + " " + taskName;
    }

}
