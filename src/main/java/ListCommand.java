public class ListCommand extends Command {
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here is your task list:");
        ui.showIndentedMessage(tasks.toString());
        return tasks;
    }
}
