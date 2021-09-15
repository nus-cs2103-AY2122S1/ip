package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor of Deadline.
     *
     * @param description Description of deadline task.
     * @param by          The deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, Task.getDateTimeFormatter());
    }

    public Deadline(String description, String by, String reminderTime) {
        super(description, reminderTime);
        this.by = LocalDateTime.parse(by, Task.getDateTimeFormatter());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + ")";
    }

    @Override
    public String getIcon() {
        return "D";
    }

    @Override
    public String getTaskTime() {
        return by.format(Task.getDateTimeFormatter());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return deadline.toString().equals(this.toString());
        } else {
            return false;
        }
    }
}
