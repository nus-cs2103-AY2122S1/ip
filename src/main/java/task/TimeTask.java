package task;

import parser.Parser;

import command.InvalidTimeFormatException;
import task.Task;

import java.time.LocalDate;

public abstract class TimeTask extends Task {

    private LocalDate time;


    public TimeTask(String description) {
        super(description);
    }

    public TimeTask(String description, boolean isDone) {
        super(description, isDone);
    }

    public TimeTask(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public TimeTask(String description, String timeString) {
        super(description);
        this.time = Parser.parseTimeString(timeString);
    }

    public TimeTask(String description, boolean isDone, String timeString) {
        super(description, isDone);
        this.time = Parser.parseTimeString(timeString);
    }

    public LocalDate getTime() {
        return this.time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
}
