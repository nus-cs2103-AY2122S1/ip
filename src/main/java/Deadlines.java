import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadlines extends Task {
    private LocalDate date;

    public Deadlines(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public Deadlines(boolean completed, String name, String deadline) {
        super(completed, name);
        this.date = LocalDate.parse(deadline);
    }

    @Override
    public String getSaveData() {
        if (this.isCompleted()) {
            return String.format("D~1~%s~%s", this.getName(), this.date);
        } else {
            return String.format("D~0~%s~%s", this.getName(), this.date);
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                this.date.format(
                        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
    }
}
