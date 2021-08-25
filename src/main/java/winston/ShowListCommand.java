package winston;

/**
 *  Represents the list command from the parent abstract class Command.
 */
public class ShowListCommand extends Command {
    public ShowListCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Prints the all tasks on TaskList as well as the number of uncompleted tasks remaining
     */
    @Override
    public void run() {
        Ui.printList(taskList);
        Ui.printTasksLeft(taskList.uncompletedTasks());
    }
}
