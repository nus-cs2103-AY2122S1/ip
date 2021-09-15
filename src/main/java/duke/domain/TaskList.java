package duke.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class TaskList implements Iterable<Task> {
    private List<Task> tasks;
    private Set<Task> taskSet;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        taskSet = new HashSet<>();
    }

    /**
     * Creates a TaskList with contents from the given list.
     * This is meant to be used to create a temporary wrapper around a list, hence
     * the task set is not updated.
     * @param taskList List of tasks to be placed into task list.
     */
    public TaskList(List<Task> taskList) {
        tasks = taskList;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the list. Does not allow duplicates.
     *
     * @param task Task to be added to the list.
     * @return Whether a task is actually added. False if the task is a duplicate.
     */
    public boolean add(Task task) {
        if (taskSet.contains(task)) {
            return false;
        }
        tasks.add(task);
        taskSet.add(task);
        return true;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the given index.
     *
     * @param index Index of task to be removed.
     * @return The removed task.
     */
    public Task remove(int index) {
        Task removedTask = tasks.remove(index);
        assert removedTask != null;
        tasks.remove(removedTask);
        taskSet.remove(removedTask);
        return removedTask;
    }

    public Stream<Task> stream() {
        return tasks.stream();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
