package tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is a DeadlineTask class, which inherits from Task.
 */
public class DeadlineTask extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d-M-yyyy k:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy hh.mma");
    public static final String KEYWORD = "[Deadline]";
    private final LocalDateTime dateTime;

    /**
     * Constructor for DeadlineTask.
     * @param taskName The description of the deadline task.
     * @param dateTime The deadline of the task in "dd-mm-yyyy hh:mm".
     */
    public DeadlineTask(String taskName, String dateTime) {
        super(taskName);
        this.dateTime = LocalDateTime.parse(dateTime, INPUT_FORMAT);
    }

    /**
     * Returns the date and time as the input format to the constructor.
     * @return The string representation of the date and time.
     */
    public String getDateTime() {
        return this.dateTime.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return KEYWORD + " " + super.toString() + "\n\t(by: "
                + this.dateTime.format(OUTPUT_FORMAT) + ")";
    }
}
