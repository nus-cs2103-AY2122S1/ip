import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> list = new ArrayList<>();

    public void addTask(Task task) {
        list.add(task);
    }

    public Task markTaskAsDone(int index) {
        return list.get(index - 1).markAsDone();
    }

    public int size() {
        return list.size();
    }

    public Task deleteTask(int index) {
        return list.remove(index - 1);
    }

    @Override
    public String toString() {
        String str = String.format("Here are the %s in your list:\n", list.size() <= 1 ? "task" : "tasks");
        StringBuilder result = new StringBuilder(str);
        int len = list.size();
        if (len == 0) return result.toString();
        for (int i = 1; i < len; i++) {
            result.append(String.format("%s. %s\n", i, list.get(i - 1)));
        }
        result.append(String.format("%s. %s", len, list.get(len - 1)));
        return result.toString();
    }
}
