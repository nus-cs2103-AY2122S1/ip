import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    private static final String TASK_TYPE = "D";
    private LocalDate by;

    /**
     * Constructor of the Deadline class
     *
     * @param description description of this deadline
     * @param by the due date of the deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of this deadline
     *
     * @return string representation of this deadline
     */
    @Override
    public String toString() {
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    protected String toSavableFormat() {
        String isDone = Parser.parseIsDoneToString(this.isDone());
        List<String> stringList = Arrays.asList(TASK_TYPE, isDone, this.getDescription(), this.by.toString());
        return String.join(Duke.DELIMITER, stringList);
    }

    @Override
    protected String getTaskType() {
        return TASK_TYPE;
    }
}