package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import static duke.Ui.dukePrint;

public class ListCommand implements Command{
    public void execute(TaskList task, Ui ui, Storage storage){
        task.displayList();
    }
    public boolean isExit() {
        return false;
    }
}
