package commands;

/**
 * A type of command that makes changes on the taskList and that can be undone.
 */
public abstract class UndoableCommand extends Command {

    private Runnable undoFunction;

    /**
     * Undoes the previous command and restore the taskList to the previous version;
     */
    public void undo() {
        assert this.undoFunction != null : "Error while undoing";
        this.undoFunction.run();
    }

    protected void setUndoFunction(Runnable undoFunction) {
        this.undoFunction = undoFunction;
    }

    protected abstract void setUndo();
}
