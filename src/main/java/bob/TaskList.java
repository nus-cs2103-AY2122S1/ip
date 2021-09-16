package bob;

import java.util.ArrayList;

import bob.exception.NoSearchResultException;
import bob.task.Task;

/**
 * Represents the user's list of tasks to be completed.
 */
public class TaskList {
    /** List of user tasks to keep track of. */
    private ArrayList<Task> taskList;

    /**
     * Constructor for a new empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for a new TaskList containing the provided tasks.
     *
     * @param tasks Array containing the tasks to be added to the new TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds the given task to the current list.
     *
     * @param task Task to be added to the current TaskList.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the current list of tasks.
     *
     * @return Current list of tasks as a String.
     */
    public String getList() {
        String result = "";
        for (int index = 0; index < this.taskList.size(); index++) {
            String currTask = this.taskList.get(index).printTask();
            String currNumber = (index + 1) + ".";
            result = result + currNumber + currTask + "\n";
        }
        return result;
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param index The index of the task in the list to be marked as completed.
     * @return String representation of the completed task.
     */
    public String markIndexCompleted(int index) {
        assert (index < this.taskList.size() && index > -1);
        Task selectedTask = this.taskList.get(index);
        selectedTask.markCompleted();
        return selectedTask.printTask();
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task in the list to be deleted.
     * @return String representation of the deleted task.
     */
    public String deleteIndex(int index) {
        assert (index < this.taskList.size() && index > -1);
        Task selectedTask = this.taskList.get(index);
        this.taskList.remove(index);
        return selectedTask.printTask();
    }

    /**
     * Returns the number of tasks in the list currently.
     *
     * @return The number of tasks in the list as a String.
     */

    public String getNoOfTasks() {
        return Integer.toString(this.taskList.size());
    }

    /**
     * Returns the task at the specified index from the list.
     *
     * @param index The index of the task in the list to be returned.
     * @return Specified task.
     */
    public Task getTask(int index) {
        assert (index < this.taskList.size() && index > -1);
        return this.taskList.get(index);
    }

    /**
     * Returns a list of tasks from the task list that contain a specific keyword.
     *
     * @param keyword Keyword provided by the user to search for in the list of tasks.
     * @return List of tasks from within the task list that contain the given keyword.
     * @throws NoSearchResultException If there are no tasks in the list that contain the given keyword.
     */
    public String searchList(String keyword) throws NoSearchResultException {
        String result = "";
        int noOfResults = 0;
        for (int index = 0; index < this.taskList.size(); index++) {
            String currTask = this.taskList.get(index).printTask();
            if (currTask.contains(keyword)) {
                String currNumber = (noOfResults + 1) + ".";
                result = result + currNumber + currTask + "\n";
                noOfResults++;
            }
        }
        if (noOfResults == 0) {
            throw new NoSearchResultException();
        } else {
            return result;
        }
    }
}
