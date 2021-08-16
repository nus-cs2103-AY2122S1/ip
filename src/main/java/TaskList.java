import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> list = new ArrayList<>(List.of(new Task("read book"), new Task("return book"), new Task("buy bread")));

    public void addTask(Task task) {
        list.add(task);
    }

    public Task markTaskAsDone(int index) {
        return list.get(index - 1).markAsDone();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        int len = list.size();
        if (len == 0) return result.toString();
        for (int i = 1; i < len; i++) {
            result.append(String.format("%s. %s\n", i, list.get(i - 1)));
        }
        result.append(String.format("%s. %s", len, list.get(len - 1)));
        return result.toString();
    }
}
