package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
// import java.lang.NumberFormatException;

public class Deadline extends Task {
    /** The date the task is due, in LocalDate format. */
    private LocalDate due;
    /** Whether the task has a date it is due in a recognisable format. */
    private boolean hasDate = true;
    /** The time the task is due, in String format. */
    private String timeStr;
    /**  Whether the task has a time it is due in a recognisable, 24-hour format. */
    private boolean hasTime = true;
    /** The information on when the task is due. */
    private String dueStr;

    private final int EARLIEST_TIME = 0;
    private final int LATEST_TIME = 2359;

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
        checkForDateTime();
        getDueDateTime();
    }

    private void checkForDateTime() {
        String[] dateTimeArr = dueStr.split(" ");
        try {
            due = LocalDate.parse(dateTimeArr[0]);
        } catch (DateTimeParseException e) {
            hasDate = false;
            hasTime = false;
        }

        if (!hasDate || !(dateTimeArr.length >= 2)) {
            hasTime = false;
            return;
        }

        int time = -1;
        try {
            time = Integer.parseInt(dateTimeArr[1]);
        } catch (NumberFormatException e) {
            hasTime = false;
        }
        if (hasTime && EARLIEST_TIME <= time && time <= LATEST_TIME) {
            timeStr = dateTimeArr[1];
        } else {
            hasTime = false;
        }
    }

    private void getDueDateTime() {
        int startIndex = getStartIndex();
        addNonDateTimeToDueStr(startIndex);
        if (hasTime) {
            addTimeToDueStr();
        }
        if (hasDate) {
            addDateToDueStr();
        }
    }

    private int getStartIndex() {
        final int TIME_START_INDEX = 2;
        final int DATE_START_INDEX = 1;
        final int NO_DATETIME_START_INDEX = 0;
        return hasTime ? TIME_START_INDEX
                : hasDate ? DATE_START_INDEX
                : NO_DATETIME_START_INDEX;
    }

    private void addNonDateTimeToDueStr(int startIndex) {
        String[] dateTimeArr = dueStr.split(" ");
        dueStr = "";
        for (int i = startIndex; i < dateTimeArr.length; i++) {
            if (i != 0) {
                dueStr += " ";
            }
            dueStr += dateTimeArr[i];
        }
    }

    private void addTimeToDueStr() {
        int hours = Integer.parseInt(timeStr.substring(0, 2));
        int minutes = Integer.parseInt(timeStr.substring(2));
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
        if (minutes < 10) {
            minPrependStr = "0";
        }
        timeStr = hours + ":" + minPrependStr + minutes + mornAftStr;
        dueStr = " " + timeStr + dueStr;
    }

    private void addDateToDueStr() {
        dueStr = due.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + dueStr;
    }

    /**
     * Converts the Deadline task to the format used for saving in the storage file on the user's computer.
     *
     * @return The save format of the Deadline task.
     */
    public String convertToSaveFormat() {
        return "D|" + super.convertToSaveFormat() + "|" + dueStr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueStr + ")";
    }
}
