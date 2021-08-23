package com.duke.task;

import com.duke.parser.Parser;

import java.io.IOException;
import java.time.LocalDate;

public class Event extends TaskList {
    private final String time;
    private LocalDate localDate;
    private String deadLineTiming;

    public Event(String description, String time, boolean isExisting) {
        super(description);
        this.time = time;
        if (!isExisting) {
            localDate = Parser.findDate(time);
            deadLineTiming = Parser.findTime(time);
            if (deadLineTiming != null) deadLineTiming = Parser.convertTime(deadLineTiming);
            try {
                file.saveTask(this); // Saves task to hard disk
                userInterface.taskAdded(this);
            } catch (IOException e) {
                userInterface.fileNotFoundWarning();
            }
        } else {
            Parser.parseTime(time,localDate, deadLineTiming);
        }
    }

    @Override
    public String toString() {
        String status = this.getStatusIcon();
        if (localDate == null) {
            return "[E]" + "[" + status + "] " + this.description
                    + "(" + time + ")";
        } else {
            String endTime = deadLineTiming == null ? "" : " "+ deadLineTiming;
            return "[E]" + "[" + status + "] " + this.description
                    + "(" + localDate.getDayOfMonth() + " " + localDate.getMonth()
                    + " " + localDate.getYear() + endTime + ")";
        }
    }
}
