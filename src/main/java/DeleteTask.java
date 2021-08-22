import java.util.List;

public class DeleteTask extends TaskOperation {
    private int indexToDelete;

    public DeleteTask(List<Task> taskList, int indexToDelete) {
        super(taskList);
        this.indexToDelete = indexToDelete;
    }

    @Override
    void performOperation() {
        String description = String.format("Got it. I've removed this task:\n  %s\n%s",
                getTasks().remove(indexToDelete).toString(), getTasksOverview());
        setDescription(description);
    }
}
