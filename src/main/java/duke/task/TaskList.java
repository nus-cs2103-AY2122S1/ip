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
    private final List<Task> tasks;

    /**
     * Creates the task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Loads task from file on disk into the task list.
     *
     * @param file File storing tasks on disk.
     * @throws StorageException If file storing the tasks is incorrectly formatted.
     */
    public void loadTasks(File file) throws StorageException {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String taskString = sc.nextLine();
                String[] splitTaskString = taskString.split(Task.STORAGE_DELIMITER);
                Task task = null;

                switch (splitTaskString[0]) {
                case Todo.IDENTIFIER:
                    task = new Todo(splitTaskString[1], Boolean.parseBoolean(splitTaskString[2]));
                    break;
                case Event.IDENTIFIER:
                    task = new Event(splitTaskString[1], Boolean.parseBoolean(splitTaskString[2]), splitTaskString[3]);
                    break;
                case Deadline.IDENTIFIER:
                    task = new Deadline(splitTaskString[1], Boolean.parseBoolean(splitTaskString[2]),
                            splitTaskString[3]);
                    break;
                }

                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException ignored) {
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new StorageException("Txt file for loading tasks is wrongly formatted. Some tasks were not loaded");
        }
    }

    /**
     * Adds a task to the task list.
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
            throw new MalformedCommandException("You only have " + numTasks() + " tasks currently. " +
                "Please provide a task index from that list");
        }
    }

    /**
     *
     * @param taskIndex Index of task to delete from task list.
     * @return Task that was deleted.
     * @throws MalformedCommandException If taskIndex provided is out of range of the task list's length.
     */
    public Task delete(int taskIndex) throws MalformedCommandException {
        try {
            return tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new MalformedCommandException("You only have " + numTasks() + " tasks currently. " +
                "Please provide a task index from that list");
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
}
