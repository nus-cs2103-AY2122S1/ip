package kermit.command;

import kermit.KermitException;
import kermit.Storage;
import kermit.TaskList;
import kermit.Ui;
import kermit.tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String filterText;

    public FindCommand(String filterText) {
        this.filterText = filterText;
    }
    /**
     * Execute command and process action.
     *
     * @param taskList Instance of task list used.
     * @param ui Instance of Ui used.
     * @param storage Instance of storage class used.
     * @throws KermitException if there are any errors while performing action.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KermitException {
        TaskList filteredTasks = taskList.filter(filterText);
        ui.showFilteredTasks(filteredTasks);
    };

    /**
     * If command is exit command
     *
     * @return true if it is an instanceOf Exit, otherwise returns false
     */
    public boolean isExit() {
        return false;
    }
}


