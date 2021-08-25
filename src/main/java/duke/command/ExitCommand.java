package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    /**
     * Method to determine if the command is an exit command
     *
     * @return whether it is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Method to carry out the command
     *
     * @param tasks the list of tasks to be modified
     * @param ui the UI for the program
     * @param storage the storage utility for the program
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * Method to determine if the object is an ExitCommand
     *
     * @param obj the object to be used for comparison
     * @return boolean indicating if the object is an ExitCommand
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
