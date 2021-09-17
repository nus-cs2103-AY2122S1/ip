package Wonderland;

public class Parser {
    /**
     * Checks if command is valid keyword.
     *
     * @param input command
     * @return Keyword
     * @throws DukeException if command is invalid keyword.
     */
    public static Keyword parse(String input) throws DukeException {
        switch (input) {
        case "list":
            return Keyword.LIST;
        case "done":
            return Keyword.DONE;
        case "todo":
            return Keyword.TODO;
        case "deadline":
            return Keyword.DEADLINE;
        case "event":
            return Keyword.EVENT;
        case "delete":
            return Keyword.DELETE;
        case "find":
            return Keyword.FIND;
        default:
            throw new DukeException("What?? That's not a valid keyword!");
        }
    }

    /**
     * Checks if command is valid keyword.
     *
     * @param input command
     * @return Keyword
     * @throws DukeException if command is invalid keyword.
     */
    public static Keyword parseForGui(String input) throws DukeException {
        if (input.startsWith("list")) {
            return Keyword.LIST;
        } else if (input.startsWith("find")) {
            return Keyword.FIND;
        } else if (input.startsWith("done")) {
            return Keyword.DONE;
        } else if (input.startsWith("delete")) {
            return Keyword.DELETE;
        } else if (input.startsWith("todo")) {
            return Keyword.TODO;
        } else if (input.startsWith("deadline")) {
            return Keyword.DEADLINE;
        } else if (input.startsWith("event")) {
            return Keyword.EVENT;
        } else {
            throw new DukeException("What?? That's not a valid keyword!");
        }
    }

    private static boolean checkInvalidCommand(String input, Keyword keyword) {
        switch (keyword) {
        case DONE:
            return input.trim().equals("done");
        case FIND:
            return input.trim().equals("find");
        case DELETE:
            return input.trim().equals("delete");
        case TODO:
            return input.trim().equals("todo");
        case DEADLINE:
            return input.trim().equals("deadline") || !input.contains("/by ");
        case EVENT:
            return input.trim().equals("event") || !input.contains("/at ");
        default:
            return true;
        }
    }

    public static String getInfo(String input, Keyword keyword) {
        switch (keyword) {
        case DEADLINE:
            if (checkInvalidCommand(input, keyword)) {
                return " /by  ";
            } else {
                return input.split(keyword.toString())[1];
            }
        case EVENT:
            if (checkInvalidCommand(input, keyword)) {
                return " /at  ";
            } else {
                return input.split(keyword.toString())[1];
            }
        case DONE:
        case DELETE:
            if (checkInvalidCommand(input, keyword)) {
                return "-1";
            } else {
                return input.split(keyword.toString())[1].trim();
            }
        case FIND:
        case TODO:
            if (checkInvalidCommand(input, keyword)) {
                return "";
            } else {
                return input.split(keyword.toString())[1].trim();
            }
        default:
            return "";
        }
    }
}
