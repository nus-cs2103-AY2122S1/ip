package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the list of Tasks for Duke.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList, setting tasks to a given list.
     *
     * @param list List to be assigned to tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Adds a new Task to tasks.
     *
     * @param toAdd Task to add to tasks.
     */
    public void add(Task toAdd) {
        this.tasks.add(toAdd);
    }

    /**
     * Getter for tasks.
     *
     * @return tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Removes Task from given index from tasks.
     *
     * @param index Index of Task to remove from tasks.
     * @return The removed Task.
     */
    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Gets the size of tasks.
     *
     * @return Size of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Converts Task of a given index in tasks into its String representation.
     *
     * @param index Index of Task to get the String representation.
     * @return String representation of Task in tasks with given index.
     */
    public String taskToString(int index) {
        return this.tasks.get(index).toString();
    }

    /**
     * Converts Task of a given index in tasks to its String representation to save.
     *
     * @param index Index of Task to get the String representation to save.
     * @return String representation of Task in tasks with given index.
     */
    public String taskSaveToString(int index) {
        return this.tasks.get(index).convertToString();
    }

    /**
     * Marks Task at given index in tasks as done.
     *
     * @param index Index of Task to mark as done.
     */
    public void markAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Finds tasks with given keyword in tasks.
     *
     * @param keyword Keyword of tasks we want to find.
     * @return TaskList of Tasks with given keyword.
     */
    public TaskList findTasksWithKeyword(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).hasKeyword(keyword)) {
                tasksWithKeyword.add(this.tasks.get(i));
            }
        }

        return new TaskList(tasksWithKeyword);
    }

    /**
     * Converts TaskList object into its String representation.
     *
     * @return String representation of TaskList object.
     */
    @Override
    public String toString() {
        return tasks.toString();
    }
}
