import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String time;
    private LocalDate localDate;

    Event(String content) throws DukeException {
        super(content.substring(6, content.indexOf("/")));
        this.time = content.substring(content.indexOf("/") + 1).trim();
        try {
            this.localDate = LocalDate.parse(this.time);
        } catch (DateTimeParseException e) {
            throw new DukeException(" ☹ SORZ but I only understand date in yyyy-MM-dd format!");
        }
    }

    public String getTime() {
        return this.localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                this.getStatus() ? "X" : " ", this.getContent(), this.time);
    }

    @Override
    public boolean hasSchedule() {
        return true;
    }
}
