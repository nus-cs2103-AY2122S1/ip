package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructs a Deadline object.
     *
     * @param description Deadline description
     * @param date Date
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.time = null;

    }

    /**
     * Constructs a Deadline object.
     *
     * @param description Deadline description
     * @param date Date
     * @param time Time
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        String dateString = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (this.time == null) {
            return "[D]" + super.toString() + " (by: " + dateString + ")";
        } else {
            String timeString = this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
            return "[D]" + super.toString() + " (by: " + dateString + " " + timeString + ")";
        }
    }

    @Override
    public String toSaveString() {
        String dateString = this.date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        if (this.time == null) {
            return "| D | " + super.toSaveString() + " | " + dateString;
        } else {
            String timeString = this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
            return "| D | " + super.toSaveString() + " | " + dateString + " | " + timeString;
        }
    }

    @Override
    public int compareTo(Task o) {
        if (!(o instanceof Deadline)) {
            return -1;
        }
        Deadline o1 = this;
        Deadline o2 = (Deadline) o;
        if (this.isDone ^ o.isDone) {
            return this.isDone ? 1 : -1;
        }
        int comp = o1.date.compareTo(o2.date);
        if (comp != 0) {
            return comp;
        } else {
            if (o1.time == null ^ o2.time == null) {
                return o1.time == null ? -1 : 1;
            } else if (o1.time == null) {
                return this.description.compareTo(o.description);
            } else {
                return o1.time.compareTo(o2.time);
            }
        }
    }
}
