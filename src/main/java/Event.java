import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate localDateAt;
    protected LocalDateTime localDateTimeAt;
    protected String at;

    private static final DateTimeFormatter DATE_FORMAT_WITH_HOURS_FOR_DISPLAY =
            DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm");
    private static final DateTimeFormatter DATE_FORMAT_WITHOUT_HOURS_FOR_DISPLAY =
            DateTimeFormatter.ofPattern("E, MMM d yyyy");
    private static final DateTimeFormatter[] DATE_FORMATTERS_WITH_HOURS = {
            DATE_FORMAT_WITH_HOURS_FOR_DISPLAY,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM, d yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"),
    };

    private static final DateTimeFormatter[] DATE_FORMATTERS_WITHOUT_HOURS = {
            DATE_FORMAT_WITHOUT_HOURS_FOR_DISPLAY,
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy"),
            DateTimeFormatter.ofPattern("MMM, d yyyy"),
            DateTimeFormatter.ofPattern("d MMMM yyyy"),
            DateTimeFormatter.ofPattern("d MMM yyyy")
    };

    public Event(String description, String at, boolean isDone) throws SaberTimeParserException {
        super(description, isDone);
        this.at = at;
        for (DateTimeFormatter df : DATE_FORMATTERS_WITH_HOURS) {
            try {
                this.localDateTimeAt = LocalDateTime.parse(at, df);
                break;
            } catch (DateTimeParseException e) {
                // Ignore; try next formatter
            }
        }
        for (DateTimeFormatter df : DATE_FORMATTERS_WITHOUT_HOURS) {
            try {
                this.localDateAt = LocalDate.parse(at, df);
                break;
            } catch (DateTimeParseException e) {
                // Ignore; try next formatter
            }
        }

        if (this.localDateAt == null && this.localDateTimeAt == null) {
            throw new SaberTimeParserException("Unable to parse time");
        }
    }

    @Override
    public String toString() {
        String atString;

        if (localDateTimeAt != null) {
            atString = localDateTimeAt.format(DATE_FORMAT_WITH_HOURS_FOR_DISPLAY);
        } else {
            atString = localDateAt.format(DATE_FORMAT_WITHOUT_HOURS_FOR_DISPLAY);
        }

        return "[E]" + super.toString() + " (at: " + atString + ")";
    }
}