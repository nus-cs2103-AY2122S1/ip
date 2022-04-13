package duke;

public class Parser {

    /**
     * Parses text the user inputs into first word and the rest.
     *
     * @param input user input.
     * @return Array with 2 elements containing first word and the rest.
     */
    public static String[] parseUserInput(String input) {
        String[] cmdArgs = input.split(" ", 2);
        return stripStrings(cmdArgs);
    }

    /**
     * Parses task argument into task name and time.
     *
     * @param args Task argument.
     * @param delim Delimiter used to split the input.
     * @return Array with 2 elements containing task name and time.
     */
    public static String[] parseArgs(String args, String delim) {
        String[] strings = args.split(delim, 2);
        checkArg(strings);
        return stripStrings(strings);
    }

    /**
     * Parses text in storage into type of task, task argument and done status.
     *
     * @param line Line in storage.
     * @return Array with 3 elements, type of task, task argument and done status.
     */
    public static String[] parseStorage(String line) {
        String[] parts = line.split("\\|");
        return stripStrings(parts);
    }

    /**
     * Parses text in storage into tags.
     *
     * @param line Text in storage for storing tags.
     * @return Array of tags.
     */
    public static String[] parseTags(String line) {
        String[] parts = line.split("#");
        return stripStrings(parts);
    }

    private static void checkArg(String[] arg) throws DukeException {
        if (arg.length < 2) {
            throw new DukeException("too little argument");
        } else if (arg.length > 2) {
            throw new DukeException("too many argument");
        }
    }

    private static String[] stripStrings(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].strip();
        }
        return strings;
    }
}
