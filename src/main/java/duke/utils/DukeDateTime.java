package duke.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DukeDateTime implements Comparable<DukeDateTime> {
    private LocalDateTime dateTime = null;
    private LocalDate date = null;
    private LocalTime time = null;
    /**
     * Instantiates an empty DukeDateTime.
     */
    public DukeDateTime() {
    }
    /**
     * Instantiates a new DukeDateTime based on a LocalDateTime.
     *
     * @param dateTime
     */
    public DukeDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns a DukeDateTime as parsed from arg.
     *
     * @param arg Formatted date/time string.
     * @return DukeDateTime as parsed from arg.
     * @throws DukeException
     */
    public static DukeDateTime parse(String arg) throws DukeException {
        DukeDateTime out = new DukeDateTime();
        String[] args = arg.split(" ");
        if (args.length > 2) {
            throw new DukeException(String.format("Incorrect date and time formatting:" + "\n\t\t \"%s\"" + "\n\t " + "Enter `help` to check the correct formatting.", arg));
        }
        if (args.length == 2) {
            out.dateTime = LocalDateTime.parse(arg, Format.DT_FULL.format);
        } else {
            if (args[0].matches(Format.DATE_LONG.regex)) {
                out.date = LocalDate.parse(args[0]);
            } else if (args[0].matches(Format.TIME.regex)) {
                out.time = LocalTime.parse(args[0]);
            }
        }
        return out;
    }

    public static void main(String[] args) {
        System.out.println("Driver testing duke.utils.DukeDateTime");
        String date = "2021-08-22";
        String startTime = "04:20";
        String endTime = "06:09";
        String desc = "memes";
        String eventArgs = date + " " + startTime + " ~ " + endTime;
        String deadlineArgs = date + " " + startTime;

        try {
            System.out.println(DukeDateTime.parse(startTime).format());
            System.out.println(DukeDateTime.parse(eventArgs.split(" ")[0]).format());
            System.out.println(DukeDateTime.parse(eventArgs.split(" ")[1]).format());
            System.out.println(DukeDateTime.parse(eventArgs).format());
        } catch (DukeException e) {
            System.err.println(e);
        }
    }

    /**
     * Default formatting of this DukeDateTime.
     *
     * @return String formatting of this DukeDateTime.
     */
    public String format() {
        Format f1 = Format.PRINT_DATE_LONG, f2 = Format.PRINT_TIME;
        return format(f1, f2);
    }

    /**
     * Formatting of this DukeDateTime according to f.
     *
     * @param f Format desired.
     * @return String formatting of this DukeDateTime.
     */
    public String format(Format f) {
        if (dateTime != null) {
            return dateTime.format(f.format);
        }
        if (date != null && f.lvl == Level.DATE) {
            return date.format(f.format);
        }
        if (time != null && f.lvl == Level.TIME) {
            return time.format(f.format);
        }
        return "";
    }

    /**
     * Formatting of this DukeDateTime according to f1, f2.
     * Separated by a single blank space.
     *
     * @param f1 Format desired.
     * @param f2 Format desired.
     * @return String formatting of this DukeDateTime.
     */
    public String format(Format f1, Format f2) {
        String s1 = format(f1);
        String s2 = format(f2);
        if (s1.equals("")) {
            return s2;
        }
        if (s2.equals("")) {
            return s1;
        }
        return s1 + " " + s2;
    }

    public int compareTo(DukeDateTime that) {
        if (this.dateTime != null && that.dateTime != null) {
            return this.dateTime.compareTo(that.dateTime);
        }
        if (this.time != null && that.time != null) {
            return this.time.compareTo(that.time);
        }
        if (this.date != null && that.date != null) {
            return this.date.compareTo(that.date);
        }
        int thisNum = (dateTime != null ? 0 : date != null ? 1 : time != null ? 2 : 3);
        int thatNum = (that.dateTime != null ? 0 : that.date != null ? 1 : that.time != null ? 2 : 3);
        return thisNum - thatNum;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DukeDateTime) {
            DukeDateTime that = (DukeDateTime) obj;
            return this.compareTo(that) == 0;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (dateTime != null) {
            return dateTime.toString();
        }
        if (date != null) {
            return date.toString();
        }
        if (time != null) {
            return time.toString();
        }
        return "";
    }

    private enum Level {TIME, DATE, DATETIME}

    public enum Format {
        DT_FULL("yyyy-MM-dd HH:mm", Level.DATETIME), // DT_SHORT("MM-dd HH:mm", Level.DATETIME),
        TIME("HH:mm", Level.TIME), // DATE_SHORT("MM-dd", Level.DATE),
        DATE_LONG("yyyy-MM-dd", Level.DATE),

        PRINT_TIME("HH:mm", Level.TIME), // PRINT_DATE_SHORT("dd MMM", Level.DATE),
        PRINT_DATE_LONG("dd MMM yyyy", Level.DATE);

        protected final DateTimeFormatter format;
        protected final String pattern;
        protected String regex;
        protected Level lvl;

        Format(String pattern, Level lvl) {
            this.pattern = pattern;
            this.regex = pattern;
            this.lvl = lvl;
            String[] rep = {"d", "y", "M", "m", "H"};
            for (String c : rep) {
                this.regex = regex.replace(c, "\\d");
            }
            this.format = DateTimeFormatter.ofPattern(pattern);
        }
    }
}
