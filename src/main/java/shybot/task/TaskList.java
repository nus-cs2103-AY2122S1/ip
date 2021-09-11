package shybot.task;

import java.util.ArrayList;

import shybot.Storage;
import shybot.exception.ShyBotIoException;

/**
 * TaskList class encapsulates a list of tasks.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Constructs a empty TaskList.
     */
    public TaskList() {
    }

    /**
     * Constructs a TaskList with the specified tasks.
     *
     * @param tasks Tasks to be contained in the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.addAll(tasks);
    }

    /**
     * Adds task to the TaskList and save it to db.
     *
     * @param task    Task to be added.
     * @param storage Storage used to save the task.
     * @return true (as specified by Collection.add).
     */
    public boolean add(Task task, Storage storage) throws ShyBotIoException {
        boolean result = super.add(task);
        if (result) {
            storage.save(task);
        }
        return result;
    }

    /**
     * Removes the task at the specified position in this list.
     *
     * @param index   The index of the task to be removed.
     * @param storage Storage used to save the new tasks after removing the specified task.
     * @return The Task that was removed from the list
     */
    public Task remove(int index, Storage storage) throws ShyBotIoException {
        assert index >= 0 : "`index` should be non-negative";
        assert index < super.size() : "`index` should be less than size of task list";
        Task task = super.remove(index);
        storage.save(this);
        return task;
    }

    /**
     * Marks the task as done and save the new list to db.
     *
     * @param task    Task to be marked as done.
     * @param storage Storage used to save the new tasks after marking the specified task as done.
     */
    public void markAsDone(Task task, Storage storage) throws ShyBotIoException {
        task.markAsDone();
        storage.save(this);
    }
}
