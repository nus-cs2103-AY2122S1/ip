public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super("☹ OOPS!!! " + errorMessage);
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! %s", super.getMessage());
    }
}
