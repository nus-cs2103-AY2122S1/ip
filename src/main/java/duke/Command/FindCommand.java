package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String filter;

    public FindCommand(String input) throws DukeException {
        try{
            this.filter = input.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The description cannot be empty!");
        }
    }

    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showSearchResult(filter, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
