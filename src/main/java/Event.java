import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    private LocalDateTime at;
    private LocalDateTime end;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        String[] tokens = at.split(" to ");
        this.at = LocalDateTime.parse(tokens[0], DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        if (tokens.length > 1) {
            end = LocalDateTime.parse(tokens[1], DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        }
    }

    public Event(String description, String at) {
        super(description);
        String[] tokens = at.split(" to ");
        this.at = LocalDateTime.parse(tokens[0], DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        if (tokens.length > 1) {
            end = LocalDateTime.parse(tokens[1], DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        }
    }

    public String formatForSave() {
        return "E" + super.formatForSave() + " | " + at;
    }

    @Override
    public String toString() {
        String s = "[E]" + super.toString();
        if (end == null) {
            s += " (at: " + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy h.mma")) + ")";
        } else {
            s += " (from: " + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy h.mma — "))
                    + end.format(DateTimeFormatter.ofPattern("dd MMM yyyy h.mma")) + ")";
        }
        return s;
    }
}