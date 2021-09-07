package duke.task;


import java.time.LocalDateTime;

import duke.DukeException;
import duke.Parser;

/**
 * A task with a done-by date, or due date.
 *
 * @author Aiken Wong
 */
public class Deadline extends Task {

    protected LocalDateTime date;
    protected String taskType = "[D]";
    protected boolean isDateOnly = false;

    /**
     * Constructor for a Deadline.
     *
     * @param description The description of the deadline.
     * @param date The due-date of the deadline.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructor for a Deadline.
     *
     * @param description The description of the deadline.
     * @param date The due-date of the deadline.
     * @param isDateOnly Represents whether only date is given (without time).
     */
    public Deadline(String description, LocalDateTime date, boolean isDateOnly) {
        super(description);
        this.date = date;
        this.isDateOnly = isDateOnly;
    }

    /**
     * Constructor for a Deadline.
     *
     * @param description The description of the deadline.
     * @param date The due-date of the deadline.
     * @param isDone Represents whether the task has been completed.
     * @param isDateOnly Represents whether only date is given (without time).
     */
    public Deadline(String description, LocalDateTime date, boolean isDone, boolean isDateOnly) {
        super(description);
        this.date = date;
        this.isDone = isDone;
        this.isDateOnly = isDateOnly;
    }

    /**
     * Factory constructor for Deadline. Parses String input to obtain date and/or time for due date.
     *
     * @param description The description of the deadline.
     * @param input The input from the user representing date and/or time.
     * @return Deadline with the given inputs.
     * @throws DukeException
     */
    public static Deadline of(String description, String input) throws DukeException {

        String exceptionMessage = "Wrong format Sir/Mdm. Dates and times must be given as only a date: "
            + "DATE\n"
            + "or as date and time: DATE TIME\n"
            + "Accepted formats for DATE: YYYY-MM-DD, DD/MM/YYYY\n"
            + "Accepted formats for TIME (24H format): TT:TT, TTTT\n"
            + "Examples for DATE TIME: 13/2/2019 1800, 13/2/2019 18:00, 2019-02-13 1800,\n"
            + "2019-02-13 18:00\n"
            + "Examples for DATE: 13/2/2019, 2019-02-13";

        String[] dateAndOrTime = input.split(" ");
        LocalDateTime dateTime;
        boolean isDateOnly;

        if (dateAndOrTime.length == 1) {
            dateTime = Parser.parseDateAndTime(dateAndOrTime[0], "00:00");
            isDateOnly = true;
        } else if (dateAndOrTime.length == 2) {
            dateTime = Parser.parseDateAndTime(dateAndOrTime[0], dateAndOrTime[1]);
            isDateOnly = false;
        } else {
            throw new DukeException(exceptionMessage);
        }

        return new Deadline(description, dateTime, isDateOnly);
    }

    /**
     * Getter for isDateOnly.
     *
     * @return Gives isDateOnly
     */
    public boolean getIsDateOnly() {
        return isDateOnly ? true : false;
    }

    /**
     * Getter for the current due date.
     *
     * @return Current due date.
     */
    public LocalDateTime getDate() {
        return LocalDateTime.parse(date.toString());
    }

    @Override
    protected LocalDateTime getComparisonTime() {
        return this.getDate();
    }



    @Override
    public String toString() {
        return taskType + super.toString() + " (by: " + Parser.getDateTimeString(this.date, isDateOnly) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Deadline) {
            Deadline deadline = (Deadline) o;

            return description.equals(deadline.description) && isDateOnly == deadline.isDateOnly
                && isDone == deadline.isDone && date.equals(deadline.date);
        }
        return false;
    }


}

