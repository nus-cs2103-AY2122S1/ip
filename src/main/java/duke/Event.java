package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date date;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.date = new SimpleDateFormat("dd/MM/yyyy").parse(at);
        }catch (ParseException e) {
            throw new DukeException("Date format incorrect");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + date.toString() + ")";
    }
}