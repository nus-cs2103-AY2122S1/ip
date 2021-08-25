public class DoneCommand extends Command{
    private int index;
    
    public DoneCommand(TaskList tasklist, int index) {
            super(tasklist);
            this.index = index;
    }
    
    @Override
    public void run() {
        super.taskList.markTask(this.index);
        Ui.printDontWorry();
        Ui.printTasksLeft(super.taskList.size());
    }
}
