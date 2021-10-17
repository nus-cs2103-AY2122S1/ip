package duke;

public class Parser {

    /**
     * Parses the user input string.
     *
     * @param input the input user string
     *
     * @return a string array representing the user's input
     */
    public static void parse(String input) throws DukeException {
        DukeException.checkInput(input);
    }

    public static String[] parseCommand(String input) {
        String[] str = input.split(" ", 2);
        switch (str[0]) {
        case "t":
            str[0] = "todo";
            break;
        case "d":
            str[0] = "deadline";
            break;
        case "e":
            str[0] = "event";
            break;
        }
        String arg = str[0];

        switch (arg) {
        case "bye":
            Storage.save();
            System.exit(0);
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