package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public static Deadline build(String desc_date) {
        desc_date = desc_date.replaceAll("\\(by: (.*)\\)", "/by $1");
        String[] input = desc_date.split(" /by ",2);
        
        try {
            return new Deadline(input[0], LocalDate.parse(input[1]));
        } catch (DateTimeParseException e) {
            LocalDate d = LocalDate.parse(input[1], DateTimeFormatter.ofPattern("MMM d yyyy"));
            return new Deadline(input[0], d);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}