package duke.tasks;

import duke.exceptions.DeadlineException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    private String by;
    private LocalDate date;
    private LocalTime time;

    public Deadline(String description, String by) throws DeadlineException {
        super(description);
        if(description.equals("deadline")) {
            throw new DeadlineException();
        }
        this.by = by;
    }

    public String getInfo() {
        return getDescription() + "/" + this.by;
    }

    public String getType() {
        return "D";
    };

    public LocalDate getDate() {
        String date = by.split(" ")[0];
        this.date = LocalDate.parse(date.split("/")[2] + "-"+ date.split("/")[1] + "-" + date.split("/")[0]);
        /*if(date.split("/").length != 0) {
            return LocalDate.parse()
        } else {
            return LocalDate.parse(date);
        }*/

        return this.date;
    }

    public LocalTime getTime() {
        String time = by.split(" ")[1];
        this.time = LocalTime.parse(time);
        return this.time;
    }

    @Override
    public String storeTask() {
        return "D/" + getDone() + "/" + getDescription() + "/" + this.by;
    };

    @Override
    public String toString() {
        //return "[D]" + super.toString() + " (by: " + by + ")";
        return "[D]" + super.toString() + "(by: " + getDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + getTime().format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}
