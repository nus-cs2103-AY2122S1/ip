import java.time.LocalDateTime;

public class Deadline extends Task {


    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }


    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description,
                isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDate() + ")";
    }


    public String formatDate() {
        if (by.getHour() == 0 && by.getMinute() == 0) {
            return String.format("%s %s %s", by.getDayOfMonth(), by.getMonth(), by.getYear());
        } else {
            return String.format("%s %s %s, %d%d", by.getDayOfMonth(), by.getMonth(), by.getYear(), by.getHour(),
                    by.getMinute());
        }

    }


    @Override
    public String saveString() {
        return String.format("D|%s|%s|%s",
                super.description,
                this.by,
                super.isDone ? "1" : "0");
    }
}
