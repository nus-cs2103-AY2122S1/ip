/**
 * The ListCommand class encapsulates the execution of the list command from the user.
 */
public class ListCommand extends Command {
    /**
     * Executes the response to the delete command from the user.
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showList(taskList);
    }
}
