package duke;

import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    /**
     * Returns a TaskList object. IOException is thrown if file does not
     * exist and is unable to be created.
     *
     * @param storage Storage object used to read and write tasks from file
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.load();
    }

    /**
     * Marks task at postition idx - 1 as complete.
     *
     * @param idx 1-based index of the task to mark as complete
     */
    public void markComplete(int idx) {
        tasks.get(idx - 1).markComplete();
        storage.saveToFile(tasks);
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Inserts the specified task at the end of the list.
     *
     * @param task task to be inserted
     */
    public void add(Task task) {
        tasks.add(task);
        storage.appendToFile(task);
    }

    /**
     * Returns the task at the specified position in this list.
     *
     * @param idx index of the task to return
     * @return the task at the specified position in this list
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Removes the task at the specified position in this list.
     *
     * @param idx index of the task to remove
     */
    public void remove(int idx) {
        tasks.remove(idx);
        storage.saveToFile(tasks);
    }

    /**
     * Returns string form of all tasks whose name contains the given query.
     *
     * @param query string to match upon
     * @return string representation of all tasks whose names contain the given query
     */
    public String getMatches(String query) {
        return String.join("\n", tasks.stream().filter(task -> task.getName().contains(query))
                .map(Task::toString).toArray(String[]::new));
    }
}
