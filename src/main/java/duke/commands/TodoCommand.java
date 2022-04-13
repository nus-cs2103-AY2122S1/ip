package duke.commands;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;

public class TodoCommand extends Command {

    public TodoCommand(Storage storage, TaskList taskList, String[] strParse) {
        super(storage, taskList, strParse);
    }

    @Override
    public String execute() {
        try {
            Parser parser = new Parser();
            taskList.addTodo(parser.parseTodo(strParse));
            storage.saveData(taskList);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Uwu! Addewd yourw taskws:\n").append(taskList.lastAddedTask() + '\n');
            stringBuilder.append("Youw noww havew " + (taskList.getTaskCounter())
                    + " taskw(s) inw thew wist! uwu\n");

            return stringBuilder.toString();
        } catch (DukeException e) {
            return this.getErrorMessage(e);
        }
    }
}
