package duke.tasks;

import duke.main.StorageElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents task with deadline
 */
public class Deadline extends Task {
    private LocalDate by;

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

    private String getFormattedTime() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * {@inheritDoc}
     */
    public Deadline(StorageElement storageElement) {
        super(storageElement);
        this.by = storageElement.getTime();
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedTime() +")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StorageElement getStorageElement() {
        return new StorageElement(this.taskIcon,
                this.isDone,
                this.description,
                this.by);
    }
}
