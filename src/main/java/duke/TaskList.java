package duke;

import duke.task.Task;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public TaskList filterTasks(String searchStr) {
        ArrayList<Task> tasks = new ArrayList<>(this.tasks);
        tasks.removeIf((task) -> !task.getDescription().toLowerCase().contains(searchStr.toLowerCase()));

        return new TaskList(tasks);
    }

    public int size() {
        return this.tasks.size();
    }

    public List<Task> items() {
        return new ArrayList<>(this.tasks);
    }
}
