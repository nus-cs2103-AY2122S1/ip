import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<? extends Task> tasks) {
        taskList = new ArrayList<>();
        for (Task task : tasks) {
            taskList.add(task);
        }
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task find(String taskName) {
        for (Task task: taskList) {
            if (task.getName().equals(taskName)) {
                return task;
            }
        }
        return null;
    }

    public Task delete(String taskName) {
        for (Task task: taskList) {
            if (task.getName().equals(taskName)) {
                taskList.remove(task);
                return task;
            }
        }
        return null;
    }
}
