import java.util.List;

public abstract class TaskOperation {
    private String description;
    private List<Task> tasks;

    public TaskOperation(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void execute() {
        performOperation();
    }

    abstract void performOperation();

    protected String getTasksOverview() {
        return tasks.isEmpty() ? "There are no tasks.\n"
                : String.format("Now you have %d task%s in the list.\n", tasks.size(), (tasks.size() == 1 ? "" : "s"));
    }

    public String getDescription() {
        return description;
    }

    protected List<Task> getTasks() {
        return tasks;
    }

    protected void setDescription(String description) {
        this.description = description;
    }
}
