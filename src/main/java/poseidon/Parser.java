package poseidon;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Parser {

    // Regex versions of all the possible commands
    private static final String CMD_BYE = "(?i)bye\\s*";
    private static final String CMD_LIST = "(?i)list\\s*";
    private static final String CMD_DONE = "(?i)done\\s+\\d+\\s*";
    private static final String CMD_DELETE = "(?i)delete\\s+\\d+\\s*";
    private static final String CMD_TODO = "(?i)todo.*";
    private static final String CMD_DEADLINE = "(?i)deadline.*";
    private static final String CMD_EVENT = "(?i)event.*";
    private static final String CMD_FIND = "(?i)find.*";
    private static final String CMD_SORT = "(?i)sort\\s*";

    /**
     * Returns a String array that contains the useful and necessary parts of a command to be executed.
     *
     * @param newCommand String version of a command.
     * @return String array.
     */
    public static String[] parse(String newCommand) {
        if (Pattern.compile(CMD_BYE).matcher(newCommand).matches()) {
            return new String[]{"bye"};
        } else if (Pattern.compile(CMD_LIST).matcher(newCommand).matches()) {
            return new String[]{"list"};
        } else if (Pattern.compile(CMD_DONE).matcher(newCommand).matches()) {
            return new String[]{"done", newCommand.substring(4).trim()};
        } else if (Pattern.compile(CMD_DELETE).matcher(newCommand).matches()) {
            return new String[]{"delete", newCommand.substring(6).trim()};
        } else if (Pattern.compile(CMD_TODO).matcher(newCommand).matches()) {
            return parseTodo(newCommand);
        } else if (Pattern.compile(CMD_DEADLINE).matcher(newCommand).matches()) {
            return parseDeadline(newCommand);
        } else if (Pattern.compile(CMD_EVENT).matcher(newCommand).matches()) {
            return parseEvent(newCommand);
        } else if (Pattern.compile(CMD_FIND).matcher(newCommand).matches()) {
            return parseFind(newCommand);
        } else if (Pattern.compile(CMD_SORT).matcher(newCommand).matches()) {
            return new String[]{"sort"};
        } else {
            return new String[]{"fail"};
        }
    }

    /**
     * Returns a LocalDateTime object after parsing a string version of date and time.
     *
     * @param dateTime String version of date and time.
     * @return LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        final String dateFormat = "yyyy MM dd HHmm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime deadlineDT;

        try {
            deadlineDT = LocalDateTime.parse(dateTime, formatter);
            return deadlineDT;
        } catch (DateTimeException ex) {
            throw new PoseidonException(ex.getMessage() + "\n"
                    + "Please try again.");
        }
    }

    /**
     * Returns the integer version of a string number.
     *
     * @param intString String version of a number.
     * @return integer version of the given number.
     */
    public static int parseIndex(String intString) {
        return Integer.parseInt(intString.trim());
    }

    /**
     * Returns true if the given user string input is a valid "bye" command by pattern matching.
     *
     * @param newCommand String user input.
     * @return Boolean validation result.
     */
    public static boolean isParsedBye(String newCommand) {
        return Pattern.compile(CMD_BYE).matcher(newCommand).matches();
    }

    private static String[] parseTodo(String newCommand) {
        final String validTodo = "(?i)todo\\s+";
        String[] strArr = Pattern.compile(validTodo).split(newCommand, 2);

        if (strArr.length == 1 && strArr[0].length() > 4) {
            throw new PoseidonException("There appears to be a typo in your TODO command.\n"
                    + "The command should be of the form:\n"
                    + "  todo 'description'\n"
                    + "Please try again.");
        }

        if (strArr.length == 1 || strArr[1].length() == 0) {
            throw new PoseidonException("The description of a TODO task cannot be empty.\nPlease try again.");
        }

        return new String[]{"add", "todo", strArr[1]};
    }

    private static String[] parseDeadline(String newCommand) {
        final String validDeadline = "(?i)(deadline ).*\\S+.*( /by )\\d{4}\\s\\d{2}\\s\\d{2}\\s\\d{4}";

        if (!Pattern.compile(validDeadline).matcher(newCommand).matches()) {
            throw new PoseidonException("There appears to be a typo in your DEADLINE command.\n"
                    + "The command should be of the form:\n"
                    + "  deadline 'description' /by 'yyyy mm dd hhmm'\n"
                    + "Please try again.");
        }

        String[] strArr = newCommand.substring(8).split(" /by ", 2);
        return new String[]{"add", "deadline", strArr[0].trim(), strArr[1].trim()};
    }

    private static String[] parseEvent(String newCommand) {
        final String validEvent = "(?i)(event ).*\\S+.*( /from )\\d{4}\\s\\d{2}\\s\\d{2}\\s\\d{4}"
                + "( to )\\d{4}\\s\\d{2}\\s\\d{2}\\s\\d{4}";

        if (!Pattern.compile(validEvent).matcher(newCommand).matches()) {
            throw new PoseidonException("There appears to be a typo in your EVENT command.\n"
                    + "The command should be of the form:\n"
                    + "  event 'description' /from 'yyyy mm dd hhmm' to 'yyyy mm dd hhmm'\n"
                    + "Please try again.");
        }

        String[] strArr = newCommand.substring(5).split(" /from ", 2);
        String[] dateTimeArr = strArr[1].split(" to ", 2);
        return new String[]{"add", "event", strArr[0].trim(), dateTimeArr[0], dateTimeArr[1]};
    }

    private static String[] parseFind(String newCommand) {
        final String validFind = "(?i)find\\s+";
        String[] strArr = Pattern.compile(validFind).split(newCommand, 2);

        if (strArr.length == 1 && strArr[0].length() > 4) {
            throw new PoseidonException("There appears to be a typo in your FIND command.\n"
                    + "The command should be of the form:\n"
                    + "  find 'content'\n"
                    + "Please try again.");
        }

        if (strArr.length == 1 || strArr[1].length() == 0) {
            throw new PoseidonException("The contents of a FIND command cannot be empty.\nPlease try again.");
        }

        return new String[]{"find", strArr[1].trim()};
    }
}
