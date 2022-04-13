package duke;

/**
 * A class to represent errors encountered during program runetime.
 */
public class DukeException extends RuntimeException {
    private String msg;
    public DukeException(String msg) {
        this.msg = msg;
    }

    /**
     * Prints the error message.
     */
    public void printMsg() {
        System.out.println(msg);
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
