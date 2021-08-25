package duke.tasks;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a collection of Tasks.
 *
 * @author ruiquan
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList given a list of Tasks.
     * @param tasks the list of Tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
      * @return the list of tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task into the TaskList.
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a Task at the specified position in the TaskList as completed and
     * returns the completed Task.
     * @param index the index of the Task in the TaskList to be marked as completed
     * @return the completed Task
     */
    public Task markTaskAsDone(int index) {
        return tasks.get(index - 1).markAsDone();
    }

    /**
     * Returns the number of Task in the TaskList.
     * @return the size of the TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Deletes a Task at the specified position in the TaskList and
     * returns the deleted Task.
     * @param index the index of the Task in the TaskList to be delted
     * @return the deleted Task
     */
    public Task deleteTask(int index) {
        return tasks.remove(index - 1);
    }

    public String find(String keyword) {
        List<Task> filteredTasks = tasks.stream().filter(task -> task.getDescription().contains(keyword)).collect(Collectors.toList());
        String str = String.format("Here are the matching %s in your list:\n", filteredTasks.size() <= 1 ? "task" : "tasks");
        StringBuilder result = new StringBuilder(str);
        int len = filteredTasks.size();
        if (len == 0) {
            return result.toString();
        }
        for (int i = 1; i < len; i++) {
            result.append(String.format("%s. %s\n", i, filteredTasks.get(i - 1)));
        }
        result.append(String.format("%s. %s", len, filteredTasks.get(len - 1)));
        return result.toString();
    }

    @Override
    public String toString() {
        String str = String.format("Here are the %s in your list:\n", tasks.size() <= 1 ? "task" : "tasks");
        StringBuilder result = new StringBuilder(str);
        int len = tasks.size();
        if (len == 0) {
            return result.toString();
        }
        for (int i = 1; i < len; i++) {
            result.append(String.format("%s. %s\n", i, tasks.get(i - 1)));
        }
        result.append(String.format("%s. %s", len, tasks.get(len - 1)));
        return result.toString();
    }
}
