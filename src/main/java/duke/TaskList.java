package duke;

import java.util.ArrayList;

/**
 * A class to handle a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList object if there is previously stored data.
     *
     * @param storage stored data
     */
    public TaskList(ArrayList<Task> storage) {
        this.tasks = storage;
    }

    /**
     * Constructor for TaskList object if there is no previously stored data.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to this TaskList.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets a task from this TaskList.
     *
     * @param i the task index (task number -1)
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Deletes a task from this TaskList.
     *
     * @param i the task number to be deleted
     * @return the deleted task
     */
    public Task removeTask(int i) {
        return this.tasks.remove(i);
    }

    /**
     * Checks if this TaskList contains a specific Task object.
     *
     * @param task the Task to be checked
     * @return true if the Task object is inside this TaskList
     */
    public boolean containsTask(Task task) {
        return this.tasks.contains(task);
    }

    /**
     * Returns the number of tasks in this TaskList.
     *
     * @return the number of tasks
     */
    public int numOfTasks() {
        return tasks.size();
    }

    public int getTaskNum(Task task) {
        int taskNum = -1;
        for (int i = 0; i < tasks.size(); i++) {
            if (task == tasks.get(i)) {
                taskNum = i + 1;
            }
        }
        return taskNum;
    }

    /**
     * Returns the list of tasks in this TaskList
     *
     * @return a list of existing tasks
     */
    @Override
    public String toString() {
        String list = "";

        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            int taskNum = i + 1;
            list = list + "\n" + taskNum + "." + curr.toString();
        }

        return list;
    }
}
