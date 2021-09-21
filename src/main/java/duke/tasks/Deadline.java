package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description, "[D]", false);
        this.date = date;
    }

    public Deadline(String description, LocalDate date, boolean status) {
        super(description, "[D]", status);
        this.date = date;
    }

    @Override
    public LocalDate getDueDate() {
        return this.date;
    }

    @Override
    public String getFormattedData() {
        String formattedStatus = super.isDone() ? "1|" : "0|";
        return "D|" + formattedStatus + super.getDescription()
            + "|" + this.date.toString();
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public boolean equals(Object other){
        if (this == other) {
            return true;
        }
        if (other instanceof Deadline) {
            return this.getDescription().equals(((Deadline) other).getDescription())
                    && this.getDueDate().equals(((Deadline) other).getDueDate());
        } else {
            return false;
        }
    }
}
