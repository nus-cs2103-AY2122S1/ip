package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the idea of a list of tasks stored in an ArrayList
 */
public class TaskList {

    // instance variables

    private ArrayList<Task> tasks;

    // constructors

    /**
     * Construct an instance of a TaskList
     *
     * @param tasks the initial ArrayList to be used
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Get the task at the given index
     *
     * @param index the index of the task to return
     * @return the task at specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    public List<Task> getList() {
        return tasks;
    }

    /**
     * Appends a task to end of the list
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task at the given index
     *
     * @param index the index of the task to delete
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Enumerates (starting from 1) tasks in list using their string representations,
     * and stores such enumerations in a list that is to be returned
     *
     * @return list of enumerations
     */
    public List<String> enumerate() {
        List<String> numList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            numList.add((i + 1) + ". " + tasks.get(i));
        }
        return numList;
    }
}
