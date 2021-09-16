package exception;

public class InvalidValue extends DukeException {

    @Override
    public String getMessage() {
        return "I don't see a task at that index, try again with a different number?";
    }
}
