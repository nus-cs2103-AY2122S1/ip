package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class FindCommand extends Command {
    public FindCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        //Ui
        String keyWord = this.userInputList.get(1);
        ui.showFoundTask(taskList.findTaskByKeyWord(keyWord));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
