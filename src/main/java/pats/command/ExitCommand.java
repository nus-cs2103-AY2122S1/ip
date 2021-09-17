package pats.command;

import static java.util.Objects.requireNonNull;

import pats.Storage;
import pats.TaskList;
import pats.ui.Ui;

public class ExitCommand extends Command {
    /**
     * Outputs goodbye message.
     *
     * @param taskList duke's task list
     * @param ui current Ui instance
     * @param storage current storage instance
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        requireNonNull(ui);

        Ui.printGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o.getClass() == this.getClass();
    }
}
