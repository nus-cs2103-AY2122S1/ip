package duke.information;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * Class that stores a Task that contains a date(deadline).
 */
public class Deadline extends Task {

    /** Class of the deadline's date. */
    public enum DeadlineDateType {
        LocalDate,
        LocalDateTime,
        YearMonth
    }

    protected DeadlineDateType deadlineDateType;
    /** Types of possible deadlineDate, only 1 of the 3 will be used whenever a deadline is initialised. */
    protected LocalDate deadlineDate;
    protected LocalDateTime deadlineDateTime;
    protected YearMonth deadlineYearMonth;

    /**
     * Constructs Deadline class if deadline is a LocalDate.
     *
     * @param description Deadline description.
     * @param deadlineDate Date of the deadline.
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.deadlineDateType = DeadlineDateType.LocalDate;
    }

    /**
     * Constructs Deadline class if deadline is a LocalDateTime.
     *
     * @param description Deadline description.
     * @param deadlineDateTime Date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
        this.deadlineDateType = DeadlineDateType.LocalDateTime;
    }

    /**
     * Constructs Deadline class if deadline is a YearMonth.
     *
     * @param description Deadline description.
     * @param deadlineYearMonth Year and month of the deadline.
     */
    public Deadline(String description, YearMonth deadlineYearMonth) {
        super(description);
        this.deadlineYearMonth = deadlineYearMonth;
        this.deadlineDateType = DeadlineDateType.YearMonth;
    }

    /**
     * Converts the deadline's information into a string.
     * To be stored in the user's dedicated txt file.
     *
     * @return String of the deadline information.
     */
    @Override
    public String toData() {
        String dateInData;
        switch (deadlineDateType) {

        case LocalDate:
            dateInData = this.deadlineDate.toString();
            break;

        case LocalDateTime:
            dateInData = this.deadlineDateTime.toString();
            break;

        default:
            dateInData = this.deadlineYearMonth.toString();
        }
        return "D|" + super.toData() + "|" + dateInData + "\n";
    }

    /**
     * Converts the deadline's information into a string.
     * To be used to display the deadline information to the user.
     *
     * @return String of the deadline information.
     */
    @Override
    public String toString() {
        switch (deadlineDateType) {

        case LocalDate:
            return "[D]" + super.toString() + " (by: "
                        + this.deadlineDate.format((DateTimeFormatter.ofPattern("MMM d yyyy"))) + ")";

        case LocalDateTime:
            return "[D]" + super.toString() + " (by: "
                        + this.deadlineDateTime.format((DateTimeFormatter.ofPattern("MMM d yyyy HHmm")))+ ")";

        default:
            return "[D]" + super.toString() + " (by: "
                        + this.deadlineYearMonth.format((DateTimeFormatter.ofPattern("MMM yyyy"))) + ")";
        }
    }
}
