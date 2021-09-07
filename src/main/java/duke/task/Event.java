package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.exception.EmptyFieldException;
import duke.exception.InvalidCommandException;

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
     * Constructor of the Event class.
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
            String errorMessage = "Error! Description or date and time is empty!";
            throw new EmptyFieldException(errorMessage);
        }
        String[] dateTime = at.split(" ");
        if (dateTime.length > 2) {
            //check for more than 1 date and time entered
            String errorMessage = "Error! You can only enter one date and time, Eg: \"2021-09-12 18:00\",\n"
                    + "one date, Eg: \"2021-09-12\" (This will enter time as 23:59 by default),\n"
                    + "or one time, Eg: \"18:00\" (This will enter today's date by default)";
            throw new InvalidCommandException(errorMessage);
        }
        if (dateTime.length == 1) {
            //user only entered a single date or time
            if (dateTime[0].length() > 5) {
                //user entered date only. Set default time to 23:59.
                this.date = LocalDate.parse(dateTime[0]);
                this.time = LocalTime.MAX;
                this.at = at + " " + this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
            } else {
                //user entered time only. Set date to today's date.
                this.date = LocalDate.now();
                this.time = LocalTime.parse(dateTime[0]);
                this.at = this.date.toString() + " " + at;
            }
        } else {
            //user entered both date and time
            this.date = LocalDate.parse(dateTime[0]);
            this.time = LocalTime.parse(dateTime[1]);
            this.at = at;
        }
    }

    public String getAt() {
        return this.at;
    }

    /**
     * Returns a string that is used to represent events when saved to a file.
     *
     * @return A string representation of an event for saving to files.
     */
    @Override
    public String toFileStringFormat() {
        return "E " + super.toFileStringFormat() + " | " + this.at;
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

    /**
     * Returns if two Event objects are equal based on their description and at.
     *
     * @param obj The other object to compare to.
     * @return A boolean if the two Event objects are equal and false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            boolean isDescriptionSame = event.getDescription().equals(this.getDescription());
            boolean isAtSame = event.at.equals(this.at);
            return isDescriptionSame && isAtSame;
        }
        return false;
    }
}
