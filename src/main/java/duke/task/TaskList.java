package duke.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Retrieves Task at the given index.
     *
     * @param index
     * @return Task at the given index
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds a given Task to the TaskList.
     *
     * @param task to add to the TaskList
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes Task at the given index.
     *
     * @param index
     * @return the deleted Task
     */
    public Task deleteTask(int index) {
        return this.taskList.remove(index);
    }

    /**
     * Returns the current number of tasks in the TaskList.
     *
     * @return number of tasks in the TaskList
     */
    public int size() {
        return this.taskList.size();
    }

}
