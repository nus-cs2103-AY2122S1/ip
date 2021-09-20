package jarvis.exception;

public class UndoException extends JarvisException {
    /**
     * Default constructor for UndoException.
     */
    public UndoException() {
        super("Undo protocol terminated! Cannot undo specified number of commands!");
    }
}
