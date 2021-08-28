package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate dateTime;

    public Event(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }
    @Override
    public boolean hasDueDate(LocalDate dueDate) {
        return dueDate.isEqual(this.dateTime);
    }

    public Event(String description, LocalDate dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String getFormattedData() {
        return super.getFormattedData() + "|" + this.dateTime;
    }

    @Override
    public String getTaskIdentifier() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + this.description + " (at: " + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof Event)) {
            return false;
        } else {
            final Event otherEvent = (Event) otherObj;

            if (this.isDone != otherEvent.isDone) {
                return false;
            } else if (!this.description.equals(otherEvent.description)) {
                return false;
            } else return this.dateTime.equals(otherEvent.dateTime);
        }
    }
}
