package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description, "[E]", false);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String description, LocalDate startDate, LocalDate endDate, boolean status) {
        super(description, "[E]", status);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    @Override
    public LocalDate getDueDate() {
        return this.startDate;
    }

    @Override
    public String getFormattedData() {
        String formattedStatus = super.isDone() ? "1|" : "0|";
        return "E|" + formattedStatus + super.getDescription()
                + "|" + this.startDate.toString()
                + "|" + this.endDate.toString();
    }

    @Override
    public String toString() {
        return super.toString() + " (from "
                + this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to " + this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    @Override
    public boolean equals(Object other){
        if (this == other) {
            return true;
        }
        if (other instanceof Event) {
            return this.getDescription().equals(((Event) other).getDescription())
                    && this.getDueDate().equals(((Event) other).getDueDate())
                    && this.getEndDate().equals(((Event) other).getEndDate());
        } else {
            return false;
        }
    }
}
