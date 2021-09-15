package duke.shared;

/**
 * Helper class with various string manipulation methods.
 */
public class StringHelpers {
    //@@author sunjc826-reused
    //Reused from https://stackoverflow.com/questions/2255500

    /**
     * Returns a given string repeated for the specified number of times.
     *
     * @param count Number of times a string is to be repeated.
     * @param with  String to be repeated.
     * @return Repeated string.
     */
    public static String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }
    //@@author

    /**
     * Returns space characters repeated for the specified number of times.
     *
     * @param count Number of times a string is to be repeated.
     * @return Repeated string.
     */
    public static String repeat(int count) {
        return repeat(count, " ");
    }

    /**
     * Returns a given string wrapped around in front and behind by another given
     * string
     *
     * @param inner   String to be wrapped.
     * @param wrapper Wrapper string.
     * @return Wrapped string.
     */
    public static String wrap(String inner, String wrapper) {
        return wrapper + inner + wrapper;
    }

    /**
     * Returns a given string enclosed in brackets.
     *
     * @param inner String to be wrapped.
     * @return Wrapped string.
     */
    public static String bracketWrap(String inner) {
        return String.format("[%s]", inner);
    }

    //@@author sunjc826-reused
    //Reused from https://stackoverflow.com/questions/15888934

    /**
     * Returns a given string indented by tabs.
     *
     * @param s String to be indented.
     * @return Indented string.
     */
    public static String indent(String s) {
        return s.replaceAll("(?m)^", "\t");
    }
    //@@author
}
