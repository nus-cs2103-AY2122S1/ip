package duke.command;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) {
        return ui.help();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
