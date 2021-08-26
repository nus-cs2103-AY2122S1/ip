import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Entry{

    private LocalDate event;

    Event() {
        super();
    }

    Event(String task, String event) throws DukeException {
        super(task);
        try {
            this.event = LocalDate.parse(event);
        } catch (DateTimeParseException e) {
            this.event = LocalDate.now();
            throw new DukeException("Invalid timing format! Enter dates in yyyy-mm-dd format");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + "(at: " + this.event.format(formatter) + ")";
    }

    @Override
<<<<<<< HEAD
    public boolean isEmpty() { return super.isEmpty() || event.length() < 1; }

    @Override
    public String saveString() {
        return "E" + super.saveString() + "," + this.event;
    }
=======
    public boolean isEmpty() { return super.isEmpty() && event.isAfter(LocalDate.now()); }
>>>>>>> branch-Level-8
}
