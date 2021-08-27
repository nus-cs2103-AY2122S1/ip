import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
//    public static final String TYPE = "Deadline";
    public static final String SYMBOL = "D";
    private String userInputByDate;
    private LocalDateTime dateTimeDeadline;
    private LocalDateTime dateTimeTaskCreation;
    private static DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public static Deadline of(String taskSummary, String byDate) {
        return new Deadline(taskSummary, byDate);
    }

    public static Deadline parse(String storageLine) {
        //example line: "D | 0 | work | 01-01-2020 14:30"
        String[] args = storageLine.split(" \\| ");
        if (args.length != 4) {
            throw new IllegalArgumentException("storage line passed in doesnt have enough arguments");
        }
        Deadline loadedTask = new Deadline(args[2], args[3]);
        boolean completed = args[1].equals("1");
        if (completed) {
            loadedTask.markCompleted();
        }
        return loadedTask;
    }

    public Deadline(String taskSummary, String byDate) {
        super(taskSummary);
        this.userInputByDate = byDate;
        this.dateTimeTaskCreation = LocalDateTime.now();
        this.updateDateTimeDeadline(byDate);
    }

    private void updateDateTimeDeadline(String byDate) {
        LocalDateTime parsedDateTime = Deadline.stringToLocalDateTime(byDate);
        if (parsedDateTime.isBefore(dateTimeTaskCreation)) {
            throw new IllegalArgumentException("Date passed as deadline is in the past: " + this.userInputByDate);
        }
        this.dateTimeDeadline = stringToLocalDateTime(byDate);
    }

    private static LocalDateTime stringToLocalDateTime(String text) {
        return LocalDateTime.parse(text, Deadline.DATE_TIME_FORMATTER);
    }

    private static String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(Deadline.DATE_TIME_FORMATTER);
    }

    public static String syntax() {
        return "deadline command syntax: \'deadline <task> /by <deadlineTime>\'";
    }

    @Override
    public String toStorageFormat() {
        return String.format(
            "%s | %d | %s | %s",
            Deadline.SYMBOL, this.isCompleted() ? 1 : 0,this.getTaskSummary(), this.userInputByDate
        );
    }

    @Override
    public String toString() {
        return String.format(
            "[%s][%s] %s (by: %s)",
            Deadline.SYMBOL,
            this.isCompleted() ? "X" : "",
            this.getTaskSummary(),
            Deadline.localDateTimeToString(this.dateTimeDeadline)
//                this.userInputByDate
        );
    }
}
