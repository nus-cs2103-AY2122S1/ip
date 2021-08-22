package duke;

import java.util.List;

/**
 * Represents collection of tasks
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs instance of <code>TaskList</code>
     * @param tasks List of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns list of tasks
     * @return List of tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds task to list
     * @param task Task to add
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Replaces task at index
     * @param index Index of task to be replaced
     * @param task Task to replace with
     */
    public void replaceTask(int index, Task task) {
        if (index >= this.tasks.size() || index < 0) {
            DukeException exception = new DukeException("Number out of range!");
            System.out.println(exception);
        }
        else {
            this.tasks.set(index, task);
        }
    }

    /**
     * Deletes task at index
     * @param index Index of task to be deleted
     */
    public void deleteTask(int index) {
        if (index >= this.tasks.size() || index < 0) {
            DukeException exception = new DukeException("Number out of range!");
            System.out.println(exception);
        }
        else {
            this.tasks.remove(index);
        }
    }
}
