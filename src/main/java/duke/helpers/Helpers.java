package duke.helpers;

/**
 * Helpers Class with helper methods which can be shared throughout the application.
 */
public class Helpers {

    /**
     * Utility function to test whether input string is an integer.
     *
     * @param input input String.
     * @return returns true if input string can be parsed as an integer, else false.
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
