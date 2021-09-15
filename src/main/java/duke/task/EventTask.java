package duke.task;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The EventTask class encapsulates a duke.task that start at a specific time and ends at a specific time.
 */
public class EventTask extends Task {
    private static final LocalTime DEFAULT_TIME = LocalTime.parse("00:00");

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    private static final DateTimeFormatter SAVE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter SAVE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final LocalDate DATE;
    private final LocalTime TIME;

    public static final String DELIMITER = " /at ";
    private static final String DELIMITER_DATETIME = " ";

    private EventTask(String description, LocalDate date, LocalTime time) {
        super(description);
        this.DATE = date;
        this.TIME = time;
    }

    private EventTask(String description, boolean isDone, LocalDate date, LocalTime time) {
        super(description, isDone);
        this.DATE = date;
        this.TIME = time;
    }

    /**
     * Returns a EventTask from String.
     * E.g. If passed in ("return books /at 2021-10-15 18:00"),
     * this function returns a EventTask Task with description "return books, and the correct date and time.
     *
     * @param command Format should follow the example.
     * @return EventTask Object.
     */
    public static EventTask getTaskFromCommandString(String command) throws DukeException {
        try {
            String[] commandSplit = command.split(DELIMITER, 2);
            String[] dateTimeSplit = commandSplit[1].split(DELIMITER_DATETIME, 2);

            LocalDate date = LocalDate.parse(dateTimeSplit[0]);
            LocalTime time = dateTimeSplit.length < 2 ? DEFAULT_TIME : LocalTime.parse(dateTimeSplit[1]);

            return new EventTask(commandSplit[0], date, time);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException();
        }
    }

    /**
     * Returns a EventTask from String.
     * E.g. If passed in ("1 | return books | 2021-10-12 | 13:20", " \\| ", "1"),
     * this function returns a done EventTask with description "return books", and the correct date and time.
     *
     * @param taskString Format should follow <done><delimiter><description><delimiter><date><delimiter><time>
     * @param delimiter  Delimiter separating the String into its formal parts.
     * @param done       Indicator for a done duke.task.
     * @return EventTask created from String.
     * @throws DukeException if String is of the wrong format.
     */
    public static EventTask getTaskFromStorageString(String taskString, String delimiter, String done) throws DukeException {
        try {
            String[] taskSplit = taskString.split(delimiter, 4);
            boolean isDone = taskSplit[0].equals(done);
            LocalDate date = LocalDate.parse(taskSplit[2]);
            LocalTime time = LocalTime.parse(taskSplit[3]);
            return new EventTask(taskSplit[1], isDone, date, time);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException();
        }
    }

    @Override
    public String getTaskFileString(String delimiter, String done, String notDone) {
        return "E" + delimiter + (this.isDone ? done : notDone) + delimiter + this.description
                + delimiter + DATE.format(SAVE_DATE_FORMATTER) + delimiter + TIME.format(SAVE_TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DATE.format(dateFormatter) + " " + TIME.format(timeFormatter) + ")";
    }
}
