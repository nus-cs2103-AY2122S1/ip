import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructor for Event
     * @param input the input array consisting of description and date/time
     */
    public Event(String[] input) throws EmptyDescriptionException, EmptyTimeException, InvalidTimeException {
        super(input[0]);
        if (input.length < 2) throw new EmptyTimeException();
        try {
            this.at = LocalDateTime.parse(input[1], DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException();
        }
    }
    
    public Event(String[] input, boolean isDone) {
        super(input[0], isDone);
        this.at = input[1];
    }
    
    @Override
    public String convertToData() {
        return String.format("E/%s/%s/%s", this.isDone ? "1" : "0", this.description, this.at);
    }

    /**
     * Returns string representation of Event
     * @return string representation of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma")) + ")";
    }

}
