package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline.
     *
     * @param description Description of deadline task.
     * @param by          The deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, Task.getDateTimeFormatter());
    }

    /**
     * Constructs Deadline with a reminder.
     *
     * @param description  Description off deadline task.
     * @param by           The deadline of task.
     * @param reminderTime The time when a reminder message display.
     */
    public Deadline(String description, String by, String reminderTime) {
        super(description, reminderTime);
        this.by = LocalDateTime.parse(by, Task.getDateTimeFormatter());
    }

    @Override
    public String toString() {
        String result = "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        result += this.hasReminder()
                ? ", remind at" + this.getReminderTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                : "";
        result += ")";

        return result;
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
