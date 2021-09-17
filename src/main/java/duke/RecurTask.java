package duke;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;


public class RecurTask extends Task{

    protected TemporalAdjuster adjuster;
    protected LocalDate nextDate;


    public RecurTask(String name, TemporalAdjuster adjuster, Boolean isDone) {
        super(name, 'R', isDone);
        this.adjuster = adjuster;
    }

    /**
     * Returns a string representation of an Recurring Task.
     * The type of task and whether the task is done are shown.
     * Time of the event will be shown in the format "MMM d yyyy HH:mm".
     * @return String representation of a Recurring Task.
     */
    @Override
    public String toString() {
        nextDate = LocalDate.now().with(adjuster);
        return "[R]" + super.toString() + " (at: " + nextDate.toString() + ")";
    }

    /**
     * Parses the string passed into Duke. Returns a Recurring Task if valid,
     * else throws an Exception.
     *
     * @param str the description of the recurring task to be created
     * @return a Recurring Task that occurs at a specified time
     * @throws TaskException
     */
    public static Task parseCommand(String str) throws TaskException {
        String[] detail = str.split(" /every ", 2);
        if (detail.length == 1) {
            throw new TaskException("When is the recurring task?");
        }
        DayOfWeek day = getDay(detail[1]);
        TemporalAdjuster adj = TemporalAdjusters.nextOrSame(day);
        RecurTask newR = new RecurTask(detail[0], adj, false);
        return newR;
    }

    private static DayOfWeek getDay(String date) throws TaskException {
        String dateToUpperCase = date.toUpperCase();
        switch (dateToUpperCase) {
        case "MONDAY":
            return DayOfWeek.MONDAY;

        case "TUESDAY":
            return DayOfWeek.TUESDAY;

        case "WEDNESDAY":
            return DayOfWeek.WEDNESDAY;

        case "THURSDAY":
            return DayOfWeek.THURSDAY;

        case "FRIDAY":
            return DayOfWeek.FRIDAY;

        case "SATURDAY":
            return DayOfWeek.SATURDAY;

        case "SUNDAY":
            return DayOfWeek.SUNDAY;

        default:
            throw new TaskException("Invalid Date");
    }
    }

    /**
     * Returns a string representation of a Recurring task is saved in the database.
     *
     * @return string representation of saved Recurring task
     */
    @Override
    public String toSavedAs() {
        nextDate = LocalDate.now().with(adjuster);
        return (super.toSavedAs() + '|' + this.nextDate);
    }
}
