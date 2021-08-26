package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.lang.NumberFormatException;

public class Deadline extends Task {
    private LocalDate due;
    private boolean hasDate = true;
    private int timeInt;
    private String timeStr;
    private boolean hasTime = true;
    private String dueStr;

    public Deadline(String name, String dueStr) {
        super(name);
        this.dueStr = dueStr;

        // Handle date and time representation, if any
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
                if (hasTime && 0 <= time && time <= 2359) {
                    timeStr = dateTimeArr[1];
                } else {
                    hasTime = false;
                }
            } else {
                hasTime = false;
            }
        }

        // Handle representation of information based on whether there is a recognisable date and time
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

    public String convertToSaveFormat() {
        return "D|" + super.convertToSaveFormat() + "|" + dueStr;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueStr + ")";
    }
}
