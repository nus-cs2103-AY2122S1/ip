import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DukeDateTime {
    private final static DateTimeFormatter USER_INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final static DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy hh.mma");

    private final LocalDateTime dateTime;

    public DukeDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static DukeDateTime parseUserInput(String str) throws DukeException {
        LocalDateTime dt;
        try {
            dt = LocalDateTime.parse(str, USER_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to parse datetime. Please use the `YYYY-MM-DD HHMM` format " +
                    "(e.g. 2021-08-24 2130).");
        }
        return new DukeDateTime(dt);
    }

    public static DukeDateTime parseISO(String str) throws DukeException {
        LocalDateTime dt;
        try {
            dt = LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to parse datetime. Please use the ISO datetime format.");
        }
        return new DukeDateTime(dt);
    }

    public String toISO() {
        return this.dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String toString() {
        return this.dateTime.format(DISPLAY_FORMATTER);
    }
}
