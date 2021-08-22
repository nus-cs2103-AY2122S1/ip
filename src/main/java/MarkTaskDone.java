import java.util.List;

public class MarkTaskDone extends TaskOperation {
    private Task taskToMarkDone;

    public MarkTaskDone(List<Task> taskList, Task taskToMarkDone) {
        super(taskList);
        this.taskToMarkDone = taskToMarkDone;
    }

    @Override
    void performOperation() {
        taskToMarkDone.markAsDone();
        String description = String.format("Nice! I've marked this task as done:\n  %s\n", taskToMarkDone.toString());
        setDescription(description);
    }
}
