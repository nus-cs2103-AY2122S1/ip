package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;

/**
 * TaskCollection represents the collection of tasks stored in the Duke application.
 */
public class TaskCollection {
    private List<Task> tasks = new ArrayList<>();
    private final Storage storage;

    /**
     * Creates a TaskCollection that consists of a Storage file at the specified pathname.
     * @param pathname Pathname of the Storage file
     */
    public TaskCollection(String pathname) {
        this.storage = new Storage(pathname);
        this.tasks = this.getTasks();
    }

    /**
     * Saves the List of Tasks into the TaskCollection's Storage file.
     */
    public void saveTasks() {
        StringBuilder taskCollectionString = new StringBuilder();

        for (int index = 0; index < this.tasks.size(); index++) {
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
     * Gets a List of Tasks that matches the given regex String.
     * @param regex The regex String to match with
     * @return The List of matching Tasks
     */
    public List<Task> findTasks(String regex) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.descriptionMatches(regex)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    /**
     * Parses a String of Tasks into a List.
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
     * Adds a task to the TaskCollection.
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets a task from the TaskCollection.
     * @param identifier The Task's identifier.
     */
    public Task get(int identifier) {
        return this.tasks.get(identifier - 1);
    }

    /**
     * Deletes the Task corresponding to the identifier specified from the TaskCollection.
     * @param identifier The specified identifier.
     * @return The deleted Task
     */
    public Task delete(int identifier) {
        return this.tasks.remove(identifier - 1);
    }

    /**
     * Gets the number of Tasks in the TaskCollection.
     * @return The size of the TaskCollection.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Converts the TaskCollection to its String representation.
     * @return The String representation of the TaskCollection.
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
