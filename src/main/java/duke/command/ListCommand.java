package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showList(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ListCommand) {
            return true;
        }
        return false;
    }
}
