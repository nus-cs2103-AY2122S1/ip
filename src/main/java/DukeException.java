public class DukeException extends Exception {
    String header;
    public DukeException(String message) {
        super(message);
        this.header = "☹ OOPS!!! ";
    }

    @Override
    public String getMessage() {
        return this.header + super.getMessage();
    }
}
