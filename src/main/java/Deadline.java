import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected String returnDate;
    protected LocalDateTime dateTime;
    protected LocalDate date;
    private static final String[] dateTimeFormats = {
            "dd/MM/yyyy HHmm",
            "dd-MM-yyyy HHmm",
            "dd/MM/yyyy hh:mm a",
            "dd-MM-yyyy hh:mm a",
            "dd/MM/yyyy HHmm",
            "dd-MM-yyyy HHmm",
            "yyyy/MM/dd HHmm",
            "yyyy-MM-dd HHmm",
            "yyyy/MM/dd hh:mm a",
            "yyyy-MM-dd hh:mm a",
            "yyyy/MM/dd HHmm",
            "yyyy-MM-dd HHmm"
    };
    private static final String[] dateFormats = {
            "dd/MM/yyyy",
            "dd-MM-yyyy",
            "yyyy/MM/dd",
            "yyyy-MM-dd"
    };
    private String detectedFormat;

    public Deadline(String restOfInput, Boolean empty) throws DukeException {
        super(restOfInput);

        if (empty || restOfInput.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        if (restOfInput.contains("/by")) {
            description = restOfInput.split("/by", 2)[0];
            returnDate = restOfInput.split("/by", 2)[1];
            String date = returnDate.split(" ", 2)[1];

            if (description.trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (returnDate.trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! The deadline of a... deadline cannot be empty.");
            }

            if (isDateTime(date)) {
                this.dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(detectedFormat));
            } else if (isDate(date)) {
                this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern(detectedFormat));
            }

        } else {
            throw new DukeException("☹ OOPS!!! The deadline of a... deadline cannot be empty.");
        }
    }

    private boolean isDateTime(String dateTimeString) {
        boolean isDateAndTime = false;

        for (String i : dateTimeFormats) {
            try {
                LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(i));
                detectedFormat = i;
                isDateAndTime = true;
            } catch (Exception e) {
            }
        }
        return isDateAndTime;
    }

    private boolean isDate(String dateString) {
        boolean isDate = false;

        for (String i : dateFormats) {
            try {
                LocalDate.parse(dateString, DateTimeFormatter.ofPattern(i));
                detectedFormat = i;
                isDate = true;
            } catch (Exception e) {
            }
        }
        return isDate;
    }

    @Override
    public String toString() {

        if (this.dateTime != null) {
            return String.format("[D]%s(by: %s)", super.toString(),
                    this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")));
        } else if (this.date != null) {
            return String.format("[D]%s(by: %s)", super.toString(),
                    this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } else {
            return String.format("[D]%s(by:%s)", super.toString(), this.returnDate);
        }
    }
}
