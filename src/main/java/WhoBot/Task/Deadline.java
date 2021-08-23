package WhoBot.Task;

import WhoBot.Main.WhoBotException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Deadline extends Task{

    private LocalDateTime deadline;
    private boolean hasTime;

    public Deadline(String task) throws WhoBotException {
        super(task.split(" /by ")[0]);
        try {
            this.deadline = processDateTime(task.split(" /by ")[1]);
        } catch (DateTimeParseException ex) {
            throw new WhoBotException("Ensure that date time is of the format d/M/yyyy HH:mm");
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new WhoBotException("Ensure that the command is of the form \"deadline #description /by #deadline\". The deadline must be given.");
        }
    }

    private LocalDateTime processDateTime(String dateTime) {
        if (dateTime.contains(" ")) {
            this.hasTime = true;
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
        } else {
            this.hasTime = false;
            return LocalDateTime.parse(dateTime + " 08:00", DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
        }
    }

    public boolean hasTime() {
        return hasTime;
    }

    private String getDateTimeFormatted() {
        return hasTime
                ? this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                : this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getDescription() {
        return "[D] " + super.getDescription() + " (by: " + this.getDateTimeFormatted() + ")";
    }

    @Override
    public String getType() {
        return "D";
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.getDateTimeFormatted() + ")";
    }

    @Override
    public int compareTo(Task o) {
        int val = super.compareTo(o);
        if (val == 0) {
            if (o instanceof Deadline) {
                return this.deadline.compareTo(((Deadline) o).deadline);
            } else if (o instanceof Event) {
                return this.deadline.compareTo(((Event) o).getTiming());
            } else {
                return this.getDescription().compareTo(o.getDescription());
            }
        } else {
            return val;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deadline deadline1 = (Deadline) o;
        return deadline.equals(deadline1.deadline) && this.hasTime == deadline1.hasTime
                && this.getDescription().equals(deadline1.getDescription());
    }
}
