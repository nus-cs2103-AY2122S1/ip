package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        super("bye");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printToUser("Bye. Hope to see you again soon!");        
    }

    @Override
    public boolean isExit() {
        return true;
    }
}