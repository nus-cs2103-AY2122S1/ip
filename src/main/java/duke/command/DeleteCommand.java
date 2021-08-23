package duke.command;

import duke.exception.DukeException;
import duke.exception.Messages;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String cmd) {
        super(cmd);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(line);
            ui.showDelete(tasks.remove(index), tasks.getSize());
            super.execute(tasks, ui, storage);
        } catch (Exception e) {
            throw new DukeException(Messages.EXIST.toString());
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
