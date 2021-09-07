package duke.task;

import java.util.List;

/**
 * The TaskList class encapsulates information
 * and methods pertaining to how Tasks are listed in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class TaskList {
    private List<Task> tasks;

    /**
    * Creates and initalizes a new TaskList with the given list of tasks.
    *
    * @param tasks The list of tasks for the TaskList to be initialised with.
    * @return A new TaskList object.
    */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
    * Retrieves and returns the Task at the specified index of the tasklist.
    *
    * @param index The specified index to retrieve the task from.
    * @return The task at index.
    */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
    * Marks the Task at the specified index of the tasklist as done.
    *
    * @param index The index of the task to be marked as done.
    */
    public void markDone(int index) {
        this.get(index).markDone();
    }

    /**
    * Returns the list of tasks held by this object.
    *
    * @return The list of tasks.
    */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
    * Appends the specified task to the back of the task list.
    *
    * @param task The task to be added.
    */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
    * Removes the Task at the specified index of the tasklist.
    *
    * @param index The index of the task to be removed.
    */
    public void delete(int index) {
        this.tasks.remove(index);
    }

    /**
    * Returns the numeber of tasks held.
    *
    * @return Returns the numeber of tasks held.
    */
    public int size() {
        return this.tasks.size();
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
    * Returns true if there are no tasks in the list.
    *
    * @return Returns true if there are no tasks in the list.
    */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
