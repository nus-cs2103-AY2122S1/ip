import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class TaskTime {
    private static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter TIMEFORMAT = DateTimeFormatter.ofPattern("hhmma");

    public static String convertDateTimeFormat(String inputTime) {
        String[] split = inputTime.split(" ");
        LocalDate date = LocalDate.parse(split[0].trim());
        if (split.length == 1) {
            return date.format(DATEFORMAT);
        } else {
            LocalTime time = LocalTime.parse(split[1].trim());
            return date.format(DATEFORMAT) + " " + time.format(TIMEFORMAT);
        }
    }
}