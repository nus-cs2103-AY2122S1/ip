import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Event build(String desc_date) {
        String[] input = desc_date.split(" /by ",2);
        return new Event(input[0], input[1]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
