package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate byDate;
    private LocalTime byTime;

    public Deadline (String taskDetails, String byDate, String byTime) {
        super(taskDetails);
        this.byDate = LocalDate.parse(byDate);
        this.byTime = LocalTime.parse(byTime);
    }

    public String taskType() {
        return "D";
    }

    @Override
    public String getTaskDetails() {
        return super.getTaskDetails() + " | " + byDate + " | " + byTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + 
                byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + byTime + ")";
    }
}