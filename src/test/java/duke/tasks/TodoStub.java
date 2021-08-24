package duke.tasks;

public class TodoStub extends Todo {
    private static final String TASK_TAG = "todo";
    private final String taskName;
    private boolean isDone;

    public TodoStub(String taskName) {
        super("");
        this.taskName = "sleep";
        this.isDone = false;
    }
}
