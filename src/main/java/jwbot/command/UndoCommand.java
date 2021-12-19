package jwbot.command;

import jwbot.data.TaskList;
import jwbot.data.exception.JwBotException;
import jwbot.data.task.Task;
import jwbot.storage.Storage;
import jwbot.ui.Ui;

public class UndoCommand extends Command {

    public UndoCommand(String input) {
        super(input);
        assert input.equals("undo");
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JwBotException {
        tasks.undoCommand();
        return ui.showUndoSuccessMessage();
    }

    @Override
    protected Task processTask(TaskList tasks) {
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
