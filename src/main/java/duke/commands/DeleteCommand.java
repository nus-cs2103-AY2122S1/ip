package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(Storage storage, TaskList taskList, String[] strParse, boolean isActivatedClearCommand) {
        super(storage, taskList, strParse, isActivatedClearCommand);
    }

    @Override
    public String execute() {
        try {
            Task t = taskList.delete(strParse);
            storage.saveData(taskList);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Thanwk youw forw youwr serwwice! Thwis taskw hasw beenw deweted:\n")
                    .append(t.toString() + '\n');
            stringBuilder.append("Youw noww havew "
                    + (taskList.getTaskCounter())
                    + " taskw(s) inw thew wist! uwu\n");

            return stringBuilder.toString();
        } catch (DukeException e) {
            return this.getErrorMessage(e);
        }
    }
}
