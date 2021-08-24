import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private LocalDate dateTimeBy;
    public Deadlines(String description, String dateTimeBy) {
        super(description);
        this.dateTimeBy = LocalDate.parse(dateTimeBy.substring(3));
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + "(by: %s)", dateTimeBy.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
