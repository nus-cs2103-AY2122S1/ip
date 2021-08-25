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
}
