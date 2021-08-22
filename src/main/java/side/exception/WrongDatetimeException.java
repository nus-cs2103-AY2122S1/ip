package side.exception;

public class WrongDatetimeException extends SideException {

    public WrongDatetimeException() {
        super("Wrong datetime format...\n" +
                "Use [YYYY-MM-DD] or [YYYY-MM-DD], [HHMM]");
    }
}