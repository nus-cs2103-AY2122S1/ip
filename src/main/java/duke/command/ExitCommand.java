package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import static duke.Ui.dukePrint;

public class ExitCommand implements Command {
    public void execute(TaskList task, Ui ui, Storage storage){
        dukePrint("Bye. Hope to see you again soon!\n");
    }
    public boolean isExit() {
        return true;
    }
}
