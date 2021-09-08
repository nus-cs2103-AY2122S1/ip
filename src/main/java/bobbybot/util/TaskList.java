package bobbybot.util;

import java.util.List;

import bobbybot.exceptions.TaskNoOutOfBoundsException;
import bobbybot.tasks.Task;

/**
 * Represents a task list data structure
 */
public class TaskList {
    private final List<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter for task
     * @param i task number to get, index starts from 0
     * @return task chosen
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Adds a task to task list
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     * Getter for List of tasks
     * @return List of task
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Mark a task as done
     * @param taskNo bobbybot Task Number (starting from index 1)
     */
    public void markAsDone(int taskNo) throws TaskNoOutOfBoundsException {
        if (taskNo < 1) {
            throw new TaskNoOutOfBoundsException("You are trying to mark as done a task number < 1");
        } else if (taskNo > tasks.size()) {
            throw new TaskNoOutOfBoundsException("Task number you are trying to mark as done does not exist");
        }
        Task taskCompleted = getTask(taskNo - 1);
        taskCompleted.markAsDone();
    }

    /**
     * Delete a task and return response, starting from 1
     * @param taskNo bobbybot.tasks.Task Number (starting from index 1)
     */
    public void deleteTask(int taskNo) throws TaskNoOutOfBoundsException {
        if (taskNo < 1) {
            throw new TaskNoOutOfBoundsException("You are trying to delete a task number < 1");
        } else if (taskNo > tasks.size()) {
            throw new TaskNoOutOfBoundsException("Task number you are trying to delete does not exist");
        }

        Task taskToDelete = tasks.get(taskNo - 1);
        System.out.println("  " + taskToDelete);
        tasks.remove(taskToDelete);
    }


}
