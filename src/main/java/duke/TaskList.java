package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

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

    /**
     * Filters tasks by their description.
     * @param searchStr The string to filter by.
     */
    public TaskList filterTasks(String searchStr) {
        String searchInput = searchStr.toLowerCase();
        ArrayList<Task> tasks = this.tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(searchInput))
                .collect(Collectors.toCollection(ArrayList::new));

        return new TaskList(tasks);
    }

    public int size() {
        return this.tasks.size();
    }

    public List<Task> items() {
        return new ArrayList<>(this.tasks);
    }
}
