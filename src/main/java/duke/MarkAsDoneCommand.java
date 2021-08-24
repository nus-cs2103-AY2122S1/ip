package duke;

public class MarkAsDoneCommand extends Command {
    private int doneListIndex;//index in the list

    public MarkAsDoneCommand(int doneListIndex){
        this.doneListIndex = doneListIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.get(doneListIndex).done();
        storage.convertTaskListToFile(tasks);
        ui.markAsDone(tasks.get(doneListIndex));
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
