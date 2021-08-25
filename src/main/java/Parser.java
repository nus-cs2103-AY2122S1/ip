
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static String[] splitDescriptionAndTime(String string) {
        return string.split("/");
    }

    public static String getDescription(String[] description_and_time) {
        return description_and_time[0].split(" ", 2)[1];
    }

    public static String getTime (String[] description_and_time) {
        LocalDate localDate = LocalDate.parse(description_and_time[1].split(" ", 2)[1]);
        String time = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return time;
    }
}
