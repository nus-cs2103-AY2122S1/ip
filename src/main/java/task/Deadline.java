package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * Class that stores a Task that contains a date(by).
 */
public class Deadline extends Task {

    protected LocalDate deadlineDate = null;
    protected LocalDateTime deadlineDateTime = null;
    protected YearMonth deadlineYearMonth = null;

    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public Deadline(String description, LocalDateTime deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    public Deadline(String description, YearMonth deadlineYearMonth) {
        super(description);
        this.deadlineYearMonth = deadlineYearMonth;
    }

    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + (deadlineDateTime == null
                ? (this.deadlineDate == null ? this.deadlineYearMonth : this.deadlineDate)
                : this.deadlineDateTime) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + (deadlineDateTime == null
                ? (deadlineDate == null ? this.deadlineYearMonth.format((DateTimeFormatter.ofPattern("MMM yyyy")))
                : this.deadlineDate.format((DateTimeFormatter.ofPattern("MMM d yyyy"))))
                : deadlineDateTime.format((DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))))+ ")";
    }
}
