package seedu.duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PeriodTask extends Task {
    private final LocalDate from;
    private final LocalDate to;

    public PeriodTask(String description, String from, String to) {
        super(description);

        this.from = LocalDate.of(Integer.parseInt(from.split("-")[2]), Integer.parseInt(from.split("-")[1]),
                Integer.parseInt(from.split("-")[0]));

        this.to = LocalDate.of(Integer.parseInt(to.split("-")[2]), Integer.parseInt(to.split("-")[1]),
                Integer.parseInt(to.split("-")[0]));
    }

    private PeriodTask(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String getSymbol() {
        return "PT";
    }

    @Override
    public PeriodTask markAsDone() {
        return new PeriodTask(this.getDescription(), this.from, this.to, true);
    }

    @Override
    public String toString() {
        return "[PT][" + this.getStatusIcon() + "] " + this.getDescription() + " between " + this.getFrom() + " and "
                + this.getTo();
    }
}
