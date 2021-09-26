package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.main.StorageElement;

public class Event extends Task {
    private final LocalDate at;

    /**
     * Creates Event
     * @param storageElement storageElement taken from Storage class
     */
    public Event(StorageElement storageElement) {
        super(storageElement);
        this.at = storageElement.getTime();
    }

    /**
     * Creates Event
     * @param description description of event
     * @param time time of event
     */
    public Event(String description, String time) {
        super(description);
        this.taskIcon = "E";
        this.at = LocalDate.parse(time);
    }

    private String getFormattedTime() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }



    public String toString() {
        return "[" + this.taskIcon + "]" + super.toString() + " (at: " + this.getFormattedTime() + ")";
    }

    @Override
    public StorageElement getStorageElement() {
        return new StorageElement(this.taskIcon,
                this.isDone,
                this.description,
                this.at);
    }
}
