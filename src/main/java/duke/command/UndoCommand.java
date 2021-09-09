package duke.command;

import duke.*;

import java.io.IOException;

public class UndoCommand extends Command {
    public static final String COMMAND = "undo";

    public UndoCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, History history) throws IOException {
    }

    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage, History history) throws IOException {
        if (history.history.isEmpty()) {
            return rf.formatError("Nothing to undo!");
        }
        Command lastCommand = history.getLastCommand();
        history.undo();
        return lastCommand.undo(taskList, rf, storage);
    }
}
