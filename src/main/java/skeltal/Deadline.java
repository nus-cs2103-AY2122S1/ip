package skeltal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String time;

    public Deadline(String rawTime) throws SkeltalException {
        super(rawTime.split("/", 2)[0]);
        String[] procTime = rawTime.split("/", 2);
        if (procTime.length == 1) {
            throw new SkeltalException("OOPS! The description of a deadline cannot be empty!");
        }

        String time;
        try {
            LocalDate date = LocalDate.parse(procTime[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            time = date.format(DateTimeFormatter.ofPattern("dd MMM yy"));
        } catch (DateTimeParseException e) {
            time = procTime[1];
        }
        this.time = time;
    }

    private String formatTime() {
        return "(" + this.time + ")";
    }

    @Override
    public String store() {
        return "D | " + super.store() + "| " + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + formatTime();
    }
}
