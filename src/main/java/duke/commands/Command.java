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
    public abstract void execute(TaskList taskList, Ui ui);

    /**
     * Checks whether the chatbot should exit following the execution of the command
     *
     * @return boolean value of whether the chatbot should exit
     */
    public abstract boolean isExit();

}
