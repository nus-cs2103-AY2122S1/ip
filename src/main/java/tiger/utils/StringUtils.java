package tiger.utils;

public class StringUtils {

    /**
     * Removes trailing back spaces on input string.
     *
     * @param input The input string.
     * @return A new string with the trailing spaces cut off.
     */

    public String removeLastSpaces(String input) {
        int last = input.length() - 1;
        while (input.charAt(last) == ' ') {
            last -= 1;
        }
        return input.substring(0, last + 1);
    }

    /**
     * Removes trailing front spaces on input string.
     *
     * @param input The input string.
     * @return A new string with the trailing spaces cut off.
     */

    public String removeFrontSpaces(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        String cut = this.removeLastSpaces(reversed);
        String reversedCut = new StringBuilder(cut).reverse().toString();
        return reversedCut;
    }

    /**
     * Removes trailing back and front spaces on input string.
     *
     * @param input The input string.
     * @return A new string with the trailing spaces cut off.
     */

    public String removeBackAndFrontSpaces(String input) {
        String cutBack = this.removeLastSpaces(input);
        String cutFront = this.removeFrontSpaces(cutBack);
        return cutFront;
    }

    /**
     * Capitalises the first letter.
     *
     * @param input The input string.
     * @return A new string with the first letter capitalised.
     */

    public String capitaliseFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
