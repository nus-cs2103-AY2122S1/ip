package duke;

import duke.task.Task;

/**
 * TaskList class handles the task list of Duke.
 */
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs the TaskList object.
     *
     * @param tasks Arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param i Index of task in task list.
     * @return Task in task list.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Removes a task from the task list.
     *
     * @param i Index of task in task list.
     * @return Task removed from task list.
     */
    public Task removeTask(int i) {
        return tasks.remove(i);
    }

    /**
     * Retrieves the task list.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns String representation of task list.
     *
     * @return String representation of task list.
     */
    public String getTaskList() {
        String list = "These are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            list += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return list;
    }

    /**
     * Returns String representation of list status.
     *
     * @return String representation of list status.
     */
    public String getListStatus() {
        if (tasks.size() == 0) {
            return "There are no tasks in the list\n";
        } else if (tasks.size() == 1) {
            return "\nThere is currently 1 task in your list\n";
        } else {
            return String.format("\nThere are currently %d tasks in your list\n", tasks.size());
        }
    }
}
