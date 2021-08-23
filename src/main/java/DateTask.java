import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class DateTask extends Task {
    private LocalDate date;

    public DateTask(String description, String dateString) throws DukeException {
        super(description);
        assignDate(dateString);
    }

    private void assignDate(String dateString) throws DukeException {
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Oops!!! Date should be in this format: dd/MM/yyyy");
        }
    }

    public String formatDate() {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

}
