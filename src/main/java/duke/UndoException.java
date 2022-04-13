package duke;

public class UndoException extends Exception {
    public UndoException() {
        super("You may not undo, please use other commands");
    }

    public UndoException(String message) {
        super(message);
    }
}
