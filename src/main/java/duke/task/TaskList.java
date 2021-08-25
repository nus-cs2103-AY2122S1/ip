package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public List<Task> getList() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public List<String> enumerate() {
        List<String> numList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            numList.add((i + 1) + ". " + tasks.get(i));
        }
        return numList;
    }
}
