import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate localDateBy;
    protected LocalDateTime localDateTimeBy;
    protected  String by;

    private static final DateTimeFormatter[] DATE_FORMATTERS_WITH_HOURS = {
            DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM, d yyyy HH:mm"),
            DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"),
    };

    private static final DateTimeFormatter[] DATE_FORMATTERS_WITHOUT_HOURS = {
            DateTimeFormatter.ofPattern("E, MMM d yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy"),
            DateTimeFormatter.ofPattern("MMM, d yyyy"),
            DateTimeFormatter.ofPattern("dd MMM yyyy")
    };


    public Deadline(String description, String by, boolean isDone) throws SaberTimeParserException {
        super(description, isDone);
        this.by = by;
        for (DateTimeFormatter df : DATE_FORMATTERS_WITH_HOURS) {
            try {
                this.localDateTimeBy = LocalDateTime.parse(by, df);
                break;
            } catch (DateTimeParseException e) {
                // Ignore; try next formatter
            }
        }
        for (DateTimeFormatter df : DATE_FORMATTERS_WITHOUT_HOURS) {
            try {
                this.localDateBy = LocalDate.parse(by, df);
                break;
            } catch (DateTimeParseException e) {
                // Ignore; try next formatter
            }
        }

        if (this.localDateBy == null && this.localDateTimeBy == null) {
            throw new SaberTimeParserException("Unable to parse time");
        }
    }

    @Override
    public String toString() {

        String byString = "";

        if (localDateTimeBy != null) {
            for (DateTimeFormatter df : DATE_FORMATTERS_WITH_HOURS) {
                try {
                    byString = localDateTimeBy.format(df);
                    break;
                } catch (DateTimeParseException e) {
                    // Ignore; try next formatter
                }
            }
        } else if (localDateBy != null) {
            if (localDateTimeBy == null) {
                for (DateTimeFormatter df : DATE_FORMATTERS_WITHOUT_HOURS) {
                    try {
                        byString = localDateBy.format(df);
                        break;
                    } catch (DateTimeParseException e) {
                        // Ignore; try next formatter
                    }
                }
            }
        } else {}

        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}