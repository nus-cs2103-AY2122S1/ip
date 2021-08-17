public class DukeException extends Exception {

    public DukeException(String str) {
        super(str);
    }

    public static void checkInput(String str) throws DukeException {
        if (str.startsWith("todo") || str.startsWith("deadline") || str.startsWith("event")
            || str.startsWith("list") || str.startsWith("bye") || str.startsWith("done")
                || str.startsWith("delete")) {
            if (str.equals("todo") || str.equals("deadline") || str.equals("event") || str.equals("delete")) {
                throw new DukeException("Jo needs to know what type of fly it is! :(");
            }
        } else {
            throw new DukeException("Jo does not understand not-frog speak! :(");
        }
    }
}
