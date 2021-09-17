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
     * Creates find command
     * @param userInput user input
     * @throws DukeException
     */
    public FindCommand(String userInput) throws DukeException {
        super(userInput);
    }

    /**
     * Executes find command
     * @param taskList The object that holds a list of Task
     * @param ui The object responsible for updating Ui response
     * @param storage The object responsible to save/load list of task to/from hard disk
     * @return string to be printed out to user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //Ui
        String keyWord = this.userInputList.get(1);
        return ui.showFoundTask(taskList.findTaskByKeyWord(keyWord));
    }

    /**
     * Check if command is exit command
     * @return true if exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
