import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate at;

    public Event(String input, String at) throws DateTimeParseException{
        super(input);
        int space = at.indexOf(" ");
        String format = space > 0 ? "d/MM/yyyy HHmm" : "d/MM/yyyy"; 
        this.at = LocalDate.parse(at.trim(), DateTimeFormatter.ofPattern(format));
        if (this.at.isBefore(LocalDate.now())) {
            super.completeItem();
        }
    }

    @Override
    public boolean compareDate(LocalDate compare) {
        return this.at.equals(compare);
    }

    @Override
    public  String toString() {
        return "[E] " + super.toString() + "(at: " + this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}