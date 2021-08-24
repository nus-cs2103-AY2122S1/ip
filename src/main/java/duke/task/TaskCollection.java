package duke.task;

import duke.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * duke.task.TaskCollection represents the collection of tasks stored in the Duke application.
 */
public class TaskCollection {
    private List<Task> tasks = new ArrayList<>();
    private final Storage storage;

    /**
     * Creates a duke.task.TaskCollection that consists of a duke.Storage file at the specified pathname.
     * @param pathname Pathname of the duke.Storage file
     */
    public TaskCollection(String pathname) {
        this.storage = new Storage(pathname);
        this.tasks = this.getTasks();
    }

    /**
     * Saves the List of Tasks into the duke.task.TaskCollection's duke.Storage file.
     */
    public void saveTasks() {
        StringBuilder taskCollectionString = new StringBuilder();

        for (int index = 0; index < this.tasks.size(); index ++) {
            if (index > 0) {
                taskCollectionString.append(System.lineSeparator());
            }
            taskCollectionString.append(this.tasks.get(index).toStorageString());
        }

        this.storage.write(taskCollectionString.toString());
    }

    /**
     * Retrieves a List of all the Tasks saved.
     * @return List of all Tasks.
     */
    private List<Task> getTasks() {
        String storageContents = this.storage.read();
        return this.parse(storageContents);
    }

    /**
     * Parse a String of Tasks into a List.
     * @param string The input String of Tasks
     * @return The List of Tasks
     */
    private List<Task> parse(String string) {
        if (string == null) {
            return new ArrayList<>();
        }

        String[] taskStrings = string.split(System.lineSeparator());
        List<Task> taskList = new ArrayList<>();

        for (String taskString : taskStrings) {
            Task task = Task.fromStorageString(taskString);
            taskList.add(task);
        }

        return taskList;
    }

    /**
     * Adds a task to the duke.task.TaskCollection.
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets a task from the duke.task.TaskCollection.
     * @param identifier The duke.task.Task's identifier.
     */
    public Task get(int identifier) {
        return this.tasks.get(identifier - 1);
    }

    /**
     * Get the number of Tasks in the duke.task.TaskCollection.
     * @return The size of the duke.task.TaskCollection.
     */
    public Task delete(int identifier) {
        Task deletedTask = this.tasks.remove(identifier - 1);
        return deletedTask;
    }

    /**
     * Get the number of Tasks in the duke.task.TaskCollection.
     * @return The size of the duke.task.TaskCollection.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Converts the duke.task.TaskCollection to its String representation.
     * @return The String representation of the duke.task.TaskCollection.
     */
    @Override
    public String toString() {
        String[] lines = new String[this.tasks.size()];
        for (int index = 0; index < this.tasks.size(); index++) {
            lines[index] = String.format("%d.%s", index + 1, this.tasks.get(index).toString());
        }
        return String.join(System.lineSeparator(), lines);
    }
}
