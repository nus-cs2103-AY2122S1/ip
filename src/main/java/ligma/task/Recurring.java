package ligma.task;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.InputMismatchException;

/**
 * This class represents a Recurring Task.
 */
public class Recurring extends Task {
    private final static String FORMAT_ERR_MSG = "Time should be in {d/w/m/y} {specific t/d/dd/m} format.\n"
            + "eg. /every d 2100\n/every w monday\n /every m 15\n /every y july";
    private enum Period {
        DAY, WEEK, MONTH, YEAR
    }
    private Period recurPeriod;
    private int recurTime;

    private Recurring(String details, Period p, int recurTime, String meta) {
        super(details, meta);
        this.recurPeriod = p;
        this.recurTime = recurTime;
    }

    private Recurring(String details, Period p, int recurTime,
                      boolean isDone, String meta) {
        super(details, isDone, meta);
        this.recurPeriod = p;
        this.recurTime = recurTime;
    }

    /**
     * Factory method that creates an Recurring object.
     *
     * @param desc                      the description of the Recurring
     * @return                          Recurring created
     * @throws InputMismatchException   if format of Recurring description is wrong
     * @throws DateTimeParseException   if format of time is wrong
     */
    public static Recurring createRecurring(String desc)
            throws InputMismatchException {
        if (!desc.contains("/every")) {
            throw new InputMismatchException("Time must be stipulated for Recurring tasks"
                    + " using '/every'.");
        }

        String[] deetsPeriodTime = splitInfo(desc);
        String details = deetsPeriodTime[0];
        String period = deetsPeriodTime[1];
        String time = deetsPeriodTime[2].toUpperCase();

        checkTimeLegitimacy(period, time);

        return createRecurring(details, period, time, false, desc);
    }

    /**
     * Factory method that creates an Recurring object.
     *
     * @param desc                      the description of the Recurring
     * @param isDone                    whether task has been completed
     * @return                          Recurring created
     * @throws InputMismatchException   if format of Recurring description is wrong
     * @throws DateTimeParseException   if format of time is wrong
     */
    public static Recurring createRecurring(String desc, boolean isDone) throws InputMismatchException {
        assert(desc.contains("/every")) : "Error with file contents";

        String[] deetsPeriodTime = splitInfo(desc);
        String details = deetsPeriodTime[0];
        String period = deetsPeriodTime[1];
        String time = deetsPeriodTime[2].toUpperCase();

        checkTimeLegitimacy(period, time);

        return createRecurring(details, period, time, isDone, desc);
    }

    private static Recurring createRecurring(String details, String p, String time,
                                             boolean isDone, String meta) {
        switch (p) {
        case "d":
            return new Recurring(details, Period.DAY,
                    Integer.parseInt(time), isDone, meta);
        case "w":
            return new Recurring(details, Period.WEEK,
                    DayOfWeek.valueOf(time).getValue(), isDone, meta);
        case "m":
            return new Recurring(details, Period.MONTH,
                    Integer.parseInt(time), isDone, meta);
        case "y":
            return new Recurring(details, Period.YEAR,
                    Month.valueOf(time).getValue(), isDone, meta);
        default:
            throw new InputMismatchException("Time must be stipulated for Recurring tasks"
                    + "using '/every'.");
        }
    }

    private static String intTimeToStr(int t) {
        int hours = t / 100;
        int min = t % 100;
        return String.format("%02d:%02d:00", hours, min);
    }

    private static void checkTimeLegitimacy(String p, String time) {
        try {
            switch (p) {
            case "d":
                LocalTime.parse(intTimeToStr(Integer.parseInt(time)),
                        DateTimeFormatter.ISO_TIME);
                break;
            case "w":
                DayOfWeek.valueOf(time);
                break;
            case "m":
                int date = Integer.valueOf(time);
                if (date > 31 || date < 1) {
                    throw new IllegalArgumentException();
                }
                break;
            case "y":
                Month.valueOf(time);
                break;
            }
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw new InputMismatchException(FORMAT_ERR_MSG);
        }
    }

    private static String[] splitInfo(String desc) {
        String[] deetsPeriodTime = new String[3];
        String[] deetsAndOthers = desc.split("/every ");
        String[] periodAndTime = deetsAndOthers[1].trim().split(" ");

        deetsPeriodTime[0] = deetsAndOthers[0].trim();
        try {
            deetsPeriodTime[1] = periodAndTime[0].trim();
            deetsPeriodTime[2] = periodAndTime[1].trim();
            return deetsPeriodTime;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InputMismatchException(FORMAT_ERR_MSG);
        }
    }


    private String getNextDailyOcc() {
        LocalTime time = LocalTime.parse(intTimeToStr(recurTime), DateTimeFormatter.ISO_TIME);
        String day = LocalDateTime.now().toLocalTime().isBefore(time) ? "today" : "tomorrow";
        return String.format("%s, %04d", day, recurTime);
    }

    private String getNextWeeklyOcc() {
        LocalDateTime next = LocalDateTime.now().with(DayOfWeek.of(recurTime));
        next = next.isBefore(LocalDateTime.now()) ? next.plusDays(7) : next;
        return next.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String getNextMonthlyOcc() throws InputMismatchException {
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        int day = LocalDateTime.now().getDayOfMonth();
        if (recurTime <= day) {
            if (month == 12) {
                year++;
                month = 1;
            } else {
                month++;
            }
        }

        boolean isValidDay =  recurTime > 0 && recurTime <= YearMonth.of(year, month).lengthOfMonth();
        if (isValidDay) {
            return LocalDateTime.of(year, month, recurTime, 0, 0)
                    .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            String thisOrNext = recurTime <= day ? "next" : "this";
            return String.format("%s month does not contain day %d", thisOrNext, recurTime);
        }
    }

    private String getNextAnnualOcc() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next = now.with(Month.of(recurTime));
        next = next.isBefore(now) ? next.plusYears(1) : next;
        return next.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getFullMeta() {
        return "R" + super.getFullMeta();
    }

    @Override
    public String toString() {
        String nextStr = "";

        switch (this.recurPeriod) {
        case DAY:
            nextStr = getNextDailyOcc();
            break;
        case WEEK:
            nextStr = getNextWeeklyOcc();
            break;
        case MONTH:
            nextStr = getNextMonthlyOcc();
            break;
        case YEAR:
            nextStr = getNextAnnualOcc();
            break;
        default:
        }

        return String.format("[R]%s (next: %s)",
                super.toString(),
                nextStr);
    }
}
