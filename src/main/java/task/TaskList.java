package task;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList represents the task list.
 *
 * @author Ho Wen Zhong
 */
public class TaskList {

    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns size of the TaskList.
     *
     * @return Size of TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns String representation of TaskList.
     *
     * @return String representation of TaskList.
     */
    public String list() {
        String message = "Here are the tasks in your list:\n";
        int count = 1;
        for (Task t : taskList) {
            message = message + Integer.toString(count) + ". "
                    + t.toString() + "\n";
            count++;
        }
        return message;
    }

    /**
     * Sets a Task in TaskList as done.
     *
     * @param index The index of the Task done.
     */
    public void done(int index) {
        taskList.get(index).setDone();
    }

    /**
     * Adds a Task to TaskList.
     *
     * @param task The Task to add.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a Task in TaskList.
     *
     * @param index The index of the Task to delete.
     */
    public void delete(int index) {
        taskList.remove(index);
    }

    /**
     * Returns a Task in TaskList.
     *
     * @param index The index of the Task to return.
     * @return The specified Task.
     */
    public Task get(int index) {
        Task task = taskList.get(index);
        return task;
    }

    public String find(String query) {
        String message = "Here are the matching tasks in your list:\n";
        int count = 1;
        for (Task t : taskList) {
            if (t.toString().contains(query)) {
                message = message + Integer.toString(count) + ". "
                        + t.toString() + "\n";
                count++;
            }
        }
        return message;
    }
}
