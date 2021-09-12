package dino.util;

/**
 * Deals with making sense of the user command
 */
public class Parser {

    public enum CMDTYPE {TODO, DEADLINE, EVENT, DONE, DELETE, LIST, FIND, EDIT, BYE, INVALID}

    /**
     * Interprets the user input command and parses it into one of the messages in the
     * enum class CMDTYPE
     * If the command doesn't match any message, then it falls into "INVALID"
     *
     * @param input the input command entered by the user
     * @return one of the messages in the enum class CMDTYPE
     */
    public static CMDTYPE parse(String input) {
        try {
            return CMDTYPE.valueOf(getFirstWord(input).toUpperCase());
        } catch (IllegalArgumentException e) {
            return CMDTYPE.INVALID;
        }
    }

    /**
     * Returns the first word of an input string
     * Returns the string itself if it contains only one word
     *
     * @param s the input string
     * @return the first word of the input string
     */
    public static String getFirstWord(String s) {
        String firstWord;
        if (s.contains(" ")) {
            firstWord = s.substring(0, s.indexOf(" "));
            return firstWord;
        } else {
            return s;
        }
    }
}
