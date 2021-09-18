package winston;

/**
 * Represents the delete command from the parent abstract class Command.
 */
public class DeleteCommand extends Command {
    private final int index;
    
    public DeleteCommand(TaskList taskList, int index) {
        super(taskList);
        this.index = index;
    }

    /**
     * Deletes a task from the TaskList based on the index provided and prints number of uncompleted tasks remaining.
     */
    @Override
    public String run() {
        super.taskList.deleteTask(this.index);
        String result =  Ui.deleteItem() + Ui.printTasksLeft(super.taskList.numberOfIncompleteTasks());
        assert(!result.equals(""));
        return result + "\n" + super.taskList.getList();
    }
}
