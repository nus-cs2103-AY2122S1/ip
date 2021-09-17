package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents command to list all tasks
 */
public class ListCommand extends Command {

    /**
     * Creates list command
     * @param userInput user input
     * @throws DukeException throws if user input is invalid
     */
    public ListCommand(String userInput) throws DukeException {
        super(userInput);
    }

    /**
     * executes of list command
     * @param taskList The object that holds a list of Task
     * @param ui The object responsible for updating Ui response
     * @param storage The object responsible to save/load list of task to/from hard disk
     * @return
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //Ui
        return ui.showTaskList(taskList);
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
