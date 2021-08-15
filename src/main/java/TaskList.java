import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<DukeTask> list;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<DukeTask> list) {
        this.list = list;
    }

    public Iterable<DukeTask> getTasks() {
        return this.list;
    }

    public DukeTask getTaskAt(int index) {
        return list.get(index);
    }

    public DukeTask removeTaskAt(int index) {
        return list.remove(index);
    }

    public void addTask(DukeTask task) {
        list.add(task);
    }
}
