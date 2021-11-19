package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * TaskList class that contains the current list of tasks in the application.
 */
public class TaskList {
    private ArrayList<Task> taskArr = new ArrayList<>();

    /**
     * Class constructor with the ArrayList of tasks.
     *
     * @param tasks The initial ArrayList of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        taskArr = tasks;
    }

    /**
     * Method to add tasks into the task list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        taskArr.add(t);
    }

    /**
     * A getter function to obtain the task at index i.
     *
     * @param i The index to obtain the task from.
     * @return The task at index i.
     */
    public Task get(int i) {
        assert(i >= 0);
        return taskArr.get(i);
    }

    /**
     * Method to remove tasks from the task list at index i.
     *
     * @param i The index to remove the task from.
     * @return The task that has been removed.
     */
    public Task remove(int i) {
        assert(i >= 0);
        return taskArr.remove(i);
    }

    /**
     * Method to obtain the number of tasks in the task list.
     *
     * @return The number of items in the task list as an integer.
     */
    public int getSize() {
        return taskArr.size();
    }
}
