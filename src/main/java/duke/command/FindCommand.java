package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String searchFilter;
    
    public FindCommand(String command) throws DukeException {
        String[] holder = command.trim().split(" ", 2);
        if (holder.length == 1) {
            throw new duke.DukeException(" ☹ OOPS!!! The description of a search cannot be empty.");
        } else {
            searchFilter = holder[1];
        }
    }
    
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showFindList(taskList, searchFilter);
    }
    
    public boolean isExit() {
        return false;
    }
}
