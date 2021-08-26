package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.ui.Ui;

/**
 * An  ExitCommand class encapsulates the instructions for user to exit and close Duke.
 */
public class ExitCommand extends Command {

    public ExitCommand() {

    }

    /**
     * executes the command on the specified tasklist
     *
     * @param taskList tasklist to be operated on
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList){
        Ui.showExitMessage();
    }

    /**
     * returns the type of command
     *
     * @return exit
     */
    @Override
    public String getType() {
        return "exit";
    }

}
