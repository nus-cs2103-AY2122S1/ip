package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Encapsulates the task list object.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Creates new TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates new TaskList instance when tasks are loaded from storage.
     *
     * @param existingTasks Previously saved tasks.
     */
    public TaskList(List<Task> existingTasks) {
        this.tasks = (existingTasks == null ? new ArrayList<>() : existingTasks);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}
