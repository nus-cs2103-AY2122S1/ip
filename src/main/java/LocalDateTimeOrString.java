import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class LocalDateTimeOrString {
    private Optional<LocalDateTime> dateTime = Optional.empty();
    private Optional<String> dateTimeString = Optional.empty();

    private static final DateTimeFormatter IN_FORMATTER = DateTimeFormatter
            .ofPattern("" + "[dd-MM-yyyy HHmm]" + "[dd/MM/yyyy HHmm]");
    private static final DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a");
    private static final String INVALID_DATE = "Invalid date pattern.";

    public LocalDateTimeOrString(String dateTimeInput) {
        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeInput, IN_FORMATTER);
            dateTime = Optional.ofNullable(parsedDateTime);
        } catch (DateTimeParseException e) {
            dateTimeString = Optional.ofNullable(dateTimeInput);
        }
    }

    protected String getDateTimeDesc() {
        return dateTime.map(x -> OUT_FORMATTER.format(x)).or(() -> dateTimeString).orElse(INVALID_DATE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (!(o instanceof LocalDateTimeOrString)) {
            return false;
        }
        LocalDateTimeOrString other = (LocalDateTimeOrString) o;
        return dateTime.equals(other.dateTime) && dateTimeString.equals(other.dateTimeString);
    }
}