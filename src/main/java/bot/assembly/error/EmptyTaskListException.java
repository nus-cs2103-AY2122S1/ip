package bot.assembly.error;

public class EmptyTaskListException extends Exception {
    public EmptyTaskListException(String msg) {
        super(msg);
    }
}
