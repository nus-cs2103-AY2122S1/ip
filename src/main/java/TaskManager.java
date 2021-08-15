import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public String addTask(Task task) {
        taskList.add(task);
        return String.format("added: %s", task.toString());
    }

    @Override
    public String toString() {
        String[] allTasks = new String[taskList.size()];
        for (int i = 0; i < allTasks.length; i++) {
            // Task numbering starts from 1
            int taskNumber = i + 1;
            String task = taskList.get(i).toString();
            allTasks[i] = String.format("%d. %s", taskNumber, task.toString());
        }
        return String.join("\n", allTasks);
    }
}
