import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadlines extends Task {
    private LocalDate date;

    public Deadlines(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                this.date.format(
                        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
    }
}
