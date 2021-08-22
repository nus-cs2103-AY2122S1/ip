public class ListCommand extends Command{

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.reply(
                String.format("Here are the tasks in your list:\n%s", taskManager.listTasks())
        );
    }
}
