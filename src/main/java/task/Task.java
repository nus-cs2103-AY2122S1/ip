package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task {
    public String type;
    public String date_time;
    public String message;


    public Task (
            String message) {
        this.message = message;
        }

    public Task (
            String message,
                boolean isDuke) {}

    public String getType() {
        return this.type;
    }

    public void setType() {
        this.type = "";
    }

    public void setTask() {
    }

    public void setTask2() {
    }

    public String getTask() {
        return "";
    }

    public void setDateTime() {
        this.date_time = "";
    }

    public String getDateTime() {
        return date_time;
    }

}

