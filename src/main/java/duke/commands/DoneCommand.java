package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;

public class DoneCommand extends Command {

    public DoneCommand(Storage storage, TaskList taskList, String[] strParse, boolean isActivatedClearCommand) {
        super(storage, taskList, strParse, isActivatedClearCommand);
    }

    @Override
    public String execute() {
        try {
            int taskNo = taskList.markDone(strParse);
            storage.saveData(taskList);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Thanwk youw forw youwr serwwice! Thwis taskw isw downe:\n")
                    .append(taskList.getTaskDescr(taskNo) + '\n');

            return stringBuilder.toString();
        } catch (DukeException e) {
            return this.getErrorMessage(e);
        }
    }
}
