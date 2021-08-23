import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    String time;

    public Event(String name) throws DukeEventException{
        super(name.substring(0, name.indexOf(" /at ") + 1));
        this.time = name.substring(name.indexOf(" /at ") + 5);
        if (name.equals("")) {
            throw  new DukeEventException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time + ")";
    }

    @Override
    public String toDataString() {
        return "E|" + super.toDataString() + "|" + this.time;
    }
}
