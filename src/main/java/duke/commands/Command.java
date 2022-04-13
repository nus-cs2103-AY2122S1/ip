package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command is an abstract class which encapsulates the execution of the commands received from the parser
 */
public abstract class Command {

    /**
     * Carries out the operations on the list of tasks
     *
     * @param taskList the list of tasks upon which the operations need to be performed
     * @param ui the ui in which the result message of the command execution is displayed to the user
     */
    public abstract String execute(TaskList taskList, Ui ui);

}