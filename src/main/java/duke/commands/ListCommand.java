package duke.commands;

import duke.main.Ui;
import duke.main.Storage;
import duke.main.TaskList;

public class ListCommand extends Command {
    public ListCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //Ui
        ui.showTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}