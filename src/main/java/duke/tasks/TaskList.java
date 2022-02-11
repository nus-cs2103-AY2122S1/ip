package duke.tasks;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Internal representation of list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Appends specified task to the end of the list.
     *
     * @param t new Task
     * @return boolean - has adding of task succeeded.
     */
    public boolean add(Task t) {
        return tasks.add(t);
    }

    /**
     * Returns the task at the specified position in this list.
     *
     * @param index index of the task to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if there is no such index in list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes all tasks from internal list. List will be empty after this call.
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Removes a specific task from internal list. List will be empty after this call.
     *
     * @param index index of the task to return
     * @throws IndexOutOfBoundsException if there is no such index in list.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Returns number of tasks in list.
     *
     * @return integer number of tasks in list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns stream of Tasks in the list.
     *
     * @return stream of Tasks in the list
     */
    public Stream<Task> stream() {
        return tasks.stream();
    }

}
