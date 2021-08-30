package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {

    protected LocalDate date;

    /**
     * Deadline constructor.
     *
     * @param description Description of deadline task.
     * @param date Date of deadline.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a Deadline from string input with description and date.
     *
     * @param desc_date String input with description and date in
     *                  '[description] (by: [MMM d yyyy]' format.
     * @return Deadline from string input with description and date.
     */
    public static Deadline build(String desc_date) {
        desc_date = desc_date.replaceAll("\\(by: (.*)\\)", "/by $1");
        String[] input = desc_date.split(" /by ",2);
        LocalDate d = LocalDate.parse(input[1], DateTimeFormatter.ofPattern("MMM d yyyy"));
        return new Deadline(input[0], d);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}