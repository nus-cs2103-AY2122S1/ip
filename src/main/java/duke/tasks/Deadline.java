package duke.tasks;

import duke.main.StorageElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String time) {
        super(description);
        this.taskIcon = "D";
        this.by = LocalDate.parse(time);
    }

    private String getFormattedTime() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public Deadline(StorageElement storageElement) {
        super(storageElement);
        this.by = storageElement.getTime();
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedTime() +")";
    }

    @Override
    public StorageElement getStorageElement() {
        return new StorageElement(this.taskIcon,
                this.isDone,
                this.description,
                this.by);
    }
}
