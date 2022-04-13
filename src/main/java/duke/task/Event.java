package duke.task;

import duke.processor.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Tasks that start at a specific time and ends at a specific time
 * 
 * @author Tianqi-Zhu
 */
public class Event extends Task {
    private boolean isTimeString;
    private String timeString;
    private LocalDate startDate; 
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    public Event(String name, String time) {
        super(name);
        this.timeString = time;
        this.isTimeString = true;
    }

    public Event(String name, LocalDate startDate) {
        super(name);
        this.startDate = startDate;
        this.isTimeString = false; 
    }

    public Event(String name, LocalDate startDate, LocalTime startTime) {
        super(name);
        this.startDate = startDate;
        this.startTime = startTime;
        this.isTimeString = false; 
    }

    public void addEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void addEndDateTime(LocalDate endDate, LocalTime endTime) {
        this.endDate = endDate;
        this.endTime = endTime;
    }

    private String getTime() {
        if (isTimeString) {
            return timeString; 
        } else if (startTime == null && endDate == null) {
            return Ui.OUT_DATE_FORMATTER.format(startDate);
        } else if (startTime == null) {
            return Ui.OUT_DATE_FORMATTER.format(startDate) + "to " + Ui.OUT_DATE_FORMATTER.format(endDate);
        } else if (endDate == null) {
            return Ui.OUT_DATE_FORMATTER.format(startDate) + " " + Ui.OUT_TIME_FORMATTER.format(startTime);
        } else {
            return Ui.OUT_DATE_FORMATTER.format(startDate) + " " + Ui.OUT_TIME_FORMATTER.format(startTime) + "to "
                    + Ui.OUT_DATE_FORMATTER.format(endDate) + Ui.OUT_TIME_FORMATTER.format(endTime);
        }
    }

    public String toString() {
        if (isDone) {
            return "[E][X] " + name + " (at: " + getTime() + ")"; 
        } else {
            return "[E][ ] " + name + " (at: " + getTime() + ")"; 
        }
    }
}