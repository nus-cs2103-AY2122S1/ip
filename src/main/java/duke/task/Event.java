package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String time;
    private LocalDate localDate;

    public Event(String content) throws duke.DukeException {
        super(content.substring(6, content.indexOf("/")).trim());
        time = content.substring(content.indexOf("/") + 1).trim();
        try {
            this.localDate = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new duke.DukeException(" ☹ SORZ but I only understand date in yyyy-MM-dd format!");
        }
    }

    public String getTime() {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                getStatus() ? "X" : " ", getContent(), time);
    }

    @Override
    public String record() {
        return String.format("E | %s | %s | %s",
                getStatus() ? "1" : "0", getContent(), time);
    }

    public String getType() {
        return "E";
    }

    public boolean hasSchedule() {
        return true;
    }
}
