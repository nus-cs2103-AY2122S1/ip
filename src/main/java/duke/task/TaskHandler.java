package duke.task;

import duke.ui.MessageFormatter;

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

    public interface TasksListUpdateObserver {
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

    public void addTasksListUpdateObserver(TasksListUpdateObserver tasksListUpdateObserver) {
        tasksListUpdateObservers.add(tasksListUpdateObserver);
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyObservers();
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

//    public String getTasksString(MessageFormatter messageFormatter) {
//        return messageFormatter.formatTasksList(tasks);
//    }

    public Task markTaskDone(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.markDone();
        notifyObservers();
        return task;
    }

    public Task deleteTask(int taskIndex) {
        Task task = tasks.remove(taskIndex);
        notifyObservers();
        return task;
    }

    private void notifyObservers() {
        for (TasksListUpdateObserver tasksListUpdateObserver : tasksListUpdateObservers) {
            tasksListUpdateObserver.onTasksListUpdated(tasks);
        }
    }
}
