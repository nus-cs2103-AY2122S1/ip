public class DukeException extends Exception {
    public DukeException (String message) {
        super("I'm sorry! " + message);
    }

    @Override
    public String toString() {
        return "I'm sorry! " + super.getMessage();
    }
}
