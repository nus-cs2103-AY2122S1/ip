package duke.task;

import java.util.ArrayList;
import java.util.List;

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
     * Adds a task into the tasks list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
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
