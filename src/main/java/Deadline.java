import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
//    public static final String TYPE = "Deadline";
    public static final String SYMBOL = "D";
    private String userInputDeadline;
    private LocalDateTime dateTimeDeadline;
    private LocalDateTime dateTimeTaskCreation;
    private static DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd-MM-uuuu HHmm");


    public static Deadline of(String taskSummary, String byDate) {
        return new Deadline(taskSummary, byDate);
    }

    public static Deadline parse(String storageLine) {
        //example line: "D | 0 | work | 01-06-2020 1430"
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
        this.userInputDeadline = byDate;
        this.dateTimeTaskCreation = LocalDateTime.now();
        this.updateDateTimeDeadline(byDate);

    }

    private void updateDateTimeDeadline(String byDate) {
        try {
            LocalDateTime parsedDateTime = Deadline.stringToLocalDateTime(byDate);
            if (parsedDateTime.isBefore(dateTimeTaskCreation)) {
                throw new IllegalArgumentException("Time of deadline is in the past!");
            }
            this.dateTimeDeadline = parsedDateTime;
        } catch (DateTimeParseException e) {
            throw DukeException.of("date",
    String.format("Invalid date & time passed (\'%s\')\n\n", byDate) + Deadline.syntax());
        } catch (IllegalArgumentException e) {
            throw DukeException.of("date",
    String.format("Invalid date & time passed (\'%s\')\n\n", byDate) + e.getMessage());
        }
    }

    private static LocalDateTime stringToLocalDateTime(String text) {
        return LocalDateTime.parse(text, Deadline.DATE_TIME_FORMATTER);
    }

    private static String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(Deadline.DATE_TIME_FORMATTER);
    }

    public static String syntax() {
        return "deadline command syntax: \n" +
                "    \'deadline <task> /by dd-MM-yyyy HHmm\'\n"+
                "Eg. \'deadline project /by 01-01-2020 2359\'";
    }

    @Override
    public String toStorageFormat() {
        return String.format(
            "%s | %d | %s | %s",
            Deadline.SYMBOL, this.isCompleted() ? 1 : 0,this.getTaskSummary(), this.userInputDeadline
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
