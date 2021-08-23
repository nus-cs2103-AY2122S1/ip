package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this(new ArrayList<Task>());
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Stream<Task> stream() {
        return this.tasks.stream();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public Task remove(int i) {
        return this.tasks.remove(i);
    }

    public int size() {
        return this.tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }
}
