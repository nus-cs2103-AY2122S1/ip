package duke.task;

import duke.processor.Ui;

import java.time.LocalDate;
import java.time.LocalTime; 

/**
 * Tasks that need to be done before a specific date/time
 * 
 * @author Tianqi-Zhu
 */
public class Deadline extends Task {
    private LocalTime endTime;
    private LocalDate endDate;
    private String timeString;
    private boolean isTimeString;
    
    public Deadline(String name, LocalDate endDate) {
        super(name);
        this.endDate = endDate;
        isTimeString = false; 
    }

    public Deadline(String name, LocalDate endDate, LocalTime endTime) {
        super(name);
        this.endDate = endDate; 
        this.endTime = endTime; 
        isTimeString = false; 
    }

    public Deadline(String name, String time) {
        super(name);
        this.timeString = time; 
        isTimeString = true; 
    }

    private String getTime() {
        if (isTimeString) {
            return timeString; 
        } else if (endTime == null) {
            return Ui.OUT_DATE_FORMATTER.format(endDate);
        } else {
            return Ui.OUT_DATE_FORMATTER.format(endDate) + " " + Ui.OUT_TIME_FORMATTER.format(endTime);
        }
    }
    
    public String toString() {
        if (isDone) {
            return "[D][X] " + name + " (by: " + getTime() + ")"; 
        } else {
            return "[D][ ] " + name + " (by: " + getTime() + ")"; 
        }
    }
}