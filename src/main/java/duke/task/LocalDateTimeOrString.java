package duke.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeOrString implements Serializable {
    private static final DateTimeFormatter IN_FORMATTER = DateTimeFormatter
            .ofPattern("" + "[dd-MM-yyyy HHmm]" + "[dd/MM/yyyy HHmm]");
    private static final DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a");
    private LocalDateTime dateTime;
    private String dateTimeString;

    public LocalDateTimeOrString(String dateTimeInput) {
        try {
            dateTime = LocalDateTime.parse(dateTimeInput, IN_FORMATTER);
        } catch (DateTimeParseException e) {
            dateTimeString = dateTimeInput;
        }
    }

    public String getDesc() {
        return (dateTime != null) ? OUT_FORMATTER.format(dateTime) : dateTimeString;
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