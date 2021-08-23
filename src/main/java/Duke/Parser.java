package Duke;

import Duke.Commands.Command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
//    public static Command parse(String command) {
//        return new Command();
//    }
    public static String convert(LocalDate date) {
    return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
}

    /**
     * Convert MMM dd YYY to YYYY-MM-DD
     */
    public static String reverse(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM dd yyyy"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
