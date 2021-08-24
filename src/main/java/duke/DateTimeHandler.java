package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeHandler {
    String[] formats = {
            "dd-MM-yyyy hh:mm a",
            "dd-MM-yyyy HHmm",
            "dd/MM/yyyy hh:mm a",
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
    public String[] getFormatList() {
        String[] res = new String[formats.length+1];
        res[0] = "Valid date formats:";
        for (int i=1; i<=formats.length; i++) {
            res[i] = formats[i];
        }
        return res;
    }

    public String invalidFormat() {
        return "Please enter a valid date-time format. Type formats to see valid formats";
    }

}
