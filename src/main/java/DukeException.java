public class DukeException extends Exception {
    private static String msg;

    public DukeException(String errorMessage) {
        super(errorMessage);
        msg = errorMessage;
    }

    @Override
    public String toString() {
        return ("Oh No! I do not understand your input due to the following problem:\n" + msg);
    }
}
