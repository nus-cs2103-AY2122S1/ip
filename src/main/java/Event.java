import java.time.LocalDate;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws DukeException1 {
        super(description);
        if(description.equals("event")) {
            throw new DukeException1();
        }
        this.at = at;
    }

    public String getInfo() {
        return getDescription() + "/" + this.at;
    }

    public String getType() {
        return "E";
    };

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
