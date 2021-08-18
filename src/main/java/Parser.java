/**
 * Class for parsing input of user for Duke.
 * @author Liew Jian Hong
 */

public class Parser {

    /**
     * Returns the result of parsing the user input in an array of string where index 0 is command, index 1 is
     * task description and index 2 is by/at date and time.
     * @param item User's input.
     * @return String array of parsed item.
     */
    public String[] parse(String item) {
        String[] result = new String[]{"", "", ""};
        String[] split = item.split(" ");
        int firstWordLength = split[0].length();
        result[0] = split[0];

        String remainder = "";
        int separator = 0;
        if (split.length > 1) {
            remainder = item.substring(firstWordLength + 1);
            for (int i = 0; i < remainder.length(); i++) {
                if (Character.toString(remainder.charAt(i)).equals("/")) {
                    separator = i;
                }
            }
        }
        if (separator == 0) {
            result[1] = remainder;
        } else {
            result[1] = remainder.substring(0, separator);
            result[2] = remainder.substring(separator + 4);
        }
        return result;
    }
}
