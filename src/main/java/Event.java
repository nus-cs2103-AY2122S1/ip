import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    LocalDate atDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    String[] monthArray = new  String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.atDate = LocalDate.parse(at, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + monthArray[atDate.getMonthValue()] + " " + atDate.getDayOfMonth() + " " + atDate.getYear() + ")";
    }
}
