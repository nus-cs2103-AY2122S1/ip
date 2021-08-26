import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;

    }

    @Override
    public String toString() {


        return ("E "
                + super.toString()
                + " - "
                + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
        );
    }

    @Override
    public String toStoredString() {
        return ("E "
                + super.toString()
                + " - "
                + time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }
}