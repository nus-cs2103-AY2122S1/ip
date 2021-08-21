import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private LocalDate at;

    public Event(String name, String at) throws DukeException {
        super(name);
        try {
            this.at = LocalDate.parse(at);
        } catch(DateTimeParseException e) {
            throw new DukeException("incorrect date format");
        }

    }

    @Override
    public String saveTask(){
        return "EVENT|" + this.getName() +"/at" + at + (this.isDone() ? "|1" : "|0");
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
