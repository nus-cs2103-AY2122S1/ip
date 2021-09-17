package duke.main;
import java.time.LocalDate;

/**
 * Represents intermediary class to bridge between Storage and Task class
 */
public class StorageElement {
    private final String taskIcon;
    private final Boolean isDone;
    private final String description;
    private LocalDate time; // In MM-DD-YY format

    /**
     * Creates storage element
     * @param taskIcon a task's icon
     * @param isDone a task's done status
     * @param description a task's description
     */
    public StorageElement(String taskIcon, Boolean isDone, String description) {
        this.taskIcon = taskIcon;
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Creates storage element
     * @param taskIcon a task's icon
     * @param isDone a task's done status
     * @param description a task's description
     * @param time a task's time attributes
     */
    public StorageElement(String taskIcon, Boolean isDone, String description, LocalDate time) {
        this(taskIcon, isDone, description);
        this.time = time;
    }

    public String getTaskIcon() {
        return taskIcon;
    }

    public Boolean getDone() {
        return isDone;
    }

    public LocalDate getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}
