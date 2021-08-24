package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private String desc;
    private boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isDone() {
        return isDone;
    }

    public String statusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String saveTask() {
        String res = "T|" + isDone + "\\|" + desc;
        return res;
    }
}
