package exception;

import java.util.Arrays;

/**
 * Encapsulates an exception when an array of strings has an incorrect number of parts.
 */
public class InvalidNumOfStringPartsException extends DukeException {
    /**
     * Encapsulates an exception when an array of strings has an incorrect number of parts.
     *
     * @param expectedNumOfParts Expected number of parts.
     * @param parts Array with string parts.
     */
    public InvalidNumOfStringPartsException(int expectedNumOfParts, String[] parts) {
        super(String.format(
            "%d parts were expected but the actual parts were %s",
            expectedNumOfParts,
            Arrays.toString(parts)
        ));
    }
}
