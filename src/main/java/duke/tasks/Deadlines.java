package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_DATE;

public class Deadlines extends Task {

    private LocalDate dateTimeBy;
    public Deadlines(String description, String dateTimeBy, boolean isDone) {
        super(description, isDone);
        this.dateTimeBy = LocalDate.parse(dateTimeBy);
    }
    @Override
    public String persistedDataStringFormat() {
        char isDone01 = this.isDone ? '1' : '0';
        return String.format("D,%c,%s,%s", isDone01, this.description, this.dateTimeBy.format(ISO_DATE));
    }
    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + " (by: %s)", dateTimeBy.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
