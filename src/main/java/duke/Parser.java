package duke;

public class Parser {

    /**
     * Parses the user input string.
     *
     * @param input the input user string
     *
     * @return a string array representing the user's input
     */
    public static String[] parse(String input) {
        String[] str = input.split(" ", 2);
        String arg = str[0];

        switch (arg) {
        case "done":
            return new String[]{ "done", str[1] };
        case "delete":
            return new String[]{ "delete", str[1] };
        case "list":
            return new String[]{ "list" };
        case "find":
            return new String[] { "find", str[1] };
        case "todo":
            String task = str[1];
            return new String[]{ "todo", task };
        case "deadline":
            String[] taskDetails = str[1].split("/by ");
            String[] s = taskDetails[1].split(" ", 2);
            return new String[]{ "deadline", taskDetails[0], s[0], s[1] };
        case "event":
            String[] eventDetail = str[1].split(" /at ");
            return new String[]{ "event", eventDetail[0], eventDetail[1] };
        default:
            return new String[]{ "" };
        }
    }
}