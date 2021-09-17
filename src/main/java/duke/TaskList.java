package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

public class TaskList {
    private final List<Task> tasks;

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

    /**
     * Filters tasks by their description.
     * @param searchStr The string to filter by.
     */
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
