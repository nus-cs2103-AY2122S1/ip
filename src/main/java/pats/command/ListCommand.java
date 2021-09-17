package pats.command;

import static java.util.Objects.requireNonNull;

import pats.Storage;
import pats.TaskList;
import pats.ui.Ui;

public class ListCommand extends Command {
    /**
     * Displays the task list.
     *
     * @param taskList duke's task list
     * @param ui current Ui instance
     * @param storage current storage instance
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        requireNonNull(taskList);
        requireNonNull(ui);
        requireNonNull(storage);

        Ui.printList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o.getClass() == this.getClass();
    }
}
