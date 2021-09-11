package chad.task;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Helper class to handle all the tasks in the tasks list.
 *
 * @author Jay Aljelo Saez Ting
 */
public class TaskHandler {

    private List<Task> tasks;
    private List<TasksListUpdateObserver> tasksListUpdateObservers;

    /**
     * Represents an observer that will get notified when the tasks list and/or any tasks inside is updated.
     *
     * @author Jay Aljelo Saez Ting
     */
    public interface TasksListUpdateObserver {
        /**
         * Performs actions when the tasks list is updated.
         *
         * @param tasks The updated tasks list.
         */
        void onTasksListUpdated(List<Task> tasks);
    }

    /**
     * Creates a TaskHandler instance.
     *
     * @param tasks The list of tasks to be managed by the instance.
     */
    public TaskHandler(List<Task> tasks) {
        assert tasks != null : "Tasks list cannot be null.";
        this.tasks = tasks;
        this.tasksListUpdateObservers = new ArrayList<>();
    }

    /**
     * Adds a TasksListUpdateObserver instance to keep track of.
     *
     * @param tasksListUpdateObserver The TasksListUpdateObserver instance.
     */
    public void addTasksListUpdateObserver(TasksListUpdateObserver tasksListUpdateObserver) {
        tasksListUpdateObservers.add(tasksListUpdateObserver);
    }

    /**
     * Appends a task to the end of the tasks list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        notifyObservers();
    }

    /**
     * Inserts a task into the tasks list at the passed index.
     *
     * @param index The index in the tasks list at which the task is to be inserted.
     * @param task The task to be added.
     */
    public void insertTask(int index, Task task) {
        tasks.add(index, task);
        notifyObservers();
    }

    /**
     * Gets the number of tasks in the tasks list.
     *
     * @return The number of tasks in the tasks list.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Marks the task at the given tasks list index as done.
     *
     * @param taskIndex The tasks list index of the task.
     * @return The task that was marked done.
     */
    public Task markTaskDone(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.markDone();
        notifyObservers();
        return task;
    }

    /**
     * Un-marks the task at the given tasks list index as done.
     *
     * @param taskIndex The tasks list index of the task.
     * @return The task that was un-marked done.
     */
    public Task unmarkTaskDone(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.unmarkDone();
        notifyObservers();
        return task;
    }

    /**
     * Deletes the task at the given tasks list index.
     *
     * @param taskIndex The tasks list index of the task.
     * @return The task that was deleted.
     */
    public Task deleteTask(int taskIndex) {
        Task task = tasks.remove(taskIndex);
        notifyObservers();
        return task;
    }

    /**
     * Finds matching tasks in the tasks list with matching task descriptions.
     *
     * @param queryTaskDescription The query task description (or part of it) to be matched.
     * @return The list of pairs of the matching tasks and their indices in the tasks list.
     */
    public List<Map.Entry<Integer, Task>> findTasksDescribedBy(String queryTaskDescription) {
        List<Map.Entry<Integer, Task>> queryResults = new ArrayList<>();
        int n = tasks.size();
        for (int i = 0; i < n; i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(queryTaskDescription)) {
                queryResults.add(new AbstractMap.SimpleEntry<>(i, task));
            }
        }
        return queryResults;
    }

    /**
     * Gets the tasks list.
     *
     * @return The tasks list.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    private void notifyObservers() {
        for (TasksListUpdateObserver tasksListUpdateObserver : tasksListUpdateObservers) {
            tasksListUpdateObserver.onTasksListUpdated(tasks);
        }
    }
}
