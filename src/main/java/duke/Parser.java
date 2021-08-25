package duke;

/**
 * Class for parsing input of user for duke.Duke.
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
        String[] result = new String[]{"", "", "", "false"};
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
                    break;
                }
            }
        }
        if (separator == 0) {
            result[1] = remainder;
        } else {
            result[1] = remainder.substring(0, separator - 1);
            result[2] = remainder.substring(separator + 4);
            System.out.println(result[2]);
        }
        return result;
    }

    public String[] parseFromFile(String input) {
        String[] result = new String[]{"", "", "", ""};
        String[] split = input.split("--");
        result[0] = split[0];
        result[1] = split[2];
        result[2] = split[3];
        result[3] = split[1];
        return result;
    }

    public static void main(String[] args) {
        Parser p = new Parser();
        String[] res = p.parse("find book");
        System.out.println(res[0]);
        System.out.println(res[1]);
        System.out.println(res[2]);
        System.out.println(res[3]);
    }
}
