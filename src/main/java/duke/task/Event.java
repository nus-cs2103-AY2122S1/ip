package duke.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String taskName;
    private String at;
    private String type = "E";
    private LocalDate date;
    private int time;
    private int day;
    private int month;
    private int year;
    private String[] TaskAtSplit;

    public Event(String taskName) {
        String[] TaskAtSplit = taskName.split("/", 2);

        if (taskName.contains("|")) {
            TaskAtSplit = taskName.split("\\|", 2);
        } else if (taskName.contains("/at")) {
            TaskAtSplit = taskName.split("/at", 2);
        }

        this.taskName = TaskAtSplit[0].trim();
        this.at = TaskAtSplit[1].trim();
        String[] dateTimeSplit = this.at.split(" ", 2);
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
        return this.taskName + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " + this.time + ")";
    }

    public String showTaskOnly() { return this.taskName; }

    public String showWhen() { return this.at; }

    public String showType() {
        return this.type;
    }

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public LocalDate showDate() { return this.date; }

    public int showTime() { return this.time; }
}
