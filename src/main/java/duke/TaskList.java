package duke;

import java.util.*;
import java.util.stream.Collectors;

public class TaskList {
    List<Task> taskList;

    /**
     * Creates a TaskList Object with no tasks.
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates a TaskList Object with tasks.
     *
     * @param tasks The tasks the user has.
     */
    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds an item to the task list.
     *
     * @param t The task to be added by the user.
     */
    public void addItem(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes an item from the task list.
     *
     * @param index The index of the item to be deleted.
     * @return The task that was just deleted.
     */
    public Task deleteItem(int index) {
        assert index >= 0 && index < getSize() : "Index of item to be deleted is out of bounds";
        Task t = getTask(index);
        taskList.remove(index);

        return t;
    }

    /**
     * Gets an item from the task list based on the index of the item.
     *
     * @param index The index of the item.
     * @return The item at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < getSize() : "Index of item to be retrieved is out of bounds";
        return taskList.get(index);
    }

    public int getSize() { return taskList.size(); }

    /**
     * Finds matching tasks from the task list based on the keyword provided.
     *
     * @param keyword The keyword provided by the user.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> find (String keyword) {
        return taskList.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked done.
     */
    public void markAsDone(int index) {
        Task t = getTask(index);
        t.setDone();
    }

    public boolean contains(Task t) {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = getTask(i);
            if (task.equals(t)) {
                return true;
            }
        }
        return false;
    }
}
