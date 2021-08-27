package storage;
import java.time.LocalDate;

public class StorageElement {
    String taskIcon;
    Boolean isDone;
    String description;
    LocalDate time;  //In MM-DD-YY format

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