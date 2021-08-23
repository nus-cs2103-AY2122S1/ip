import java.time.DateTimeException;
import java.time.LocalDateTime;

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
            super.dateTime = LocalDateTime.parse(args[1], formatter);
        } catch (DateTimeException e) {
            throw new DukeException("Error reading DB");
        }
}

    public void addTime(String rawArgs) throws DukeException {
        String[] args = rawArgs.split(" / ");
        if (args.length == 0) return;
        try {
            dateTime = LocalDateTime.parse(args[0].trim(), formatter);
            if (args.length > 1) details = args[1];
        } catch (DateTimeException e) {
            details = rawArgs;
        }
    }
    
    
    public String toDB() {
        // return String.format("D | %d | %s | %s", super.done ? 1 : 0, super.desc, dl);
        return String.format("D | %d | %s | %s | %s", super.done ? 1 : 0, super.desc, dateTime.format(Task.formatter),
                details);
    }

    @Override
    public String toString() {
        String timeSeq = formatTime();
        return "[D]" + super.toString() + (timeSeq != null ? String.format(" (by: %s)", timeSeq) : "");
    }
}
