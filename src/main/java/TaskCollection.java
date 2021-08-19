import java.util.ArrayList;
import java.util.List;

/**
 * TaskCollection represents the collection of tasks stored in the Duke application.
 */
public class TaskCollection {
    private List<Task> tasks = new ArrayList<>();

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
     * Get the number of Tasks in the TaskCollection.
     * @return The size of the TaskCollection.
     */
    public Task delete(int identifier) {
        return this.tasks.remove(identifier - 1);
    }

    /**
     * Get the number of Tasks in the TaskCollection.
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
