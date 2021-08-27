package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents command to find tasks with key word
 */
public class FindCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public FindCommand(String userInput) throws DukeException {
        super(userInput);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //Ui
        String keyWord = this.userInputList.get(1);
        ui.showFoundTask(taskList.findTaskByKeyWord(keyWord));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
