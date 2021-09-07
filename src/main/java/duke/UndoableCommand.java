package duke;

public abstract class UndoableCommand extends Command {
    public UndoableCommand(TaskList tasks) {
        super(tasks);
    }
    public UndoableCommand(TaskList tasks, String input) {
        super(tasks, input);
    }
    public abstract String undo();
}
