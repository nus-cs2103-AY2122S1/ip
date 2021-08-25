package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class IncorrectCommand extends Command {
    String errorMessage;

    /**
     * Class constructor for IncorrectCommand Class specifying the error message
     */
    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Execute the command
     *
     * @param tasks    the TaskList
     * @param ui       the Ui
     * @param storage  the data source
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(errorMessage);
    }

    /**
     * Check if the command is an ExitCommand
     *
     * @return           boolean stating if command is ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Return the toString of the class
     *
     * @return           toString of the class
     */
    @Override
    public String toString() {
        return "[error]";
    }
}
