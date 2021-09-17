package duke.command;

import java.io.IOException;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;

public class UndoCommand extends Command {
    public static final String COMMAND = "undo";
    private static final String ERROR_MESSAGE = "Nothing to undo!";

    public UndoCommand() {}

    @Override
    public String execute(TaskList taskList, ResponseFormatter rf,
                          Storage storage, History history) throws IOException {
        if (history.isEmpty()) {
            return rf.formatError(ERROR_MESSAGE);
        }
        Command lastCommand = history.getLastCommand();
        history.undo();
        return lastCommand.undo(taskList, rf, storage);
    }
}
