package duke.tasks;

import duke.main.StorageElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, String time) {
        super(description);
        this.taskIcon = "E";
        this.at = LocalDate.parse(time);
    }

    private String getFormattedTime() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public Event(StorageElement storageElement) {
        super(storageElement);
        this.at = storageElement.getTime();
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedTime() +")";
    }

    @Override
    public StorageElement getStorageElement() {
        return new StorageElement(this.taskIcon,
                this.isDone,
                this.description,
                this.at);
    }
}
