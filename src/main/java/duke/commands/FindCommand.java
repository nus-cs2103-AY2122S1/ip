package duke.commands;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;

public class FindCommand extends Command {
    public FindCommand(Storage storage, TaskList taskList, String[] strParse, boolean isActivatedClearCommand) {
        super(storage, taskList, strParse, isActivatedClearCommand);
    }

    @Override
    public String execute() {
        try {
            Parser parser = new Parser();
            String tasksFound = taskList.find(parser.parseFind(strParse));

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Foundw! Here are the matching tasks:\n")
                    .append(tasksFound + '\n');

            return stringBuilder.toString();
        } catch (DukeException e) {
            return this.getErrorMessage(e);
        }
    }
}
