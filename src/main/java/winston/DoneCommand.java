package winston;

/**
 *  Represents the done command from the parent abstract class Command.
 */
public class DoneCommand extends Command{
    private int index;
    
    public DoneCommand(TaskList tasklist, int index) {
            super(tasklist);
            this.index = index;
    }


    /**
     * Marks a task from the TaskList based on the index provided as complete and prints number of uncompleted 
     * tasks remaining
     */
    @Override
    public void run() {
        super.taskList.markTask(this.index);
        Ui.printDontWorry();
        Ui.printTasksLeft(super.taskList.uncompletedTasks());
    }
}
