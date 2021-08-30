import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeHandler {
    public static String parse(String timeString) {
        String[] s = timeString.trim().split("\\s+");
        String formattedDate = "";
        String formattedTime = "";
        for (String s2 : s) {
            if (s2.chars().filter(c->c=='/').count() == 2) {
                String[] date = s2.split("/");
                try {
                    LocalDate localDate = LocalDate.of(Integer.parseInt(date[2]),
                            Integer.parseInt(date[1]), Integer.parseInt(date[0]));
                    formattedDate = localDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            } else if (s2.length() == 4 && s2.matches("\\d{4}")) {
                int hour = Integer.parseInt(s2.substring(0,2));
                String minute = s2.substring(2);
                String meridiem = (hour > 11) ? "PM" : "AM";
                hour = (hour - 1) % 12 + 1;
                formattedTime = " " + String.valueOf(hour) + ":" + minute + " " + meridiem;
            }
        }
        return formattedDate + formattedTime;
    }
}
