import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event {
    
    private Event event;
    private LocalDate eventDate;
    
    public Event(String input) {
        //create dates from strings
        this.eventDate = LocalDate.parse(input);
        this.eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}