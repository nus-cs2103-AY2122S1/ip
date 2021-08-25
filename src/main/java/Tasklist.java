import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Tasklist {
    private final List<Task> taskList = new ArrayList<>();
    public Tasklist() {

    }

    public int size() {
        return taskList.size();
    }

    public Task getTask(int idx) throws IndexOutOfBoundsException {
        int i = idx - 1;
        return taskList.get(i);
    }

    public Task removeTask(int idx) throws IndexOutOfBoundsException {
        int i = idx - 1;
        return taskList.remove(i);
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public String toWritable() {
        return taskList.stream().map(task -> task.toString() + "\n").collect(Collectors.joining());
    }

    public ListIterator<Task> toIterable() {
        return taskList.listIterator();
    }
}
