package duke;

/**
 * Class for parsing keywords, and handling unparsed Strings.
 */
public class Parser {
    Parser() {
    }

    /**
     * Parses keywords.
     * @param str String to be parsed.
     * @return String of keyword.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public String parseCommand(String str) throws DukeException {
        if (str.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (str.equalsIgnoreCase("help")) {
            return "help";
        } else if (str.equalsIgnoreCase("list")) {
            return "list";
        } else if (str.equalsIgnoreCase("todo")) {
            return "todo";
        } else if (str.equalsIgnoreCase("deadline")) {
            return "deadline";
        } else if (str.equalsIgnoreCase("event")) {
            return "event";
        } else if (str.equalsIgnoreCase("done")) {
            return "done";
        } else if (str.equalsIgnoreCase("delete")) {
            return "delete";
        } else if (str.equalsIgnoreCase("find")) {
            return "find";
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Joins together String for find keyword.
     * @param strparse Array of Strings to be joined together.
     * @return Keyword to be found.
     */
    public String parseFind(String[] strparse) {
        StringBuilder strb = new StringBuilder();
        for (int i = 1; i < strparse.length; i ++) {
            strb.append(strparse[i]);
        }
        return strb.toString();
    }
}
