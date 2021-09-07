package duke;

import duke.task.Task;

import java.util.ArrayList;

public abstract class DukeList {
    private final ArrayList<Task> taskArrayList;

    public DukeList() {
        this.taskArrayList = new ArrayList<>();
    }

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
     * @return tasks.
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
