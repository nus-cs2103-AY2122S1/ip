package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description, "[D]", false);
        this.time = time;
    }

    public Deadline(String description, LocalDate time, boolean status) {
        super(description, "[D]", status);
        this.time = time;
    }

    @Override
    public String getFormattedData() {
        String formattedStatus = super.isDone() ? "1|" : "0|";
        return "D|" + formattedStatus + super.getDescription()
            + "|" + this.time.toString();
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
