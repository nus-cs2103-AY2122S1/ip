import java.util.Scanner;

public class Event extends Task{
    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description, "event");
        this.at = at;
    }

    @Override
    public String saveTaskFormat(){
        return "E" + super.saveTaskFormat() + String.format("|%s", at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
