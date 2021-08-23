import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalTime end;

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
            super.dateTime = LocalDateTime.parse(args[1], formatter);
            end = LocalTime.parse(args[2]);
        } catch (DateTimeException e) {
            throw new DukeException("Error reading DB");
        }
    }

    public void addTime(String rawArgs) throws DukeException {
        String[] args = rawArgs.split(" / ");
        if (args.length == 0) return;
        try {
            String[] parts = args[0].split("~");
            dateTime = LocalDateTime.parse(parts[0].trim(), formatter);
            if (parts.length > 1) end = LocalTime.parse(parts[1].trim());
            if (args.length > 1) details = args[1];
        } catch (DateTimeException e) {
            details = rawArgs;
        }
    }

    public String toDB() {
        DateTimeFormatter d = DateTimeFormatter.ofPattern("HH:mm");
        // return String.format("E | %d | %s | %s", super.done ? 1 : 0, super.desc, time);
        return String.format("E | %d | %s | %s | %s | %s", super.done ? 1 : 0, super.desc, 
                dateTime.format(Task.formatter), end.format(d), details);
    }

    @Override
    protected String formatTime() {
        if (details == null && dateTime == null) return null;
        if (dateTime == null) return details;
        String dt = String.format("%s to %s on %s",
                dateTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                end.format(DateTimeFormatter.ofPattern("HH:mm")),
                dateTime.format(DateTimeFormatter.ofPattern("dd MMM")));
        if (details == null) return dt;
        return String.format("%s -- %s", dt, details);
    }

    @Override
    public String toString() {
        String timeSeq = formatTime();
        return "[E]" + super.toString() + (timeSeq != null ? String.format(" (at: %s)", timeSeq) : "");
    }
}
