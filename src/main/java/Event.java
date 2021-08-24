import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private String at;
    private LocalDate atDate;

    public Event(String description, LocalDate atDate) {
        super(description);
        this.atDate = atDate;
    }

    public Event(String description, String at) {
        super(description);
        this.atDate = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "E" + super.toString() + " | " + this.at;
    }
}
