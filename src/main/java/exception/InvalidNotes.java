package exception;

public class InvalidNotes extends DukeException {
    @Override
    public String getMessage() {
        return "I don't see any notes information added, please add it in or remove the '--' flag ";
    }
}
