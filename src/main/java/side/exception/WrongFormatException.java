package side.exception;

public class WrongFormatException extends SideException {

    public WrongFormatException(String format) {
        super("Follow this format, don't make this worse:\n" + format);
    }
}
