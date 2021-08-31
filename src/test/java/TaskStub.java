import lebron.task.Task;

public class TaskStub extends Task {
    String name;
    /**
     * Constructor.
     *
     * @param name the name of the task
     */
    public TaskStub(String name) {
        super(name);
    }

    @Override
    public String getDoneValue() {
        return "1";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void mark() {
        name = "bob";
    }
}
