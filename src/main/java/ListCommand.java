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
            taskList.printTasks(ui);
    }
}
