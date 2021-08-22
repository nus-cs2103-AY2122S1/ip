import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeHandler {
    String[] formats = {
            "dd-MM-yyyy hh:mm",
            "dd-MM-yyyy HHmm",
            "dd/MM/yyyy hh:mm",
            "dd/MM/yyyy HHmm"
    };
    DateTimeFormatter[] dtfList = new DateTimeFormatter[formats.length];

    public DateTimeHandler() {
        for(int i=0; i<formats.length; i++) {
            dtfList[i] = DateTimeFormatter.ofPattern(formats[i]);
        }
    }
    private boolean tryToParse(DateTimeFormatter dtf, String s) {
        try {
            LocalDateTime.parse(s, dtf);
        } catch(DateTimeParseException e) {
            return false;
        }
        return true;
    }
    public LocalDateTime parseDate(String s) {
        for (DateTimeFormatter dateTimeFormatter : dtfList) {
            if (tryToParse(dateTimeFormatter, s)) {
                return LocalDateTime.parse(s, dateTimeFormatter);
            }
        }
        return null;
    }
    public String getFormatList() {
        StringBuilder sb = new StringBuilder("Valid date formats:");
        for (int i=0; i<formats.length; i++) {
            sb.append("\n     ").append(i+1).append(". ").append(formats[i]);
        }
        return sb.toString();
    }

}
