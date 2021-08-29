import java.io.IOException;

public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws NoSuchTaskException {
        try {
            Task temp = tasklist.getTask(index);
            tasklist.doneTask(index);
            ui.showDoneMessage(temp, tasklist);
            storage.writeToFile(tasklist);
        } catch (NoSuchTaskException | IOException e) {
            throw new NoSuchTaskException("Task index is out of bounds");
        }
    }
}