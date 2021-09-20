package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of any number of Tasks, including zero.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates a TaskList from a list of Tasks.
     * @param tasks The list of Tasks containing the Tasks this TaskList will have.
     */
    public TaskList(List<? extends Task> tasks) {
        taskList = new ArrayList<>();
        for (Task task : tasks) {
            taskList.add(task);
        }
    }

    /**
     * Adds a Task to this TaskList.
     * @param task The Task to be added to this TaskList
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Finds a Task with the same name as a supplied String.
     * If multiple are present, the first one is returned.
     * @param taskName The name of the Task to find
     * @return A Task with the same name, or <code>null</code> if none was found.
     */
    public Task find(String taskName) {
        for (Task task: taskList) {
            if (task.getName().equals(taskName)) {
                return task;
            }
        }
        return null;
    }

    /**
     * Finds all Tasks containing a supplied String.
     * If multiple are present, the first one is returned.
     * @param keyword The name of the Task to find
     * @return A Task with the same name, or <code>null</code> if none was found.
     */
    public TaskList match(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }



    /**
     * Removes a Task with the same name as a supplied String
     * If multiple are present, the first is deleted
     * @param taskName The name of the Task to be deleted
     * @return The deleted Task, or <code>null</code> if none were deleted
     */
    public Task delete(String taskName) {
        Task taskToDelete = find(taskName);
        if (taskToDelete != null){
            taskList.remove(taskToDelete);
        }
        return taskToDelete;
    }

    /**
     * Converts this TaskList to a String in CSV format
     * @return A CSV representation of this TaskList
     */
    public String toCsvString() {
        return String.join("\n", taskList.stream()
                .map(task -> task.toCsvRow())
                .collect(Collectors.toList()));
    }

    /**
     * Returns all tasks in this TaskList
     * @return A shallow copy of the Tasks in this Tasklist
     */
    public List<Task> getTasks() {
        return new ArrayList<Task>(taskList);
    }

    @Override
    public String toString() {
        return String.join("\n", taskList.stream()
                .map(task -> task.toString())
                .collect(Collectors.toList()));
    }
}
