package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String time;
    protected LocalDate eventTime;


    Event(String description) {
        super(description);
        this.time = "";
        this.eventTime = null;
    }

    Event(String description, boolean isDone) {
        super(description, isDone);
        this.time = "";
        this.eventTime = null;
    }

    Event(String description, String time) {
        super(description);
        this.time = time;
        this.eventTime = setEventTime(time);
    }

    Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
        this.eventTime = setEventTime(time);
    }

    public String getTime() {
        if (this.eventTime == null) {
            return this.time;
        } else {
            return this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    public LocalDate setEventTime(String time) {
        try {
            LocalDate date = LocalDate.parse(time);
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String saveTaskToFile() {
        return typeOfTask() + "||" + getStatusIcon() + "||" + this.getDescription() + "||" + this.getTime();
    }

    @Override
    public String typeOfTask() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", this.typeOfTask(),
                this.getStatusIcon(), this.getDescription(), this.getTime());
    }
}
