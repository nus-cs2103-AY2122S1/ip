package jared.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for deadline task if date is provided.
     * @param description of deadline task.
     * @param date of deadline task.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructor for deadline task if time and date is provided.
     * @param description of deadline task.
     * @param date of deadline task.
     * @param time of deadline task.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Converts to string for printing list.
     * @return String of deadline details.
     */
    @Override
    public String toString() {
        String res = "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (time != null) {
            res += " " + time.format(DateTimeFormatter.ofPattern("H:mm"));
        }
        assert res != null;
        return res += ")";
    }

    /**
     * Converts to string for saving in data.
     * @return String of deadline details in save format.
     */
    @Override
    public String saveFormat() {
        String res = "D _ " + super.saveFormat() + " _ " + this.date;
        if (time != null) {
            res += " _ " + this.time;
        }
        assert res != null;
        return res;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public int compareTo(Task t) {
        if (t instanceof Deadline) {
            Deadline deadlineTask = (Deadline) t;
            return this.date.compareTo(deadlineTask.getDate());
        } else if (t instanceof Event) {
            Event eventTask = (Event) t;
            return this.date.compareTo(eventTask.getDate());
        } else if (t instanceof Todo) {
            return -1;
        } else {
            return 0;
        }
    }
}
