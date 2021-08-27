import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String type;
    private String userInputByDate;
    private LocalDateTime dateTimeDeadline;
    private LocalDateTime dateTimeTaskCreation;
    private static DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static Deadline of(String taskSummary, String byDate) {
        return new Deadline(taskSummary, byDate);
    }

    public Deadline(String taskSummary, String byDate) {
        super(taskSummary);
        this.type = "Deadline";
        this.userInputByDate = byDate;
        this.dateTimeTaskCreation = LocalDateTime.now();
        this.updateDateTimeDeadline(byDate);
    }

    private static LocalDateTime stringToLocalDateTime(String text) {
        return LocalDateTime.parse(text, Deadline.DATE_TIME_FORMATTER);
    }

    private static String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(Deadline.DATE_TIME_FORMATTER);
    }

    private void updateDateTimeDeadline(String byDate) {
        LocalDateTime parsedDateTime = Deadline.stringToLocalDateTime(byDate);
        if (parsedDateTime.isBefore(dateTimeTaskCreation)) {
            throw new IllegalArgumentException("Date passed as deadline is in the past: " + this.userInputByDate);
        }
        this.dateTimeDeadline = stringToLocalDateTime(byDate);
    }

    @Override
    public String toString() {
        return String.format(
                "[%s][%s] %s (by: %s)",
                this.type.charAt(0),
                this.isCompleted() ? "X" : "",
                this.getTaskSummary(),
                Deadline.localDateTimeToString(this.dateTimeDeadline)
//                this.userInputByDate
        );
    }
}
