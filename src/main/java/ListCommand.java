/**
 * This is a ListCommand class that extends Command.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }


    @Override
    public void execute(TaskList taskList, Storage store, Ui ui)
            throws EmptyListException {
        if (taskList.getSize() != 0) {
            ui.printList(taskList.loadList());
        } else {
            throw new EmptyListException();
        }
    }
}
