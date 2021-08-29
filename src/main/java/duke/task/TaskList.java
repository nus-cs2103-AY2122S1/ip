package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates the list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructor of the duke.task.TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor of the duke.task.TaskList class, initialized with existing tasks.
     *
     * @param tasks the tasks to be initialized with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Deletes the task by task id.
     *
     * @param id The id of the task.
     * @return The deleted task.
     * @throws InvalidTaskSelectedException Thrown if the task id was invalid.
     */
    public Task deleteTaskById(int id) throws InvalidTaskSelectedException {
        this.validateTaskId(id);
        int index = id - 1;
        return this.tasks.remove(index);
    }

    /**
     * Adds task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the task list.
     *
     * @return The resulting task list.
     */
    public List<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Get a task by its id.
     *
     * @param id The id of the task.
     * @return The resulting task.
     * @throws InvalidTaskSelectedException Thrown if the task id was invalid.
     */
    public Task getTaskById(int id) throws InvalidTaskSelectedException {
        this.validateTaskId(id);
        int index = id - 1;
        return this.tasks.get(index);
    }

    /**
     * Finds a task by a query string.
     *
     * @param query The query string.
     * @return The list of tasks.
     */
    public List<Task> find(String query) {
        List<Task> matches = new ArrayList<>();
        for (Task task : this.tasks) {
            String description = task.getDescription();
            if (description.contains(query)) {
                matches.add(task);
            }
        }

        return matches;
    }

    private void validateTaskId(int id) throws InvalidTaskSelectedException {
        int index = id - 1;
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskSelectedException();
        }
    }
}
