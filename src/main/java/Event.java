import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date date;

    public Event(String description, String at) throws Exception {
        super(description);
        this.date = new SimpleDateFormat("dd/MM/yyyy").parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + date.toString() + ")";
    }
}