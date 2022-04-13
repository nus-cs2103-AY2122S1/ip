package duke.task;

import duke.utility.Utility;

import java.time.LocalDateTime;

/**
 * Deadline class.
 * Used to represent a deadline task.
 * @author KelvinSoo
 * @version Level-8
 */
public class Deadline extends Task {
    private final String dateLine;
    private final int HALF_DAY_HOURS = 12;

    /**
     * A constructor to create a new deadline task.
     */
    public Deadline(String description, String dateLine) {
        super(description);
        LocalDateTime ldt = Utility.stringToDate(dateLine);
        if (ldt == null) {
            this.dateLine = dateLine;
        } else {
            boolean isDayTime = ldt.getHour() < HALF_DAY_HOURS;
            this.dateLine = String.format("%s of %s %s, %s%s",
                    ldt.getDayOfMonth(),
                    ldt.getMonth().toString(),
                    ldt.getYear(),
                    isDayTime ? ldt.getHour() : ldt.getHour() - HALF_DAY_HOURS,
                    isDayTime ? "am" : "pm");
        }
    }

    /**
     * A constructor to create a new deadline task.
     */
    public Deadline(String description, String dateLine, Boolean isDone) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
        this.dateLine = dateLine;
    }

    /**
     * A method to get the state of the task.
     * @return The status icon
     */
    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    /**
     * A method to get data about the task in a savable format.
     * @return The savable data
     */
    @Override
    public String getMetaData() {
        return String.format("D|%s|%s", super.getMetaData(), dateLine);
    }

    /**
     * A method to get the description of the task.
     * @return The description of the task including date
     */
    @Override
    public String getDescription() {
        return String.format("%s (by: %s)", super.getDescription(), dateLine);
    }

    /**
     * A method to get all task details.
     * @return All the task details
     */
    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), getDescription());
    }
}
