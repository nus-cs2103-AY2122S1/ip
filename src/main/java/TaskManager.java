import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public String addTask(Task task) {
        taskList.add(task);
        return String.format("added: %s", task.getTaskName());
    }

    public Task markTaskAsDone(int taskNumber) {
        try {
            // User input is 1-indexed
            int taskIndex = taskNumber - 1;
            Task task = taskList.get(taskIndex);
            return task.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        String[] allTasks = new String[taskList.size()];
        for (int i = 0; i < allTasks.length; i++) {
            Task task = taskList.get(i);
            // Display numbers are 1-indexed
            int taskNumber = i + 1;
            allTasks[i] = String.format("%d. %s", taskNumber, task.toString());
        }
        return String.join("\n", allTasks);
    }
}
