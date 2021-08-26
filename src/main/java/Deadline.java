import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return ("D "
                + super.toString()
                + " / "
                + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
        );
    }

    @Override
    public String toStoredString() {
        return ("D "
                + super.toString()
                + " / "
                + time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }
}