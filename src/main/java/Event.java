import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event object that can be added
 * to users' task list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Event extends Task {
    private LocalDate dateOfEvent;

    protected Event(String[] arrString) throws DukeException {
        super(arrString.length < 2 ? " " : arrString[0]);
        String date = arrString[1] == null ? " " : arrString[1].strip();
        try {
            this.dateOfEvent = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeIncorrectTaskDescription(this, new IllegalArgumentException());
        }

    }

    /**
     * String representation of the Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " +
                this.dateOfEvent.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String saveToFile() {
        return "E | " + super.saveToFile() + "| " + this.dateOfEvent;
    }

}
