package winston;

/**
 *  Represents the delete command from the parent abstract class Command.
 */
public class DeleteCommand extends Command {
    private final int index;
    
    public DeleteCommand(TaskList taskList, int index) {
        super(taskList);
        this.index = index;
    }

    /**
     * Deletes a task from the TaskList based on the index provided and prints number of uncompleted tasks remaining
     */
    @Override
    public void run() {
        super.taskList.deleteTask(this.index);
        Ui.printDontWorry();
        Ui.printTasksLeft(super.taskList.uncompletedTasks());
    }
}
