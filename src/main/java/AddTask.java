import java.util.List;

public class AddTask extends TaskOperation {
    private Task taskToAdd;

    public AddTask(List<Task> taskList, Task taskToAdd) {
        super(taskList);
        this.taskToAdd = taskToAdd;
    }

    @Override
    void performOperation() {
        getTasks().add(taskToAdd);

        String description = String.format("Got it. I've added this task:\n  %s\n%s", taskToAdd.toString(),
                getTasksOverview());
        setDescription(description);
    }
}
