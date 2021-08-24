import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    private LocalDate atDate;

    public Event(String description, String at) throws InvalidFormatException {
        super(description);
        try {
            this.atDate = LocalDate.parse(at);
        } catch (DateTimeParseException e){
            throw new InvalidFormatException("date", "yy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "E" + super.toString() + " | "
                + this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
