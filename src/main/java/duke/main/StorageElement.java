package duke.main;
import java.time.LocalDate;

/**
 * Represents intermediary class to bridge between Storage and Task class
 */
public class StorageElement {
    private String taskIcon;
    private Boolean isDone;
    private String description;
    private LocalDate time; // In MM-DD-YY format

    public StorageElement(String taskIcon, Boolean isDone, String description) {
        this.taskIcon = taskIcon;
        this.isDone = isDone;
        this.description = description;
    }

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
