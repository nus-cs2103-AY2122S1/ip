import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.lang.Exception;

public class Deadline extends Task {

    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = parseStringToDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    public String parseStringToDate(String dateTime){
        DateTimeFormatter sourceFormat;
        DateTimeFormatter targetFormat;
        String convertedTime;
        try {
            if (dateTime.split("\\s+").length == 2) {
                sourceFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm");
                targetFormat = DateTimeFormatter.ofPattern("MMM dd uuuu hh:mm a", Locale.ENGLISH);
                convertedTime = LocalDateTime.parse(dateTime, sourceFormat)
                        .format(targetFormat);
            } else {
                sourceFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd");
                targetFormat = DateTimeFormatter.ofPattern("MMM dd uuuu", Locale.ENGLISH);
                convertedTime = LocalDate.parse(dateTime, sourceFormat)
                        .format(targetFormat);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return dateTime;
        }
        return convertedTime;

    }
}