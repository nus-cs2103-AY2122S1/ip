package bot.assembly.error;

public class InvalidCommandFormatException extends Exception {
    public InvalidCommandFormatException(String msg) {
        super(msg);
    }
}
