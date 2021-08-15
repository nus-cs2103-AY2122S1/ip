import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskList {
    private final List<DukeTask> list;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<DukeTask> list) {
        this.list = list;
    }

    public Collection<DukeTask> getTasks() {
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

    public int indexOf(DukeTask task) {
        return list.indexOf(task);
    }

    public int size() {
        return list.size();
    };
}
