package duke.tasks;

import duke.utils.DukeDateTime;
import duke.utils.DukeException;

import java.time.DateTimeException;

public class Deadline extends Task {

    public Deadline() {}
    public Deadline(String desc) {
        super(desc);
    }
    public Deadline(String entry, boolean done) throws DukeException {
        try {
            String[] args = entry.split("( \\| )", 3);
            if (args.length < 3) throw new DukeException("Error reading DB");
            super.desc = args[0];
            super.done = done;
            super.dateTime = DukeDateTime.parse(args[1]);
            if (args.length == 3) super.details = args[2];
        } catch (DateTimeException e) {
            throw new DukeException("Error reading DB");
        }
}

    public void addTime(String rawArgs) throws DukeException {
        String[] args = rawArgs.split(" / ");
        if (args.length == 0) return;
        try {
            dateTime = DukeDateTime.parse(args[0].trim());
            if (args.length > 1) details = args[1];
        } catch (DateTimeException e) {
            details = rawArgs;
        }
    }
    
    public String toDB() {
        // return String.format("D | %d | %s | %s", super.done ? 1 : 0, super.desc, dl);
        return String.format("D | %d | %s | %s | %s", super.done ? 1 : 0, super.desc,
                dateTime.format(DukeDateTime.Format.DATE_LONG, DukeDateTime.Format.PRINT_TIME),
                details);
    }

    @Override
    public String toString() {
        String timeSeq = dateTime.format();
        String desc = (details.equals(new String()) ? details : ", " + details);
        return "[D]" + super.toString() + (timeSeq != null ? String.format(" (by: %s)", timeSeq) : "") + desc;
    }
}
