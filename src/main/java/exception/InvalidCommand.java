package exception;

public class InvalidCommand extends DukeException {

    @Override
    public String getMessage() {
        return "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
