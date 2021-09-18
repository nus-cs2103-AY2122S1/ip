package winston;

/**
 * Represents the done command from the parent abstract class Command.
 */
public class DoneCommand extends Command {
    private final int index;
    
    public DoneCommand(TaskList tasklist, int index) {         
        super(tasklist);
        this.index = index;
    }


    /**
     * Marks a task from the TaskList based on the index provided as complete and prints number of uncompleted 
     * tasks remaining.
     */
    @Override
    public String run() {
        super.taskList.markTask(this.index);
        String result = Ui.printDontWorry() + Ui.printTasksLeft(super.taskList.numberOfIncompleteTasks());
        assert(!result.equals(""));
        return result;
    }
}
