package Duke.Main;

public class DukeException extends RuntimeException {
    public enum Type {INCOMPLETE, OUT_OF_BOUND, SYNTAX_ERROR, INVALID_FORMAT}
    protected final Type type;
    public DukeException(String message, Type type) {
        super(message);
        this.type = type;
    }

    @Override
    public String getMessage() {
        switch (type) {
        case INCOMPLETE:
            return "☹ OOPS!!! Your command is incomplete!\n" +
                    "Please enter 'help' for further instructions!";
        case OUT_OF_BOUND:
            return "☹ OOPS!!! Your task number is out of bound!\n" +
                    "Please enter 'help' for further instructions!";
        case SYNTAX_ERROR:
            return "☹ OOPS! I'm sorry, but I don't know what that means :-(\n" +
                    "Please enter 'help' for further instructions!";
        case INVALID_FORMAT:
            return "OOPS! Please try to enter the date in the following format:\n" +
                    "YYYY-MM-DD (optionally, HH:MM)";
        default:
            return super.getMessage();
        }
    }
}

