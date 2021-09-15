package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents Tasks for Duke.
 */
public abstract class DukeList {
    private final ArrayList<Task> taskArrayList;

    /**
     * Constructor for DukeList.
     */
    public DukeList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Constructor for DukeList, which assigns the taskArrayList to a
     * specific ArrayList.
     *
     * @param taskArrayList ArrayList to be assigned to taskArrayList.
     */
    public DukeList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public abstract String type();

    /**
     * Adds a new Task to taskArrayList.
     *
     * @param toAdd Task to add to tasks.
     */
    public void add(Task toAdd) {
        this.taskArrayList.add(toAdd);
    }

    /**
     * Getter for taskArrayList.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    /**
     * Removes Task from given index from taskArrayList.
     *
     * @param index Index of Task to remove from taskArrayList.
     * @return The removed Task.
     */
    public Task remove(int index) {
        return this.taskArrayList.remove(index);
    }

    /**
     * Gets the size of taskArrayList.
     *
     * @return Size of tasks.
     */
    public int getSize() {
        return this.taskArrayList.size();
    }

    /**
     * Converts Task of a given index in tasks into its String representation.
     *
     * @param index Index of Task to get the String representation.
     * @return String representation of Task in tasks with given index.
     */
    public String taskToString(int index) {
        return this.taskArrayList.get(index).toString();
    }

    /**
     * Converts Task of a given index in tasks to its String representation to save.
     *
     * @param index Index of Task to get the String representation to save.
     * @return String representation of Task in tasks with given index.
     */
    public String taskSaveToString(int index) {
        return this.taskArrayList.get(index).convertToString();
    }

    /**
     * Gets the task at given index of the DukeList.
     *
     * @param index index which task is at in DukeList.
     * @return Task at index.
     */
    public Task get(int index) {
        return this.taskArrayList.get(index);
    }

    /**
     * Converts DukeList object into its String representation.
     *
     * @return String representation of TaskList object.
     */
    @Override
    public String toString() {
        return taskArrayList.toString();
    }
}
