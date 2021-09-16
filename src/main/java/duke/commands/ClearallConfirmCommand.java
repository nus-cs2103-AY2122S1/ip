package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;

public class ClearallConfirmCommand extends Command {

    public ClearallConfirmCommand(Storage storage, TaskList taskList, String[] strParse, boolean isActivatedClearCommand) {
        super(storage, taskList, strParse, isActivatedClearCommand);
    }

    @Override
    public String execute() {
        try {
            if (strParse.length > 1) {
                throw new InvalidInputException();
            }
            if (!isActivatedClearCommand) {
                throw new InvalidInputException();
            }

            isActivatedClearCommand = false;
            taskList.clearTaskList();
            storage.saveData(taskList);

            return ("Alright. Cleared! Uwu!");
        } catch (DukeException e) {
            return this.getErrorMessage(e);
        }
    }
}
