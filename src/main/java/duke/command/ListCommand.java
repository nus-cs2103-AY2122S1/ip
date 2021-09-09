package duke.command;

import duke.History;
import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.Ui;


public class ListCommand extends Command {
    public static final String COMMAND = "list";

    /**
     * Executes list command and prints out the current list
     *  @param taskList current list
     * @param ui current ui to access print responses
     * @param storage current storage
     * @return
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, History history) {
        ui.printList(taskList);
    }

    /**
     * Executes list command and returns the current list
     *
     * @param taskList current list
     * @param rf Response Formatter
     * @param storage current storage
     * @return formatted response
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage, History history) {
        return rf.formatList(taskList);
    }
}
