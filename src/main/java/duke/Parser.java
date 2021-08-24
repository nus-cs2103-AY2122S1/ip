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
            default:
                String task = str[1];
                if (arg.equals("todo")) {
                    return new String[]{ "todo", task };
                } else if (str[0].equals("deadline")) {
                    String[] taskDetail = task.split("/by ");
                    String[] s = taskDetail[1].split(" ", 2);
                    return new String[]{ "deadline", taskDetail[0], s[0], s[1] };
                } else { // is an duke.Event
                    String[] taskDetail = str[1].split(" /at ");
                    return new String[]{ "event", taskDetail[0], taskDetail[1] };
                }
        }
    }
}