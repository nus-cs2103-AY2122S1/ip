package duke.task;

import java.time.format.DateTimeParseException;
import java.lang.NumberFormatException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /** The date the task is due, in LocalDate format. */
    private LocalDate due;
    /** Whether the task has a date it is due in a recognisable format. */
    private boolean hasDate = true;
    /** The time the task is due, in Integer format. */
    private int timeInt;
    /** The time the task is due, in String format. */
    private String timeStr;
    /**  Whether the task has a time it is due in a recognisable, 24-hour format. */
    private boolean hasTime = true;
    /** The information on when the task is due. */
    private String dueStr;

    /**
     * Constructs a new Deadline task.
     * Takes in the name of the Deadline and information on when it's due. The information is checked to see if it
     * contains a date in a recognisable format. If it does, the information is also checked if it contains a time
     * in a recognisable, 24-hour format. It then stores those accordingly. The rest of the string is stored as is.
     *
     * @param name The name of the new Deadline task.
     * @param dueStr The information on when the Deadline task is due.
     */
    public Deadline(String name, String dueStr) {
        super(name);
        this.dueStr = dueStr;

        String[] dateTimeArr = dueStr.split(" ");
        try {
            due = LocalDate.parse(dateTimeArr[0]);
        }
        catch (DateTimeParseException e) {
            hasDate = false;
            hasTime = false;
        }
        if (hasDate) {
            if (dateTimeArr.length >= 2) {
                int time = -1;
                try {
                    time = Integer.parseInt(dateTimeArr[1]);
                }
                catch (NumberFormatException e) {
                    hasTime = false;
                }
                if (hasTime && 0 <= time && time <= 2359) { // 24 hr format
                    timeStr = dateTimeArr[1];
                } else {
                    hasTime = false;
                }
            } else {
                hasTime = false;
            }
        }

        int startIndex = hasTime ? 2 : hasDate ? 1 : 0;
        this.dueStr = "";
        for (int i = startIndex; i < dateTimeArr.length; i++) {
            if (i != 0) this.dueStr += " ";
            this.dueStr += dateTimeArr[i];
        }
        if (hasTime) {
            int hours = Integer.parseInt(timeStr.substring(0, 2));
            int mins = Integer.parseInt(timeStr.substring(2));
            String mornAftStr = "";
            if (hours > 12) {
                hours -= 12;
                mornAftStr = "pm";
            } else {
                if (hours == 0) {
                    hours = 12;
                }
                mornAftStr = "am";
            }
            String minPrependStr = "";
            if (mins < 10) {
                minPrependStr = "0";
            }
            timeStr = hours + ":" + minPrependStr + mins + mornAftStr;
            this.dueStr = " " + timeStr + this.dueStr;
        }
        if (hasDate) {
            this.dueStr = due.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + this.dueStr;
        }
    }

    /**
     * Converts the Deadline task to the format used for saving in the storage file on the user's computer.
     *
     * @return The save format of the Deadline task.
     */
    public String toSaveFormat() {
        return "D|" + super.toSaveFormat() + "|" + dueStr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueStr + ")";
    }
}
