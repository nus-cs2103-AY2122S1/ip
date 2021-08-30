package bobcat.model;

import java.util.ArrayList;

import bobcat.exception.InvalidOpsException;
import bobcat.model.task.Task;

public class TaskList {
    private final ArrayList<Task> storage;
    private int index;

    /**
     * Constructor for a task list
     */
    public TaskList() {
        storage = new ArrayList<>();
        index = 0;
    }

    /**
     * Adds a <code>Task</code> to the end of a <code>TaskList</code>
     * @param t task to be added
     * @return task to be added
     */
    public Task push(Task t) {
        storage.add(t);
        index += 1;
        return storage.get(index - 1);
    }

    /**
     * Marks a given task as completed
     * @param id ID of the task to be marked
     * @return the Task to be marked
     */
    public Task markDone(int id) {
        if (id >= index || id < 0) {
            throw new InvalidOpsException("Provided Index is out of bounds! Given index is " + (
                    id + 1) + " but there are " + index + " elements in the list");
        }
        storage.get(id).markDone();
        return storage.get(id);
    }

    public Task[] getAllTasks() {
        return storage.toArray(new Task[0]);
    }

    /**
     * Deletes a task by its given id
     * @param id ID of the task to be deleted
     * @return Task to be deleted
     */
    public Task deleteTaskByIdx(int id) {
        if (id >= index || id < 0) {
            throw new InvalidOpsException("Provided Index is out of bounds!");
        }
        index -= 1;
        return storage.remove(id);
    }

    /**
     * Returns an array of <code>Task</code> whose descriptions contain the given <i>name</i>
     * @param name String to be searched in the description of <code>Task</code> in <code>TaskList</code>
     * @return Array of <code>Task</code>
     */
    public Task[] findByName(String name) {
        return storage.stream().filter(x -> x.toString().contains(name)).toArray(Task[]::new);
    }

    public int numTasks() {
        return index;
    }

}
