package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import duke.command.MalformedCommandException;
import duke.storage.StorageException;

/**
 * Represents a collection of all the user's tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Creates the task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to task list.
     * @return task that was added to the task list.
     */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Marks a task done in the task list.
     *
     * @param taskIndex Index of task to be marked done.
     * @return Task that was marked done.
     * @throws MalformedCommandException If taskIndex provided is out of range of the task list's length.
     */
    public Task markTaskDone(int taskIndex) throws MalformedCommandException {
        try {
            Task task = tasks.get(taskIndex);
            task.markDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new MalformedCommandException("You only have " + numTasks() + " tasks currently. "
                    + "Please provide a task index from that list");
        }
    }

    /**
     * Deletes a task from the user's task list.
     *
     * @param taskIndex Index of task to delete from task list.
     * @return Task that was deleted.
     * @throws MalformedCommandException If taskIndex provided is out of range of the task list's length.
     */
    public Task delete(int taskIndex) throws MalformedCommandException {
        try {
            return tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new MalformedCommandException("You only have " + numTasks() + " tasks currently. "
                    + "Please provide a task index from that list");
        }
    }

    /**
     * Returns number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public int numTasks() {
        return tasks.size();
    }

    /**
     * Returns a TaskList with filtered tasks matching the search term.
     *
     * @param searchTerm Serch term to filter tasks.
     * @return TaskList with filtered tasks matching the search term.
     */
    public TaskList findTasks(String searchTerm) {
        TaskList filteredTaskList = new TaskList();
        List<Task> filteredTasks = new ArrayList<>(tasks);
        filteredTasks.removeIf(task -> !(task.toString()).contains(searchTerm));
        filteredTaskList.setTasks(filteredTasks);
        return filteredTaskList;
    }

    /**
     * Clears the user's current list of tasks.
     */
    public void clear() {
        tasks = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder taskListStringRepresentation = new StringBuilder();
        for (int i = 0; i < numTasks(); i++) {
            taskListStringRepresentation.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return taskListStringRepresentation.toString();
    }

    /**
     * Returns a string representation of the task list for storage on disk.
     *
     * @return String representation of the task list for storage on disk.
     */
    public String toStorageFormat() {
        StringBuilder taskStorageRepresentation = new StringBuilder();
        for (int i = 0; i < numTasks(); i++) {
            taskStorageRepresentation.append(tasks.get(i).toStorageFormat()).append("\n");
        }
        return taskStorageRepresentation.toString();
    }

    private void setTasks(List<Task> newTasks) {
        tasks = newTasks;
    }
}
