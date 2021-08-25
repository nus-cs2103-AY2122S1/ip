import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String endTime;

    public Deadline(String str, LocalDateTime endTime) {
        super(str, endTime);
        DateTimeFormatter dateOnly = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        this.endTime = dateOnly.format(endTime);
    }



    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime + ")";
    }
}
