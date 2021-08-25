package duke.tasks;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskList {
    private final ArrayList<Task> tasksList;

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * Appends specified task to the end of the list.
     *
     * @param t new Task
     * @return boolean - has adding of task succeeded.
     */
    public boolean add(Task t) {
        return tasksList.add(t);
    }

    /**
     * Returns the task at the specified position in this list.
     *
     * @param index index of the task to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if there is no such index in list.
     */
    public Task get(int index) {
        return tasksList.get(index);
    }

    /**
     * Removes all tasks from internal list. List will be empty after this call.
     */
    public void clear() {
        tasksList.clear();
    }

    /**
     * Removes a specific task from internal list. List will be empty after this call.
     *
     * @param index index of the task to return
     * @throws IndexOutOfBoundsException if there is no such index in list.
     */
    public void remove(int index) {
        tasksList.remove(index);
    }

    /**
     * Returns number of tasks in list.
     *
     * @return integer number of tasks in list.
     */
    public int size() {
        return tasksList.size();
    }

    /**
     * Returns stream of Tasks in the list.
     *
     * @return stream of Tasks in the list
     */
    public Stream<Task> stream() {
        return tasksList.stream();
    }

}
