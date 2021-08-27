package duke;

import java.util.ArrayList;

/**
 * TaskList is the class that contains the list of tasks.
 * It provides operations to add and delete tasks from the list,
 * and mark tasks as complete.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Class constructor.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Class constructor specifying an initial list of tasks.
     *
     * @param tasks the list of tasks to initialise with.
     */
    public TaskList(ArrayList<Task> tasks) {
        if (tasks != null)
            this.tasks = tasks;
        else
            this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int taskNum) {
        return tasks.get(taskNum - 1);
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param newTask the new task to be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Marks a task in the list as done.
     *
     * @param taskNum the number of the task to be marked as done.
     * @return the task that has been marked as done.
     */
    public Task completeTask(int taskNum) {
        tasks.get(taskNum - 1).setDone(true);
        return tasks.get(taskNum - 1);
    }

    /**
     * Deletes a task from the list.
     *
     * @param taskNum the number of the task to be deleted from the list.
     * @return the task that has been deleted from the list.
     */
    public Task deleteTask(int taskNum) {
        return tasks.remove(taskNum - 1);
    }
}
