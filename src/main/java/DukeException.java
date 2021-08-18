public class DukeException extends RuntimeException {
    String err;
    public DukeException(String err) {
        super(err);
    }

    public String toString() {
        return err;
    }
}