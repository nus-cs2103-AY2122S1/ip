import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Task {

    private String description;
    private boolean isDone = false;
    private LocalDate date = null;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean done) {
        this.isDone = done;
        this.description = description;
    }

    public Task(String description, String date) throws DukeException {
        this.description = description;
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date format!");
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    private String getDoneSymbol() {
        return isDone ? "X" : " ";
    }


    private String getDoneString() { return isDone ? "1" : "0"; }

    public String toFileData() { return String.format("%s,%s", getDoneString(), description); }

    public String getDateString() {
        return this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneSymbol(), description);
    }
}
