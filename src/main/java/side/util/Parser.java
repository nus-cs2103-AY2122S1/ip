package side.util;

/**
 * Encapsulates operations for parsing user input and translating it to corresponding commands.
 *
 * @author Lua Yi Da
 */

public class Parser {

    /**
     * Initialises new instance of Parser.
     */
    public Parser() {}

    public enum COMMAND { TODO, DEADLINE, EVENT, LIST, DONE, DELETE, INVALID, FIND, BYE }

    private static String findDatetime(String input, String argument) {
        assert input.length() > 0;

        int argumentIndex = input.lastIndexOf(argument);
        String output = input.substring(argumentIndex + argument.length());

        if (output.replaceAll("\\s", "").length() < 1) {
            return null;
        }

        return output.trim();
    }

    /**
     * Helper method to find corresponding datetime for deadline commands.
     *
     * @param input String representing user input.
     * @return String of datetime.
     */
    public String findDeadlineDatetime(String input) {
        assert input.length() > 0;
        return findDatetime(input, "/by");
    }

    /**
     * Helper method to find corresponding datetime for event commands.
     *
     * @param input String representing user input.
     * @return String of datetime.
     */
    public String[] findEventDatetime(String input) {
        assert input.length() > 0;

        String output = input;

        if (input.contains("/at")) {
            output = findDatetime(input, "/at");
        }
        if (output != null) {
            String[] outputArr = output.trim().split("/to");

            for (int i = 0; i < outputArr.length; i++) {
                outputArr[i] = outputArr[i].trim();
            }
            return outputArr;
        }

        return null;
    }

    /**
     * Helper method to find task descriptions for commands.
     *
     * @param input String representing user input.
     * @return String of description.
     */
    public String findDescription(String input) {
        assert input.length() > 0;

        String cmd = input.split("\\s+", 2)[1];

        if (cmd.contains("/by")) {
            return cmd.split("/by")[0];
        } else if (cmd.contains("/at")) {
            return cmd.split("/at")[0];
        } else {
            return null;
        }
    }

    /**
     * Helper method to parse string and isolate index passed in by user
     *
     * @param s String to be parsed.
     * @return Integer from parsing String s.
     */
    public Integer tryIntParsing(String s) {
        try {
            int parsedInt = Integer.parseInt(s);
            return parsedInt;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Helper to convert parsed command to COMMAND constant.
     *
     * @param command String representing command input.
     * @return COMMAND corresponding to String input.
     */
    public static COMMAND toCommand(String command) {
        switch (command) {
        case "todo":
            return COMMAND.TODO;
        case "deadline":
            return COMMAND.DEADLINE;
        case "event":
            return COMMAND.EVENT;
        case "list":
            return COMMAND.LIST;
        case "done":
            return COMMAND.DONE;
        case "delete":
            return COMMAND.DELETE;
        case "find":
            return COMMAND.FIND;
        case "bye":
            return COMMAND.BYE;
        default:
            return COMMAND.INVALID;
        }
    }
}
