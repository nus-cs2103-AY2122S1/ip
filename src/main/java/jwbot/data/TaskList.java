package jwbot.data;

import jwbot.data.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> items;

    public TaskList(List<Task> items) {
        this.items = items;
    }

    public TaskList() {
        items = new ArrayList<>();
    }

    public List<Task> getItems() {
        return items;
    }

    public Task remove(int index) {
        return items.remove(index);
    }

    public int getSize() {
        return items.size();
    }

    public void addTask(Task task) {
        items.add(task);
    }

    public Task getTask(int index) {
        return items.get(index);
    }
}
