package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exceptions.AuguryException;
import duke.exceptions.InvalidTaskCreationException;

/**
 * The {@code StringCleaner} class is a utility class that operates on {@code Strings}.
 */
public class StringCleaner {

    /**
     * Returns an {@code ArrayList<Integer>} from a {@code String}.
     *
     * @param commaSeparatedString User provided String.
     * @return An {@code ArrayList<Integer>}.
     */
    public static ArrayList<Integer> toArrayListInteger(String commaSeparatedString) {
        String[] strings = commaSeparatedString.split(",");

        // clean input
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].trim();
        }

        // convert String[] to ArrayList<Integer>
        ArrayList<Integer> integers = new ArrayList<>();
        for (String s : strings) {
            integers.add(Integer.parseInt(s));
        }
        return integers;
    }

    /**
     * Returns an {@code LocalDateTime} from a {@code String}.
     *
     * @param dateInput User provided String (should be lowercase).
     * @return A {@code LocalDateTime} object.
     * @throws InvalidTaskCreationException If input String does not match format.
     */
    public static LocalDateTime toLocalDateTime(String dateInput) throws AuguryException {
        assert dateInput.equals(dateInput.toLowerCase());
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateInput, formatter);
        } catch (Exception e) {
            throw new InvalidTaskCreationException("Please use the YYYY-MM-DD HHMM format to specify time!");
        }
    }
}
