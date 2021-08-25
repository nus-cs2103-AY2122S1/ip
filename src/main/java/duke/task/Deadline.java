package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String taskName, by, type = "D";
    private LocalDate date;
    private int time, day, month, year;

    public Deadline(String taskName) {
        String[] TaskBySplit = taskName.split("/", 2);

        if (taskName.contains("|")) {
            TaskBySplit = taskName.split("\\|", 2);
        } else if (taskName.contains("/by")) {
            TaskBySplit = taskName.split("/by", 2);
        }

        this.taskName = TaskBySplit[0].trim();
        this.by = TaskBySplit[1].trim();
        String[] dateTimeSplit = this.by.split(" ", 2);
        this.time = Integer.parseInt(dateTimeSplit[1]);

        if (dateTimeSplit[0].contains("/")) {
            String[] dateSplit = dateTimeSplit[0].split("/", 3);
            this.day = Integer.parseInt(dateSplit[0]);
            this.month = Integer.parseInt(dateSplit[1]);
            this.year = Integer.parseInt(dateSplit[2]);
            this.date = LocalDate.of(this.year, this.month, this.day);
        } else if (dateTimeSplit[0].contains("-")) {
            this.date = LocalDate.parse(dateTimeSplit[0]);
        }
    }

    public String showTask() {
        return this.taskName + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.time + ")";
    }

    public String showTaskOnly() {
        return this.taskName;
    }

    public String showWhen() {
        return this.by;
    }

    public String showType() {
        return this.type;
    }

    public LocalDate showDate() { return this.date; }

    public int showTime() { return this.time; }

}
