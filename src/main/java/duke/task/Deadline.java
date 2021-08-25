package duke.task;

import duke.exception.EmptyFieldException;
import duke.exception.InvalidCommandException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String by) throws EmptyFieldException, InvalidCommandException {
        super(description);
        if (description.equals("") || by.equals("")) {
            //check for empty description or by
            throw new EmptyFieldException("     Error! Description or date and time is empty!");
        } else {
            String[] dateTime = by.split(" ");
            if (dateTime.length > 2) {
                //check for more than 1 date and time entered
                throw new InvalidCommandException(
                        "     Error! You can only enter one date and time, Eg: \"2021-09-12 18:00\",\n"
                                + "     one date, Eg: \"2021-09-12\" (This will enter time as 23:59 by default),\n"
                                + "     or one time, Eg: \"18:00\" (This will enter today's date by default)");
            } else {
                this.by = by;
                if (dateTime.length == 1) {
                    //user only entered a single date or time
                    if (dateTime[0].length() > 5) {
                        //user entered date only. Set default time to 23:59.
                        this.date = LocalDate.parse(dateTime[0]);
                        this.time = LocalTime.MAX;
                    } else {
                        //user entered time only. Set date to today's date.
                        this.date = LocalDate.now();
                        this.time = LocalTime.parse(dateTime[0]);
                    }
                } else {
                    //user entered both date and time
                    this.date = LocalDate.parse(dateTime[0]);
                    this.time = LocalTime.parse(dateTime[1]);
                }
            }
        }
    }

    @Override
    public String saveToFile() {
        String[] dateTime = this.by.split(" ");
        if (dateTime.length == 1 && dateTime[0].length() == 5) {
            //user only entered time. so save task date (default)
            return "D " + super.saveToFile() + " | " + this.date.toString() + " " + this.by;
        } else {
            return "D " + super.saveToFile() + " | " + this.by;
        }
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMMM d yyyy"))
                + ", "
                + this.time.format(DateTimeFormatter.ofPattern("h:mm a"))
                + ")";
    }
}