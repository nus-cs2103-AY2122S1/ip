package duke.task;


import duke.DukeException;
import duke.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime date;
    protected String taskType = "[D]";
    protected boolean isDateOnly = false;

    public boolean getIsDateOnly() {
        return isDateOnly ? true : false;
    }

    public LocalDateTime getDate() {
        return LocalDateTime.parse(date.toString());
    }

    private static String errorMessage = "Wrong format Sir/Mdm. Dates and times must be given as only a date: DATE\n"
            + "or as date and time: DATE TIME\n"
            + "Accepted formats for DATE: YYYY-MM-DD, DD/MM/YYYY\n"
            + "Accepted formats for TIME (24H format): TT:TT, TTTT\n"
            + "Examples for DATE TIME: 13/2/2019 1800, 13/2/2019 18:00, 2019-02-13 1800,\n"
            + "2019-02-13 18:00\n"
            + "Examples for DATE: 13/2/2019, 2019-02-13";

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, LocalDateTime date, boolean isDateOnly) {
        super(description);
        this.date = date;
        this.isDateOnly = isDateOnly;
    }

    public Deadline(String description, LocalDateTime date, boolean isDone, boolean isDateOnly) {
        super(description);
        this.date = date;
        this.isDone = isDone;
        this.isDateOnly = isDateOnly;
    }


    public static Deadline of(String description, String input) throws DukeException {

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
            throw new DukeException(errorMessage);
        }

        return new Deadline(description, dateTime, isDateOnly);
    }

    @Override
    public String toString() {
        return taskType + super.toString() + " (by: " + Parser.dateTimeToString(this.date, isDateOnly) + ")";
    }


}

