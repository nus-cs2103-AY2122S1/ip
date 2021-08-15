public class DukeException extends IllegalArgumentException {

    private String task;

    public DukeException(String message) {
        super(message);
    }

    public DukeException(String message, String task) {
        super(message);
        this.task = task;
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a " + task + " cannot be empty.";
    }
}
