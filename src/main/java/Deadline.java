import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String by;
    private LocalDate byDate;

    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    public Deadline(String description, String by) {
        super(description);
        this.byDate = LocalDate.parse(by);
    }

    @Override
    public String toString() {
         return "D" + super.toString() + " | " + this.by;
    }
}
