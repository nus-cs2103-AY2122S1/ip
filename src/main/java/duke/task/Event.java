package duke.task;

import duke.exception.EmptyFieldException;
import duke.exception.InvalidCommandException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Represents events (a type of tasks) that Duke can keep track of.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class Event extends Task {

    /** A string representing the start date and time of an Event. */
    protected String at;

    /** The date of an Event. */
    protected LocalDate date;

    /** The time of an Event. */
    protected LocalTime time;

    /**
     * Constructor of the Deadline class.
     *
     * @param description A string representing the description of an event.
     * @param at A string representing the start date and time of an event.
     * @throws EmptyFieldException if the description or at is empty.
     * @throws InvalidCommandException if the description or at is invalid.
     */
    public Event(String description, String at) throws EmptyFieldException, InvalidCommandException {
        super(description);
        if (description.equals("") || at.equals("")) {
            //check for empty description or by
            throw new EmptyFieldException("     Error! Description or date and time is empty!");
        } else {
            String[] dateTime = at.split(" ");
            if (dateTime.length > 2) {
                //check for more than 1 date and time entered
                throw new InvalidCommandException(
                        "     Error! You can only enter one date and time, Eg: \"2021-09-12 18:00\",\n"
                                + "     one date, Eg: \"2021-09-12\" (This will enter time as 23:59 by default),\n"
                                + "     or one time, Eg: \"18:00\" (This will enter today's date by default)");
            } else {
                this.at = at;
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

    /**
     * Returns a string that is used to represent events when saved to a file.
     *
     * @return A string representation of an event for saving to files.
     */
    @Override
    public String saveToFile() {
        String[] dateTime = this.at.split(" ");
        if (dateTime.length == 1 && dateTime[0].length() == 5) {
            //user only entered time. so save task date (default)
            return "E " + super.saveToFile() + " | " + this.date.toString() + " " + this.at;
        } else {
            return "E " + super.saveToFile() + " | " + this.at;
        }
    }

    /**
     * Returns a string that is used to represent events when Duke is interacting with a user.
     *
     * @return A string representation of an event to be displayed to users.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMMM d yyyy"))
                + ", "
                + this.time.format(DateTimeFormatter.ofPattern("h:mm a"))
                + ")";
    }
}