public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList taskList) {
        taskList.printTaskList();
    }
}
