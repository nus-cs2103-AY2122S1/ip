package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command is an abstract class which encapsulates the execution of the commands received from the parser
 */

public abstract class Command {

    /**
     * The abstract method execute facilitates the operations on the list of tasks and accordingly sends
     * calls to the ui to display a message
     *
     * @param taskList the list of tasks upon which the operations need to be performed
     * @param ui the ui in which a message relating to the command execution is displayed to the user
     */
    public abstract void execute(TaskList taskList, Ui ui);

    /**
     * Checks whether the chatbot should exit following the execution of the command
     *
     * @return boolean value of whether the chatbot should exit
     */
    public abstract boolean isExit();

}
