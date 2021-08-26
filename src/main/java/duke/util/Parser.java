package duke.util;

/**
 * Parser to take care of parsing input given by the user
 */
public class Parser {
    /**
     * Returns the string following the first word
     *
     * @param firstWord The word you want to exclude
     * @param input The string including the first word
     * @return The remaining string
     */
    public static String getRemainingText(String firstWord, String input) {
        String remainingText = "";
        if (input.length() > firstWord.length() + 1) {
            remainingText = input.substring(firstWord.length() + 1).trim();
        }
        return remainingText;
    }
}
