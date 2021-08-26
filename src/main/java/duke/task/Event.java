package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.taskType = TaskType.E;
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += String.format(" (at: %s)", this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        return str;
    }

    @Override
    public String getDataString() {
        return String.format("%s_~_%s_~_%s_~_%s", this.taskType, this.getStatusInt(), this.description, this.date.format(DateTimeFormatter.ofPattern("d/MM/yyyy")));
    }

    @Override
    public boolean equals(Object object) {
        Event otherEvent = (Event) object;
        return this.date.equals(otherEvent.date) &&
                this.getStatusIcon().equals(otherEvent.getStatusIcon()) &&
                this.description.equals(otherEvent.description) &&
                this.taskType.equals(otherEvent.taskType);
    }

}
