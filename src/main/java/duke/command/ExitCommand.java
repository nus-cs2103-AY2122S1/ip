package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.writeToFile(tasks);
        ui.showExit();
    }

    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ExitCommand;
    }
}