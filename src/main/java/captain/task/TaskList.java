package captain.task;

import java.util.ArrayList;
import java.util.Collections;

import captain.DukeException.MissingTaskException;

/**
 * Contains the task list.
 *
 * @author Adam Ho
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getListSize() {
        return taskList.size();
    }

    public Task getTaskById(int taskId) throws MissingTaskException {
        if (taskId < 1 || taskId > taskList.size()) {
            throw new MissingTaskException();
        }
        return taskList.get(taskId - 1);
    }
    /**
     * Adds a task to the list of tasks.
     * @param task The task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the list of tasks.
     * @param task The task to delete.
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Clears all tasks from the task list.
     */
    public void clearTaskList() {
        taskList.clear();
    }

    public void sortByTask() {
        Collections.sort(taskList);
    }
}
