package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d-M-yyyy k:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy hh.mma");
    public static final String KEYWORD = "[EVENT]";
    public static final String TIMEDATE_DELIMITER = ("(at: ");
    private final LocalDateTime dateTime;

    public EventTask(String taskName, String dateTime) {
        super(taskName);
        this.dateTime = LocalDateTime.parse(dateTime, INPUT_FORMAT);
    }

    public String getDateTime() {
        return this.dateTime.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return KEYWORD + " " + super.toString() + " (at: "
                + this.dateTime.format(OUTPUT_FORMAT) + ")";
    }
}
