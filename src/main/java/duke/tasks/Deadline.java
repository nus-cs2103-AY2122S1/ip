package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.main.StorageElement;
/**
 * Represents task with deadline
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Creates Deadline object from description and deadline time
     *
     * @param description Task description
     * @param time deadline
     */
    public Deadline(String description, String time) {
        super(description);
        this.taskIcon = "D";
        this.by = LocalDate.parse(time);
    }

    /**
     * Creates deadline
     * @param storageElement storageElement taken from Storage class
     */
    public Deadline(StorageElement storageElement) {
        super(storageElement);
        this.by = storageElement.getTime();
    }

    private String getFormattedTime() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String toString() {
        return "[" + this.taskIcon + "]" + super.toString() + " (by: " + this.getFormattedTime() + ")";
    }

    /**
     * Get storage element
     * @return storage element to be returned
     */
    @Override
    public StorageElement getStorageElement() {
        return new StorageElement(this.taskIcon,
                this.isDone,
                this.description,
                this.by);
    }
}
