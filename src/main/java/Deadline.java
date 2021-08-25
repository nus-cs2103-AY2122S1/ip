package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;

    public Deadline(String toDo, LocalDate date, LocalTime time){
        super(toDo);
        this.date = date;
        this.time = time;
    }

    String getType() {
        return "D";
    }
    
    String getDateString() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))  + ", " + 
                time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    public String getToWrite() {
        return this.getType() + " | " + super.getToWrite() + " | " + this.getDateString();
    }

    @Override
    public String toString() {
        return("[D]" + super.toString() + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " 
                + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")");
    }
}