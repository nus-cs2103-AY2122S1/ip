/**
 * Contains the executables for the 'list' command.
 */
public class ListCommand extends Command {
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listView(taskList.getAllTasks());
    }
}
