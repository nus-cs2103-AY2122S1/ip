package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate by;
    private boolean isTimeIncluded;

    /**
     * Constructor for Deadline.
     *
     * @param input deadline title.
     * @param by date/time of deadline.
     * @throws DateTimeParseException if date format is incorrect.
     */
    public Deadline(String input, String by) throws DateTimeParseException {
        super(input);
        int space = by.indexOf(" ");
        String format;
        if (space > 0) {
            format = "d/MM/yyyy HHmm";
            isTimeIncluded = true;
        } else {
            format = "d/MM/yyyy";
            isTimeIncluded = false;
        }
        this.by = LocalDate.parse(by.trim(), DateTimeFormatter.ofPattern(format));
        if (this.by.isBefore(LocalDate.now())) {
            super.completeItem();
        }
    }

    @Override
    public boolean compareDate(LocalDate compare) {
        return this.by.equals(compare);
    }

    @Override
    public String formatTask() {
        return "D" + " | " + super.formatTask() + " | " + this.by.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }

    @Override
    public String toString() {
        String format = isTimeIncluded ? "MMM dd yyyy HH:mm" : "MMM dd yyyy";
        return "[D] " + super.toString() + "(by: " + this.by.format(DateTimeFormatter.ofPattern(format)) + ")";
    }
}
