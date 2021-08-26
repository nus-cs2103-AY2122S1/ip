package duke.util;

import duke.exceptions.AuguryException;
import duke.exceptions.InvalidTaskCreationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The {@code StringCleaner} class is a utility class that operates on {@code Strings}.
 */
public class StringCleaner {
    public static ArrayList<Integer> toArrayListInteger(String commaSeparatedString) {
        String[] s_String = commaSeparatedString.split(",");
        for (int i = 0; i < s_String.length; i++) {
            // trim whitespace on each item
            s_String[i] = s_String[i].trim();
        }
        // convert String[] to ArrayList<Integer>
        ArrayList<Integer> s_Integer = new ArrayList<>();
        for (String ss : s_String) {
            s_Integer.add(Integer.parseInt(ss));
        }
        return s_Integer;
    }

    public static LocalDateTime toLocalDateTime(String input) throws AuguryException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(input, formatter);
        } catch (Exception e) {
            throw new InvalidTaskCreationException("Please use the YYYY-MM-DD HHMM format to specify time!");
        }
    }
}
