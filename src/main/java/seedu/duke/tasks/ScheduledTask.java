package seedu.duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScheduledTask extends Task {
    private final LocalDate taskDate;
    private final String date;
    private final int timeFrom;
    private final int timeTo;

    public ScheduledTask(String description, String date, int timeFrom, int timeTo) {
        super(description);

        this.taskDate = LocalDate.of(Integer.parseInt(date.split("-")[2]), Integer.parseInt(date.split("-")[1]),
                Integer.parseInt(date.split("-")[0]));
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.date = date;
    }

    private ScheduledTask(String description, LocalDate localDate, int timeFrom, int timeTo, boolean isDone) {
        super(description, isDone);
        this.taskDate = localDate;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.date = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public LocalDate getTaskDate() {
        return this.taskDate;
    }

    public int getTimeFrom() {
        return this.timeFrom;
    }

    public int getTimeTo() {
        return this.timeTo;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String getSymbol() {
        return "ST";
    }

    @Override
    public ScheduledTask markAsDone() {
        return new ScheduledTask(this.getDescription(), this.getTaskDate(), this.getTimeFrom(), this.getTimeTo(), true);
    }

    @Override
    public String toString() {
        return "[ST][" + this.getStatusIcon() + "] " + this.getDescription() + " on " + this.getDate() + " at "
                + this.getTimeFrom() + " to " + this.getTimeTo();
    }

}
