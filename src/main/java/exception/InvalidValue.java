package exception;

public class InvalidValue extends DukeException {

    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but the task number seems to be invalid.";
    }
}
