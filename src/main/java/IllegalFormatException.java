public class IllegalFormatException extends DukeException {
    public IllegalFormatException(String format) {
        super("☹ Sorry! Please follow the following input format:\n" + format);
    }
}