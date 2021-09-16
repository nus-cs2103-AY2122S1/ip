package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.IncorrectInputException;

public class ListCommand extends Command {

    public ListCommand(Storage storage, TaskList taskList, String[] strParse, boolean isActivatedClearCommand) {
        super(storage, taskList, strParse, isActivatedClearCommand);
    }

    @Override
    public String execute() {
        try {
            if (strParse.length > 1) {
                throw new IncorrectInputException("list", "'list'");
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Uwu! Herw arw yourw taskws:\n");
            if (taskList.getTaskCounter() == 0) {
                stringBuilder.append("Itw seewsm like youw wist is emptwy! Congwats!\n");
            } else {
                stringBuilder.append(taskList.displayList());
            }

            return stringBuilder.toString();
        } catch (DukeException e) {
            return this.getErrorMessage(e);
        }
    }
}
