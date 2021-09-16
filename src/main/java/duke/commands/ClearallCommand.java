package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.IncorrectInputException;

public class ClearallCommand extends Command {
    public ClearallCommand(Storage storage, TaskList taskList, String[] strParse, boolean isActivatedClearCommand) {
        super(storage, taskList, strParse, isActivatedClearCommand);
    }

    @Override
    public String execute() {
        try {
            if (strParse.length > 1) {
                throw new IncorrectInputException("clearall", "'clearall'");
            }
            isActivatedClearCommand = true;
            return ("Pwease confirm clear task list by typing 'y'");
        } catch (DukeException e) {
            return this.getErrorMessage(e);
        }
    }
}
