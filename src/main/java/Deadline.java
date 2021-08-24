import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %d %d)", super.toString(), date.getMonth(), date.getDayOfMonth(), 
                date.getYear());
    }
}
