package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskHandler taskHandler, Storage storage, Ui ui) throws DukeException {
        taskHandler.printTasks();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
