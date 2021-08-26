package duke.tasks;

import duke.exceptions.EventException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String description, String at) throws EventException {
        super(description);
        if(description.equals("event")) {
            throw new EventException();
        }
        this.at = at;
    }

    public String getInfo() {
        return getDescription() + "/" + this.at;
    }

    public String getType() {
        return "E";
    };

    public LocalDate getDate() {
        String date = at.split(" ")[0];
        this.date = LocalDate.parse(date.split("/")[2] + "-"+ date.split("/")[1] + "-" + date.split("/")[0]);
        /*if(date.split("/").length != 0) {
            return LocalDate.parse()
        } else {
            return LocalDate.parse(date);
        }*/

        return this.date;
    }

    public LocalTime getStartTime() {
        String time = at.split(" ")[1];
        String startTime = time.split("-")[0];
        this.startTime = LocalTime.parse(startTime);
        return this.startTime;
    }

    public LocalTime getEndTime() {
        String time = at.split(" ")[1];
        String endTime = time.split("-")[1];
        this.endTime = LocalTime.parse(endTime);
        return this.endTime;
    }
    @Override
    public String storeTask() {
        return "E/" + getDone() + "/" + getDescription() + "/" + this.at;
    };

    @Override
    public String toString() {
        //return "[E]" + super.toString() + " (at: " + at + ")";
        return "[E]" + super.toString() + "(at: " + getDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " from " + getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")) + " to "
                + getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}
