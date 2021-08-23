package bern.functionalities;

import bern.model.Task;

import java.util.ArrayList;

/**
 * A class to encapsulates all methods TaskList related.
 */
public class TaskList {
    /**
     * A method to add task into an ArrayList of Tasks.
     *
     * @param task The additional task.
     * @param arListTask The initial ArrayList of Tasks.
     */
    public void addTask(Task task, ArrayList<Task> arListTask) {
        arListTask.add(task);
    }

    /**
     * A method to remove a task from an ArrayList of Tasks.
     *
     * @param index The index of the task to be removed.
     * @param arListTask The initial ArrayList of Tasks.
     */
    public void removeTask(int index, ArrayList<Task> arListTask) {
        arListTask.remove(index);
    }
}