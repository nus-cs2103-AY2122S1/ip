package duke;

public class DukeException extends Exception {

    public DukeException(String str) {
        super(str);
    }

    /**
     * Checks if the user input string is valid.
     *
     * @param str the user input
     * @throws DukeException If input does not match Duke's command words
     */
    public static void checkInput(String str) throws DukeException {
        if (str.startsWith("todo") || str.startsWith("deadline") || str.startsWith("event")
            || str.startsWith("list") || str.startsWith("bye") || str.startsWith("done")
                || str.startsWith("delete") || str.startsWith("find") || str.equals("t")
                || str.equals("d") || str.equals("e")) {
            if (str.equals("todo") || str.equals("deadline") || str.equals("event") || str.equals("delete")
                || str.equals("t") || str.equals("d") || str.equals("e")) {
                throw new DukeException("Jo needs to know what type of fly it is! :(");
            }
        } else {
            throw new DukeException("Jo does not understand non-frog speak! :(");
        }
    }
}
