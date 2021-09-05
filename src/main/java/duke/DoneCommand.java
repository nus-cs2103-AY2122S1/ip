package duke;

public class DoneCommand extends Command{
    private int index;
    public DoneCommand(int index){
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui u, Storage storage) {
        tasks.markTaskDone((this.index - 1));
       u.displayTaskDone(tasks.getTask(this.index - 1));
    }
}
