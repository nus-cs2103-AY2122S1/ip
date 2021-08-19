public class DukeException extends RuntimeException {
    public DukeException(String errMessage) {
        super("☹ OOPS!!! " + errMessage);
    }
}
