import java.time.LocalDate;
import java.util.Optional;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String by;
    Optional<LocalDate> date;

    public Deadline(String desc, String by) throws DukeException.EmptyDescriptionException {
        super(desc);
        try {
            this.date = Optional.of(LocalDate.parse(by));
        } catch (DateTimeParseException e) {
            this.by = by;
            this.date = Optional.empty();
        }
    }

    public Deadline(boolean isComplete, String desc, String by) throws DukeException.EmptyDescriptionException {
        super(isComplete, desc);
        try {
            this.date = Optional.of(LocalDate.parse(by));
        } catch (DateTimeParseException e) {
            this.by = by;
            this.date = Optional.empty();
        }
    }

    private String getDate(String pattern) {
        return this.date.map((date) -> date.format(DateTimeFormatter.ofPattern(pattern)))
                        .orElse(this.by);
    }

    @Override
    public String getRepr() {
        return String.format("D|%s|%s", super.getRepr(), this.getDate("yyyy-MM-d"));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getDate("MMM d yyyy"));
    }
}
