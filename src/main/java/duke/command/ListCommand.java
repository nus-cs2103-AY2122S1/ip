package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class ListCommand extends Command {
    public static final String COMMAND = "list";

    /**
     * Executes list command and prints out the current list
     *
     * @param taskList current list
     * @param ui current ui to access print responses
     * @param storage current storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}
