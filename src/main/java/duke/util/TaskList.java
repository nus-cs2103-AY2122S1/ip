package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {

    private final ArrayList<Task> list;

    /**
     * Default constructor for a TaskList.
     *
     * @param tasks The ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        list = tasks;
    }

    /**
     * Constructor for an empty TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Gets the Task with the specified index.
     *
     * @param index The index of the Task we want to get.
     * @return The Task with the specified index.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Adds the specified Task into the TaskList.
     *
     * @param task The task to be added into the TaskList.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes the specified Task from the TaskList.
     *
     * @param index The index of the Task we want to delete.
     */
    public void delete(int index) {
        try {
            list.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e);
        }
    }

    /**
     * Gives the size of the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return list.size();
    }

    /**
     * Applies n action to every element in the TaskList.
     *
     * @param action The action we want to carry out on the elements in the TaskList.
     */
    public void forEach(Consumer<? super Task> action) {
        list.forEach(action);
    }

}
