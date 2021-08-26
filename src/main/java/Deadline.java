import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description, "[D]");
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
