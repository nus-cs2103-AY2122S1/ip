import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        String res = "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (time != null) {
            res += " " + time.format(DateTimeFormatter.ofPattern("H:mm"));
        }
        return res += ")";
    }

    @Override
    public String saveFormat() {
        String res = "D _ " + super.saveFormat() + " _ " + this.date;
        if (time != null) {
            res += " _ " + this.time;
        }
        return res;
    }
}