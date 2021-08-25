import exceptions.InvalidNumberInputException;
import task.Task;

public class DeleteCommand extends Command{

    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            Task currTask = taskList.remove(index - 1);
            ui.displayDelete(currTask.toString(), taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException ex) {
            throw new InvalidNumberInputException();
        }
    }
}
