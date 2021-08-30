package duke;

import java.util.ArrayList;
import java.util.List;

import task.Task;

/**
 * Contains the task list and operations to add/delete tasks in the list.
 *
 * @author felix-ong
 */
public class TaskList {
    /** List of tasks */
    private List<Task> tasks;

    /**
     * Constructor of TaskList.
     *
     * @param tasks The list of tasks to be used.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor of TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the size of the list of tasks with the appropriate grammar.
     * @return A String describing the size of the list of tasks with the appropriate grammar.
     */
    public String getTaskCount() {
        int taskCount = this.tasks.size();
        return String.format("You have %d %s in the list.%n", taskCount, taskCount > 1 ? "tasks" : "task");
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds the given task to the given list of tasks.
     *
     * @param storage The storage object to handle saving tasks.
     * @param task The task to be added to the list of tasks.
     */
    public void addTask(Storage storage, Task task) throws DukeException {
        this.tasks.add(task);
        storage.saveData(this.tasks);
    }

    /**
     * Deletes a task as done and saves the new list of tasks to the file.
     *
     * @param storage The storage object to handle saving tasks.
     * @param index The index of the task to mark as done.
     * @return The deleted task.
     * @throws DukeException If index is out of range.
     */
    public Task deleteTask(Storage storage, String index) throws DukeException {
        if (index.isBlank()) {
            throw new DukeException("☹ OOPS!!! Please provide the index of the "
                    + "task you want to delete.");
        }
        int deleteTaskIndex = Integer.parseInt(index) - 1;
        Task toBeDeleted = this.tasks.get(deleteTaskIndex);
        this.tasks.remove(deleteTaskIndex);
        storage.saveData(this.tasks);
        return toBeDeleted;
    }

    /**
     * Marks a task as done and saves it to the file.
     *
     * @param storage The storage object to handle saving tasks.
     * @param index The index of the task to mark as done.
     * @return The task marked as done.
     * @throws DukeException If index is out of range.
     */
    public Task doneTask(Storage storage, String index) throws DukeException {
        if (index.isBlank()) {
            throw new DukeException("☹ OOPS!!! Please provide the index of the "
                    + "task you want to mark as done.");
        }
        int taskIndex = Integer.parseInt(index) - 1;
        Task doneTask = this.tasks.get(taskIndex);
        doneTask.markAsDone();
        storage.saveData(this.tasks);
        return doneTask;
    }

    /**
     * Returns a list of tasks with description containing the keyword.
     *
     * @param keyword Word to search for in task description.
     * @return List of tasks with description containing the keyword.
     */
    public List<Task> findTask(String keyword) {
        List<Task> matches = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }
}
