package bot.assembly.error;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
