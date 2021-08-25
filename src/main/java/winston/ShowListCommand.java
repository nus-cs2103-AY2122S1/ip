package winston;

public class ShowListCommand extends Command {
    public ShowListCommand(TaskList taskList) {
        super(taskList);
    }

    @Override
    public void run() {
        Ui.printList(taskList);
        Ui.printTasksLeft(taskList.completedTasks());
    }
}
