package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description, "[D]", false);
        this.time = time;
    }

    public Deadline(String description, LocalDate time, boolean status) {
        super(description, "[D]", status);
        this.time = time;
    }

    public String getformmatedData() {
        String formmatedStatus = super.isDone() ? "1|" : "0|";
        return "D|" + formmatedStatus + super.getDescription() 
            + "|" + this.time.toString();
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
