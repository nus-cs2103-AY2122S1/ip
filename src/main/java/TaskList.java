import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTaskAsDone(int index) {
        return tasks.get(index - 1).markAsDone();
    }

    public int size() {
        return tasks.size();
    }

    public Task deleteTask(int index) {
        return tasks.remove(index - 1);
    }

    @Override
    public String toString() {
        String str = String.format("Here are the %s in your list:\n", tasks.size() <= 1 ? "task" : "tasks");
        StringBuilder result = new StringBuilder(str);
        int len = tasks.size();
        if (len == 0) return result.toString();
        for (int i = 1; i < len; i++) {
            result.append(String.format("%s. %s\n", i, tasks.get(i - 1)));
        }
        result.append(String.format("%s. %s", len, tasks.get(len - 1)));
        return result.toString();
    }
}
