package duke.task;

import duke.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private String time;
    private LocalDate localDate;

    public Deadline(String content) throws duke.DukeException {
        super(content.substring(9, content.indexOf("/")).trim());
        time = content.substring(content.indexOf("/") + 1).trim();
        try {
            this.localDate = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException(" ☹ SORZ but I only understand date in yyyy-MM-dd format!");
        }
        
    }

    public String getTime() {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                getStatus() ? "X" : " ", getContent(), time);
    }

    @Override

    public String record() {
        return String.format("D | %s | %s | %s",
                getStatus() ? "1" : "0", getContent(), time);
    }

    public String getType() {
        return "D";
    }

    public boolean hasSchedule() {
        return true;
    }
}
