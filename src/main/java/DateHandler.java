import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHandler {
    public DateHandler() {
    }
    public static LocalDate formatDate(String str) {
        return LocalDate.parse(str);
    }
    
    public static String convertDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMM YYYY"));
    }
}
