package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class Deadline extends Task {

    protected Date date;

    public Deadline(String description, String by) throws DukeException{
        super(description);
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(by);
        } catch (ParseException e) {
            throw new DukeException("Date incorrect format");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + new SimpleDateFormat("dd/MM/yyyy").format(date) + ")";
    }
}