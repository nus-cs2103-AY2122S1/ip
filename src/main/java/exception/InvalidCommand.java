package exception;

public class InvalidCommand extends DukeException {

    @Override
    public String getMessage() {
        return "Please don't confuse me with unknown commands, I do not know what that means! >_<";
    }
}
