package duke;

import java.util.Arrays;

/**
 * Class for parsing input of user for duke.Duke.
 * @author Liew Jian Hong
 */

public class Parser {

    private static final int DATE_SPACING = 4;
    /**
     * Returns the result of parsing the user input in an array of string where index 0 is command, index 1 is
     * task description and index 2 is by/at date and time.
     *
     * @param item User's input.
     * @return String array of parsed item.
     */
    public String[] parse(String item) {
        String[] result = new String[]{"", "", "", "false", ""};
        String copy = item;
        String command = "";
        String desc = "";
        String date = "";
        String location = "";

        //extract command, if any
        if (copy.contains(" ")) {
            int commandSeparator = copy.indexOf(" ");
            command = item.substring(0, commandSeparator);
            copy = copy.substring(commandSeparator + 1);
        } else {
            command = copy;
        }

        //extract location, if any
        if (copy.contains(" > ")) {
            String[] splitLocation = copy.split(" > ");
            copy = splitLocation[0];
            location = splitLocation[1];
        }

        //extract date, if any
        if (copy.contains(" /")) {
            String[] splitDate = copy.split(" /");
            desc = splitDate[0];
            int dateSeparator = copy.indexOf("/");
            date = copy.substring(dateSeparator + DATE_SPACING);
        } else {
            desc = copy;
        }

        result[0] = command;
        result[1] = desc;
        result[2] = date;
        result[4] = location;

        return result;
    }


    /**
     * Returns parsed input from file containing stored tasks.
     *
     * @param input String from file.
     * @return Parsed input from file.
     */
    public String[] parseTasksFromFile(String input) {
        String[] result = new String[]{"", "", "", "", ""};
        String[] split = input.split("--");
        result[0] = split[0];
        result[1] = split[2];
        result[2] = split[3];
        result[3] = split[1];
        result[4] = split[4] == "0" ? "" : split[4];
        return result;
    }

    /**
     * Returns parsed input from file containing stored places.
     *
     * @param input String from file.
     * @return Parsed input from file.
     */
    public String[] parsePlacesFromFile(String input) {
        String[] result = new String[] {"", ""};
        String[] split = input.split("--");
        result[0] = split[0] == "0" ? "" : split[0];
        result[1] = split[1] == "0" ? "" : split[1];
        return result;
    }

    /**
     * Testing
     * @param args args.
     */
    @SuppressWarnings("checkstyle:CommentsIndentation")
    public static void main(String[] args) {
        Parser p = new Parser();
        String s = "delete-task 4";
        String[] test = p.parse(s);
        System.out.println(Arrays.toString(test));
    }

}
