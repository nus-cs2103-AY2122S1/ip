package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.IncorrectInputException;

public class ClearallCommand extends Command {
    public ClearallCommand(Storage storage, TaskList taskList, String[] strParse) {
        super(storage, taskList, strParse);
    }

    @Override
    public String execute() {
        try {
            if (strParse.length > 1) {
                throw new IncorrectInputException("clearall", "'clearall'");
            }
            return ("Pwease confirm clear task list by typing 'y'");
        } catch (DukeException e) {
            return this.getErrorMessage(e);
        }
    }
}
