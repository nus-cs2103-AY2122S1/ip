package task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructs the tasklist using the Arraylist of task specified.
     *
     * @param t the Arraylist of task.
     */
    public TaskList(ArrayList<Task> t) {
        this.taskList = t;
    }

    /** Constructs an empty tasklist */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds a task into the tasklist.
     *
     * @param taskToAdd the task object to be added.
     */
    public void addTask(Task taskToAdd) {
        taskList.add(taskToAdd);
    }

    /**
     * Removes a task from the tasklist.
     *
     * @param index index of the task to be removed.
     * @return the task object being removed.
     */
    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    /**
     * Marks as task as completed.
     *
     * @param index the index of the task in the list.
     */
    public void completeTask(int index) {
        Task taskToComplete = taskList.get(index);
        taskToComplete.markIsDone();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks in the tasklist in int.
     *
     * @return the number of tasks in the tasklist in int value.
     */
    public int size() {
        return taskList.size();
    }
}
