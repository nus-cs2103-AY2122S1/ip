package winston;

public class DeleteCommand extends Command {
    private final int index;
    
    public DeleteCommand(TaskList taskList, int index) {
        super(taskList);
        this.index = index;
    }

    @Override
    public void run() {
        super.taskList.deleteTask(this.index);
        Ui.printDontWorry();
        Ui.printTasksLeft(super.taskList.completedTasks());
    }
}
