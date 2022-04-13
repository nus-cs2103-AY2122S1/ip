package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

/**
 * <code>Deadline</code> subclass has two additional attributes:
 * A LocalDate "date" and a Localtime "time" to indicate the deadline of the Deadline object
 * The [D] in toString() identifies a Deadline object
 */

public class Deadline extends Task implements Comparable<Deadline>{
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time, boolean isDone) {
        //input: dd/mm/yyyy, time
        super(description, isDone);
        this.date = date;
        this.time = time;
    }
    private LocalDate getDate(){
        return date;
    }

    private LocalTime getTime(){
        return time;
    }
    @Override
    public int compareTo(Deadline d) {
        return Comparator.comparing(Deadline::getDate)
                .thenComparing(Deadline::getTime).compare(this, d);
    }
    @Override
    public String toString() {
        //output: yyyy-mm-dd, time
        return "[D] " + "[" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.date + " " + this.time + ")";
    }
}
