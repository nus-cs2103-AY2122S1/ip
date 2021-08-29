package duke;

import java.util.ArrayList;
import java.util.List;

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
     * Prints the size of the list of tasks with the appropriate grammar.
     */
    public int printSize() {
        int taskCount = this.tasks.size();
        System.out.printf("You have %d %s in the list.%n", taskCount, taskCount > 1 ? "tasks" : "task");
        return taskCount;
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
        System.out.println("Added task:\n " + task);
        this.printSize();
    }

    /**
     * Deletes a task as done and saves the new list of tasks to the file.
     *
     * @param storage The storage object to handle saving tasks.
     * @param index The index of the task to mark as done.
     * @throws DukeException If index is out of range.
     */
    public void deleteTask(Storage storage, String index) throws DukeException {
        if (index.isBlank()) {
            throw new DukeException("☹ OOPS!!! Please provide the index of the "
                    + "task you want to delete.");
        }
        int deleteTaskIndex = Integer.parseInt(index) - 1;
        Task toBeDeleted = this.tasks.get(deleteTaskIndex);
        this.tasks.remove(deleteTaskIndex);
        storage.saveData(this.tasks);
        System.out.printf("Noted! I have removed the following task:%n %s%n", toBeDeleted);
        this.printSize();
    }

    /**
     * Marks a task as done and saves it to the file.
     *
     * @param storage The storage object to handle saving tasks.
     * @param index The index of the task to mark as done.
     * @throws DukeException If index is out of range.
     */
    public void doneTask(Storage storage, String index) throws DukeException {
        if (index.isBlank()) {
            throw new DukeException("☹ OOPS!!! Please provide the index of the "
                    + "task you want to mark as done.");
        }
        int taskIndex = Integer.parseInt(index) - 1;
        Task doneTask = this.tasks.get(taskIndex);
        doneTask.markAsDone();
        storage.saveData(this.tasks);
        System.out.printf("Good job! I have marked the following task as done:%n %s%n", doneTask);
    }

    /**
     * Returns a list of tasks with description containing the keyword.List of tasks with
     * description containing the keyword.
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
