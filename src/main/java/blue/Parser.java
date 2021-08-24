package blue;

import java.util.Arrays;

/**
 * Helps in making sense of the user input.
 */
public class Parser {
    static String getCommand(String input) {
        if (input.length() > 0) {
            return input.split(" ")[0];
        } else {
            return "";
        }
    }

    /**
     * Extracts the arguments from the user input.
     *
     * @param input User input.
     * @return Extracted arguments.
     */
    public static String[] getArguments(String input) {
        if (input.length() > 0) {
            String[] split = input.split(" ");
            if (split.length >= 2) {
                return Arrays.copyOfRange(split, 1, split.length);
            }
        }
        return new String[]{};
    }
}
