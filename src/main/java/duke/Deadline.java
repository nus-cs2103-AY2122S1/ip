package duke;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");
    private DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Deadline(String desc, String deadline) throws DukeException {
        super(desc.trim());
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(deadline);
            LocalDateTime newDT = new java.sql.Timestamp(date.getTime()).toLocalDateTime();
            this.deadline = newDT;
        } catch (DateTimeParseException | ParseException e) {
            throw new DukeException();
        }
    }

    public Deadline(String desc, String deadline, boolean isDone) throws DukeException {
        super(desc.trim(), isDone);
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(deadline);
            LocalDateTime newDT = new java.sql.Timestamp(date.getTime()).toLocalDateTime();
            this.deadline = newDT;
        } catch (ParseException e) {
            throw new DukeException();
        }
    }

    @Override
    public String saveTask() {
        return "D|" + this.isDone() + "|" + getDesc() + "|" + deadline.format(saveFormat) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + this.statusIcon() + this.getDesc() + " (by: " + deadline.format(formatter) + ")";
    }
}
