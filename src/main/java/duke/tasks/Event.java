package duke.tasks;

import duke.utils.DukeDateTime;
import duke.utils.DukeException;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private DukeDateTime end;

    public Event() {}
    public Event(String desc) {
        super(desc);
    }
    public Event(String entry, boolean done) throws DukeException {
        try {
            String[] args = entry.split("( \\| )", 4);
            if (args.length < 4) throw new DukeException("Error reading DB");
            super.desc = args[0];
            super.done = done;
            super.dateTime = DukeDateTime.parse(args[1]);
            end = DukeDateTime.parse(args[2]);
            if (args.length == 4) super.details = args[3];
        } catch (DateTimeException e) {
            throw new DukeException("Error reading DB");
        }
    }

    public void addTime(String rawArgs) throws DukeException {
        String[] args = rawArgs.split(" / ");
        if (args.length == 0) return;
        try {
            String[] parts = args[0].split("~");
            dateTime = DukeDateTime.parse(parts[0].trim());
            if (parts.length > 1) end = DukeDateTime.parse(parts[1].trim());
            if (args.length > 1) details = args[1];
        } catch (DateTimeException e) {
            details = rawArgs;
        }
    }

    public String toDB() {
        DateTimeFormatter d = DateTimeFormatter.ofPattern("HH:mm");
        // return String.format("E | %d | %s | %s", super.done ? 1 : 0, super.desc, time);
        return String.format("E | %d | %s | %s | %s | %s", super.done ? 1 : 0, super.desc, 
                dateTime.format(DukeDateTime.Format.DATE_LONG, DukeDateTime.Format.PRINT_TIME), 
                end.format(DukeDateTime.Format.DATE_LONG, DukeDateTime.Format.TIME), details);
    }

    @Override
    public String toString() {
        String timeSeq = dateTime.format() + " to " + end.format();
        String detailsAdd = (details.equals(new String()) ? details : " -- " + details);
        return "[E]" + super.toString() + String.format(" (at: %s%s)", timeSeq, detailsAdd);
    }
}
