package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String taskContent;
    public final String type;
    private boolean completed = false;
    public Task(String taskContent, String type) {
        this.taskContent = taskContent;
        this.type = type;
    }
    public void markCompleted() {
        this.completed = true;
    }
    public String getTaskContent() {
        return this.taskContent;
    }
    public boolean isCompleted() {
        return this.completed;
    }

    public String getTiming() {
        return "";
    }
    public String formatTiming(String timing) {
        String formattedDateTime = "";
        String date= timing.split(" ")[0];
        String time = timing.split(" ")[1];
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2, 4));
        LocalDate formattedDate = LocalDate.parse(date);
        LocalTime formattedTime = LocalTime.of(hour, minute);
        formattedDateTime += formattedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " ";
        formattedDateTime += formattedTime.format(DateTimeFormatter.ofPattern("ha"));
        return formattedDateTime;
    }
}
