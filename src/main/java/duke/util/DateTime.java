package duke.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateTime {

    // TODO: multiple regexes for date
    private static final String DATE_REGEX = "(\\d{4}-\\d{2}-\\d{2}).+";
    // Negative lookahead the dash so it doesn't capture the date
    private static final String TIME_REGEX = "(\\d{4})(?!-)(.+)?";

    private LocalDate date;
    private LocalTime time;

    public DateTime(String input) {
        Pattern datePattern = Pattern.compile(DATE_REGEX);
        Matcher dateMatcher = datePattern.matcher(input);
        if (dateMatcher.matches()) {
//            System.out.println(dateMatcher.group(1));
            date = LocalDate.parse(dateMatcher.group(1));
        }
        Pattern timePattern = Pattern.compile(TIME_REGEX);
        Matcher timeMatcher = timePattern.matcher(input);
        if (timeMatcher.matches()) {
//            System.out.println(timeMatcher.group(1));
            time = LocalTime.parse(timeMatcher.group(1), DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    private String dateToString() {
        return date != null ? date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")): null;
    }

    private String timeToString() {
        return time != null ? time.format(DateTimeFormatter.ofPattern("hh.mm a")) : null;
    }

    @Override
    public String toString() {
        // Thanks to https://stackoverflow.com/a/59323744/12499338
        return Stream.of(dateToString(), timeToString())
                        .filter(Objects::nonNull)
                        .collect(Collectors.joining(" "));
    }
}
